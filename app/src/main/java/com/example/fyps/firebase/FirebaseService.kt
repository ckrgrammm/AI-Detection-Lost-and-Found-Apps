package com.example.fyps.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.fyps.R
import com.example.fyps.activities.ChatActivity
import com.example.fyps.activities.UsersActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.Random

class FirebaseService : FirebaseMessagingService() {

    val CHANNEL_ID = "my_notification_channel"
    companion object{
        var sharedPref: SharedPreferences? = null

        var token:String?
            get(){
                return sharedPref?.getString("token","")
            }
            set(value){
                sharedPref?.edit()?.putString("token",value)?.apply()
            }
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        token = p0
    }

    fun sendMessage(senderId: String, receiverId: String, imageUrl: String?, textMessage: String?) {
        var reference: DatabaseReference? = FirebaseDatabase.getInstance().getReference()

        var hashMap: HashMap<String, String> = HashMap()
        hashMap.put("senderId", senderId)
        hashMap.put("receiverId", receiverId)

        if (!imageUrl.isNullOrBlank()) {
            hashMap.put("messageType", "image")
            hashMap.put("message", imageUrl)
        } else if (!textMessage.isNullOrBlank()) {
            hashMap.put("messageType", "text")
            hashMap.put("message", textMessage)
        }

        Log.d("SendMessage", "Sending message: $hashMap")

        reference!!.child("Chat").push().setValue(hashMap)
            .addOnSuccessListener {
                // 发送成功后滚动 RecyclerView 到最后一条消息
                // 在这里加入你的逻辑
            }
    }


    private fun sendSecondQuestion(userId: String?) {
        val secondQuestion = "你好吗？ 【1】好 【2】不好"

        // 调用 sendMessage 方法发送第二个问题
        token?.let { sendMessage(userId ?: "", it, null, secondQuestion) }
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d("FirebaseService", "Received a new message: ${p0.data}")

        val messageType = p0.data["messageType"]
        val receivedMessage = p0.data["message"]
        val notificationText = when (messageType) {
            "text" -> p0.data["message"]
            "image" -> "You received an image"
            else -> "New Message"
        }

        val userId = p0.data["userId"]

        // 添加日志以便调试
        Log.d("FirebaseService", "MessageType: $messageType")
        Log.d("FirebaseService", "ReceivedMessage: $receivedMessage")
        Log.d("FirebaseService", "UserId: $userId")

        // 处理接收到的消息
        if (messageType == "text") {
            when (receivedMessage) {
                "1" -> {
                    // 用户回复1，发送第二个问题
                    Log.d("FirebaseService", "User replied with 1, sending second question.")
                    sendSecondQuestion(userId)
                    sendSecondQuestion(userId)
                }
                "2" -> {
                    // 用户回复2，可以选择停止发送问题或采取其他操作
                    // 这里可以添加逻辑来处理用户选择停止的情况
                }
                else -> {
                    // 处理其他用户回复的情况
                    Log.d("FirebaseService", "User replied with other message: $receivedMessage")
                }
            }
        }

        // 创建 ChatActivity 的 Intent
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("userId", userId)  // 将聊天相关信息放入 Intent extras

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = Random().nextInt()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(p0.data["title"])
            .setContentText(notificationText)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationId, notification)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager){

        val channelName = "ChannelFirebaseChat"
        val channel = NotificationChannel(CHANNEL_ID,channelName,IMPORTANCE_HIGH).apply {
            description="MY FIREBASE CHAT DESCRIPTION"
            enableLights(true)
            lightColor = Color.WHITE
        }
        notificationManager.createNotificationChannel(channel)

    }

    // FirebaseService 中添加 sendNotification 方法
    fun sendNotificationList(receiverId: String, notificationMessage: String, senderId: String) {
        // 获取 Cloud Firestore 实例
        val db = FirebaseFirestore.getInstance()

        // 创建通知消息数据
        val notificationData = notificationMessage + " from " + senderId + "!"


        // 将通知消息添加到 receiverId 对应的 notificationList 数组中
        db.collection("users").document(receiverId)
            .update("notificationList", FieldValue.arrayUnion(notificationData))
            .addOnSuccessListener {
                Log.d("FirebaseService", "Notification added to the list successfully.")
            }
            .addOnFailureListener { e ->
                Log.e("FirebaseService", "Error adding notification to the list: $e")
            }
    }

}