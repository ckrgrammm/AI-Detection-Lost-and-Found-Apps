package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.adapters.UserAdapter
import com.example.fyps.databinding.ActivityUsersBinding
import com.example.fyps.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ChatFragment : Fragment() {
    private lateinit var binding: ActivityUsersBinding
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var userList: ArrayList<User>  // User 是你的模型類型

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityUsersBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 初始化 RecyclerView、Adapter 和數據列表
        userRecyclerView = binding.userRecyclerView
        userList = ArrayList()
        userAdapter = UserAdapter(requireContext(), userList)

        // 設置 RecyclerView 的布局管理器和適配器
        userRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        userRecyclerView.adapter = userAdapter

        // 加載數據
        loadFriendListData()

        // 加載登入用戶的頭像
        loadUserProfileImage()
    }

    private fun loadUserProfileImage() {
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
    }


    private fun loadFriendListData() {
        // 在登錄成功後，獲取當前用戶的 UID
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        // 如果 UID 不為空，則使用 UID 作為 documentID 進行後續的數據操作
        if (uid != null) {
            // 這裡可以使用 uid 進行其他操作，例如獲取 documentID
            // 這裡僅為示例，實際上需要替換為你的代碼
            Log.d("hi",uid)
            val firestore = FirebaseFirestore.getInstance()
            val usersCollection = firestore.collection("users")

            loadFriendListData(uid)
        } else {
            // UID 為空，可能需要處理此情況
        }
    }

    private fun loadFriendListData(docID: String) {
        // 現在你有了當前用戶的 documentID，你可以使用它來加載朋友列表數據
        val firestore = FirebaseFirestore.getInstance()
        val userDocRef = firestore.collection("users").document(docID)

        userDocRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val user = documentSnapshot.toObject(User::class.java)

                // 檢查 user 是否為空，以及 friendList 是否為空
                if (user != null && user.friendList != null) {
                    val friendList = user.friendList
                    Log.d("hi", friendList.toString())

                    // 調用 displayUsers 方法來更新 UI
                    if (friendList != null) {
                        displayUsers(friendList)
                    }
                }
            }
        }
    }

    private fun displayUsers(friendList: ArrayList<String>) {
        val firestore = FirebaseFirestore.getInstance()
        val usersCollection = firestore.collection("users")
        Log.d("hi2", friendList.toString())

        val newUsersList = ArrayList<User>()

        for (friendId in friendList) {
            val userDocRef = usersCollection.document(friendId)
            userDocRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val user = documentSnapshot.toObject(User::class.java)
                    if (user != null) {
                        newUsersList.add(user)
                    }
                    // 檢查是否所有用戶都添加完成，然後更新 UI
                    if (newUsersList.size == friendList.size) {
                        // 清除舊的用戶數據，添加新的數據
                        userList.clear()
                        userList.addAll(newUsersList)
                        Log.d("hiii3", userList.toString())
                        // 通知適配器數據已更改
                        userAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}