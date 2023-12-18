package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.adapters.recyclerview.NotificationAdapter
import com.example.fyps.databinding.ActivityNotificationBinding
import com.example.fyps.databinding.ActivityUsersBinding
import com.example.fyps.model.NotificationItem
import com.example.fyps.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NotificationFragment : Fragment() {
    private lateinit var binding: ActivityNotificationBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private val notificationList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 初始化适配器并设置给 RecyclerView
        notificationAdapter = NotificationAdapter(notificationList)
        recyclerView.adapter = notificationAdapter

        // Load data from Firebase Firestore
        loadDataFromFirestore()
        // 加載登入用戶的頭像
    }


    /*private fun loadUserProfileImage() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val imgProfile = binding.imgProfile

        if (currentUser != null) {
            val userId = currentUser.uid
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val imagePath = document.getString("imagePath")

                        // 使用 Glide 加載圖片到 imgProfile
                        Glide.with(requireContext())
                            .load(imagePath)
                            .placeholder(R.drawable.address_image) // 占位符圖片，可選
                            .into(imgProfile)
                    }
                }
        }
    }*/



    private fun loadDataFromFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid
        val userDocRef = userId?.let { firestore.collection("users").document(it) }

        if (userDocRef != null) {
            userDocRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val user = documentSnapshot.toObject(User::class.java)

                    // 檢查 user 是否為空，以及 friendList 是否為空
                    if (user != null && user.notificationList != null) {
                        val notificationList = user.notificationList
                        Log.d("hi", notificationList.toString())

                        // 調用 displayUsers 方法來更新 UI
                        if (notificationList != null) {
                            // 刷新 RecyclerView
                            notificationAdapter.updateData(notificationList)
                            Log.d("NotificationFragment", "NotificationList size: ${notificationList.size}")
                        }
                    }
                }
            }
        }
    }
}