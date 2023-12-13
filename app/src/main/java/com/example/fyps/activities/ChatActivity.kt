package com.example.fyps.activities

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.RetrofitInstance
import com.example.fyps.adapters.ChatAdapter
import com.example.fyps.databinding.ChatBinding
import com.example.fyps.model.Chat
import com.example.fyps.model.NotificationData
import com.example.fyps.model.PushNotification
import com.example.fyps.model.User
import com.example.fyps.resource.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatActivity : AppCompatActivity() {

    var firebaseUser: FirebaseUser? = null
    var reference: DatabaseReference? = null
    var chatList = ArrayList<Chat>()
    var topic = ""
    // 在 ChatActivity 中添加以下属性
    private val CAMERA_REQUEST_CODE = 100
    private val GALLERY_REQUEST_CODE = 200
    private var imageUri: Uri? = null

    private lateinit var binding: ChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat)
        binding = ChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 使用 binding 对象引用按钮
        val btnSendMessage = binding.btnSendMessage
        val etMessage = binding.etMessage
        val chatRecyclerView = binding.chatRecyclerView

        chatRecyclerView.layoutManager = LinearLayoutManager(this)

        firebaseUser = FirebaseAuth.getInstance().currentUser  // 添加这一行

        // 获取传递过来的用户ID
        val userId = intent.getStringExtra("userId")
        var userName = intent.getStringExtra("userName")
        Log.d("YourTag", "UserId: $userId")

        val documentReference = FirebaseFirestore.getInstance().document("users/$userId")

        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val user = documentSnapshot.toObject(User::class.java)
                // 处理用户数据...
                // 输出获取到的用户信息和图片 URL
                Log.d("ChatActivity", "User Info: $user")
                if (user != null) {
                    Log.d("ChatActivity", "User Photo URL: ${user.imagePath}")
                    val userPhotoUrl = user.imagePath

                    // 使用 Glide 加载当前用户的照片到 CircleImageView 中
                    Glide.with(this@ChatActivity)
                        .load(userPhotoUrl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(binding.imgProfile)
                }
                // 设置用户名字
                if (user != null) {
                    binding.tvUserName.text = user.firstName
                }

            } else {
                // 文档不存在
            }

        }

        // 获取传递过来的用户ID
        btnSendMessage.setOnClickListener {
            var message: String = etMessage.text.toString()

            if (message.isEmpty()) {
                Toast.makeText(applicationContext, "message is empty", Toast.LENGTH_SHORT).show()
                etMessage.setText("")
            } else {
                if (userId != null) {
                    sendMessage(firebaseUser!!.uid, userId, null,message)
                }
                etMessage.setText("")
                topic = "/topics/$userId"
                PushNotification(NotificationData(userName!!,message,"text"),topic).also {
                    Log.w("sendMsg", "sendMsg")
                    sendNotification(it)
                }
            }
        }

        if (userId != null) {
            readMessage(firebaseUser!!.uid, userId)
        }

        // 在 onCreate 方法中的 btnAddPhoto 按钮点击事件中添加以下代码
        binding.btnAddPhoto.setOnClickListener {
            // 启动相机或相册
            val options = arrayOf("Take Photo", "Choose from Gallery", "Cancel")
            val builder = AlertDialog.Builder(this)
            builder.setItems(options) { dialog, item ->
                when {
                    options[item] == "Take Photo" -> {
                        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
                    }
                    options[item] == "Choose from Gallery" -> {
                        val pickPhotoIntent =
                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        startActivityForResult(pickPhotoIntent, GALLERY_REQUEST_CODE)
                    }
                    options[item] == "Cancel" -> {
                        dialog.dismiss()
                    }
                }
            }
            builder.show()
        }
    }

    // 在 onActivityResult 方法中处理相机或相册返回的结果
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val imageBitmap = data.extras?.get("data") as Bitmap
            uploadImageToFirebase(imageBitmap)
        } else if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageUri = data.data
            val imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            uploadImageToFirebase(imageBitmap)
        }
    }

    // 添加一个新方法用于上传图片到Firebase Storage
    private fun uploadImageToFirebase(bitmap: Bitmap) {
        val storageReference =
            FirebaseStorage.getInstance().getReference("chat_images/${System.currentTimeMillis()}.jpg")

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = storageReference.putBytes(data)
        uploadTask.addOnSuccessListener {
            // 图片上传成功，获取下载URL，并发送到聊天
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                val imageUrl = uri.toString()
                val userId = intent.getStringExtra("userId")
                if (userId != null) {
                    // 发送图片消息
// 发送图片消息
                    sendMessage(firebaseUser!!.uid, userId, imageUrl, null)
                }
            }
        }.addOnFailureListener {
            // 处理上传失败
            Toast.makeText(this, "Image upload failed", Toast.LENGTH_SHORT).show()
        }
    }


    // 在 sendMessage 方法中，根据消息类型处理图片和文本消息
    private fun sendMessage(senderId: String, receiverId: String, imageUrl: String?, textMessage: String?) {
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
                binding.chatRecyclerView.scrollToPosition(chatList.size - 1)

                // 在成功发送消息后，调用 sendNotification 发送通知
                if (!imageUrl.isNullOrBlank() || !textMessage.isNullOrBlank()) {
                    val notificationData = NotificationData(
                        title = "New Message",
                        message = if (!imageUrl.isNullOrBlank()) "You received an image." else textMessage!!,
                        messageType = if (!imageUrl.isNullOrBlank()) "image" else "text"
                    )
                    val pushNotification = PushNotification(data = notificationData, to = "receiver_token")
                    sendNotification(pushNotification)

                    //ADD 對方的ID去users/uid/friendList的array裏面
                    addFriendToFriendList(senderId, receiverId)

                    addFriendToFriendList(receiverId, senderId)
                }
            }
    }

    private fun addFriendToFriendList(userId: String, friendId: String) {
        val db = FirebaseFirestore.getInstance()

        // 获取当前用户的文档引用
        val currentUserDocRef = db.collection("users").document(userId)

        // 使用 FieldValue 类的 arrayUnion 方法来将新的朋友 ID 添加到 friendList 数组中
        currentUserDocRef.update("friendList", FieldValue.arrayUnion(friendId))
            .addOnSuccessListener {
                Log.d("AddFriend", "Friend added successfully to friendList.")
            }
            .addOnFailureListener { e ->
                Log.e("AddFriend", "Error adding friend to friendList: $e")
            }
    }

    fun readMessage(senderId: String, receiverId: String) {
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Chat")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                chatList.clear()
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val chat = dataSnapShot.getValue(Chat::class.java)

                    if (chat!!.senderId.equals(senderId) && chat!!.receiverId.equals(receiverId) ||
                        chat!!.senderId.equals(receiverId) && chat!!.receiverId.equals(senderId)
                    ) {
                        chatList.add(chat)
                    }
                }

                val chatAdapter = ChatAdapter(this@ChatActivity, chatList)
                binding.chatRecyclerView.adapter = chatAdapter

                // 檢查是否有消息，如果有，滾動到最後一條消息
                if (chatList.isNotEmpty()) {
                    binding.chatRecyclerView.scrollToPosition(chatList.size - 1)
                }

            }
        })
    }
    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if (response.isSuccessful) {
                Log.d("TAG", "Notification sent successfully!")
            } else {
                Log.e("TAG", "Notification sending failed: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("TAG", "Notification sending failed: ${e.message}")
        }
    }



}
