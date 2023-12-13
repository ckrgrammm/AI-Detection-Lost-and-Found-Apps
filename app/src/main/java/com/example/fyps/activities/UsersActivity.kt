package com.example.fyps.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.adapters.UserAdapter
import com.example.fyps.firebase.FirebaseService
import com.example.fyps.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import de.hdodenhof.circleimageview.CircleImageView

class UsersActivity : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var userList: ArrayList<User>
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FCM", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                Log.w("FCM", "yeah")
                // Get new Instance ID token
                val token = task.result?.token

                // Log and handle the token as needed
                Log.d("FCM", "Token: $token")
            })
        /*FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            FirebaseService.token = it.token
        }*/

        userRecyclerView = findViewById(R.id.userRecyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        userList = ArrayList()
        userAdapter = UserAdapter(this, userList)

        userRecyclerView.adapter = userAdapter

        // 初始化 Firestore 引用
        firestore = FirebaseFirestore.getInstance()

        // 获取当前用户的 UID
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        var userid = firebase.uid
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/$userid")

        // 获取当前用户照片的 ImageView
        val imgProfile = findViewById<CircleImageView>(R.id.imgProfile)

        // 从 Firestore 中获取当前用户的数据
        val currentUserQuery: DocumentReference = firestore.collection("users").document(uid!!)
        currentUserQuery.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val currentUser = document.toObject(User::class.java)
                    if (currentUser != null) {
                        // 在这里获取当前用户的照片 URL
                        val userPhotoUrl = currentUser.imagePath

                        // 使用 Glide 加载当前用户的照片
                        Glide.with(this)
                            .load(userPhotoUrl)
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(imgProfile)
                    }
                }
            }

        // 从 Firestore 中获取用户数据
        val query: Query = firestore.collection("users")
        query.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                // 处理异常
                return@addSnapshotListener
            }

            userList.clear()

            if (snapshot != null) {
                for (document in snapshot.documents) {
                    val user = document.toObject(User::class.java)
                    if (user != null) {
                        userList.add(user)
                    }
                }
            }

            userAdapter.notifyDataSetChanged()
        }
    }
}
