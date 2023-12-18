package com.example.fyps.adapters.recyclerview


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fyps.R
import com.example.fyps.model.NotificationItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import de.hdodenhof.circleimageview.CircleImageView

class NotificationAdapter(private val notificationList: MutableList<String>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notificationList[position]

        if (notification.isNotEmpty()) {
            holder.messageTextView.text = notification
        } else {
            holder.messageTextView.text = "You have no notification!"
        }
    }

    // 添加一個方法用於刪除項目
    private fun removeItem(position: Int) {
        notificationList.removeAt(position)
        notifyItemRemoved(position)
    }
    override fun getItemCount(): Int {
        return notificationList.size
    }

    // Method to update data
    fun updateData(newData: List<String>) {
        notificationList.clear()
        notificationList.addAll(newData)
        notifyDataSetChanged()
    }

    /*private fun loadUserProfileImage(userImage: ImageView) {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val userId = currentUser.uid
            val db = FirebaseFirestore.getInstance()

            db.collection("users").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val imagePath = document.getString("imagePath")

                        // Using Glide to load the image into userImage
                        Glide.with(userImage)
                            .load(imagePath)
                            .placeholder(R.drawable.address_image)
                            .into(userImage)
                    }
                }
        }
    }*/
}