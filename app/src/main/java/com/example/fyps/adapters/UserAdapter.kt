package com.example.fyps.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.activities.ChatActivity
import com.example.fyps.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
/*import com.codingwithme.firebasechat.activity.ChatActivity
import com.example.fyps.firebasechat.model.User*/
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private val context: Context, private val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    // ViewHolder 类型需要指定
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtUserName: TextView = view.findViewById(R.id.firstName)
        val imgUser: CircleImageView = view.findViewById(R.id.userImage)
        val layoutUser: LinearLayout = view.findViewById(R.id.layoutUser)
        /*
                val imgProfile: ImageView = view.findViewById(R.id.imgProfile)  // 添加这一行
        */
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        Log.d("UserAdapter", "Binding user: $user")

        holder.txtUserName.text = user.firstName
        Glide.with(context).load(user.imagePath).placeholder(R.drawable.address_image).into(holder.imgUser)

        val currentUser = FirebaseAuth.getInstance().currentUser
        var userId: String? = null

        if (currentUser != null) {
            userId = currentUser.uid
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    /*if (document.exists()) {
                        val currentUserImagePath = document.getString("imagePath")
                        // 使用 Glide 加载当前登录用户的头像到 imgProfile
                        Glide.with(context)
                            .load(currentUserImagePath)
                            .placeholder(R.drawable.address_image) // 占位符图片，可选
                            .into(holder.imgProfile)
                    }*/
                }
        }

        // 使用 userId 来启动 ChatActivity
        holder.layoutUser.setOnClickListener {
            val selectedUser = userList[position]

            // 获取被选中用户的 firstName 和 imagePath
            val selectedUserName = selectedUser.firstName
            val selectedUserImagePath = selectedUser.imagePath

            // 使用 Firestore 查询获取被选中用户的 documentID
            val db = FirebaseFirestore.getInstance()
            db.collection("users")
                .whereEqualTo("firstName", selectedUserName)
                .whereEqualTo("imagePath", selectedUserImagePath)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        // 获取被选中用户的 documentID
                        val selectedUserId = document.id

                        // 启动 ChatActivity 并传递被选中用户的信息
                        val intent = Intent(context, ChatActivity::class.java)
                        intent.putExtra("userId", selectedUserId)
                        intent.putExtra("userName", selectedUserName)
                        intent.putExtra("userImagePath", selectedUserImagePath)
                        context.startActivity(intent)
                    }
                }
                .addOnFailureListener { exception ->
                    // 处理查询失败的情况
                    Log.e("FirestoreQuery", "Error getting documents: $exception")
                }
        }


    }
}