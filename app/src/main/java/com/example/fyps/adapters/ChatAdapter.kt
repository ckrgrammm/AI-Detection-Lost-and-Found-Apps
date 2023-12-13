package com.example.fyps.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fyps.R
import com.example.fyps.model.Chat
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView

class ChatAdapter(private val context: Context, private val chatList: ArrayList<Chat>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    private val MESSAGE_TYPE_LEFT = 0
    private val MESSAGE_TYPE_RIGHT = 1
    private var lastPosition: Int = -1
    var firebaseUser: FirebaseUser? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == MESSAGE_TYPE_RIGHT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_right, parent, false)
            ViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_left, parent, false)
            ViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val chat = chatList[position]

        if (position != lastPosition) {
            if (chat.messageType == "image") {
                val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(chat.message)
                storageReference.downloadUrl.addOnSuccessListener { uri ->
                    val imageUrl = uri.toString()

                    if (getItemViewType(position) == MESSAGE_TYPE_RIGHT) {
                        // 处理发送方的图片消息
                        Glide.with(context)
                            .load(imageUrl)
                            .thumbnail(0.25f)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.imgMessageItemRight)

                        holder.imgMessageItemRight.visibility = View.VISIBLE
                        holder.tvMessage.visibility = View.GONE
                    } else {
                        // 处理接收方的图片消息
                        Glide.with(context)
                            .load(imageUrl)
                            .thumbnail(0.25f)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.imgMessageItemLeft)

                        holder.imgMessageItemLeft.visibility = View.VISIBLE
                        holder.tvMessage.visibility = View.GONE
                    }
                }.addOnFailureListener { e ->
                    Log.e("ChatAdapter", "Error fetching image from Firebase Storage: $e")
                }
            } else {
                // 处理文本消息
                holder.txtUserName.text = chat.message

                if (getItemViewType(position) == MESSAGE_TYPE_RIGHT) {
                    holder.imgMessageItemRight.visibility = View.GONE
                    holder.tvMessage.visibility = View.VISIBLE
                } else {
                    holder.imgMessageItemLeft.visibility = View.GONE
                    holder.tvMessage.visibility = View.VISIBLE
                }
            }

            val userImageReference =
                FirebaseFirestore.getInstance().collection("users").document(chat.senderId)

            userImageReference.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val userImagePath = documentSnapshot.getString("imagePath")

                    Glide.with(context).load(userImagePath).placeholder(R.drawable.user)
                        .into(holder.imgUser)
                } else {
                    Log.d("ChatAdapter", "User document not found")
                }
            }.addOnFailureListener { e ->
                Log.e("ChatAdapter", "Error fetching user document: $e")
            }

            lastPosition = position
        }
    }

    override fun getItemViewType(position: Int): Int {
        firebaseUser = FirebaseAuth.getInstance().currentUser
        return if (chatList[position].senderId == firebaseUser!!.uid) {
            MESSAGE_TYPE_RIGHT
        } else {
            MESSAGE_TYPE_LEFT
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtUserName: TextView = view.findViewById(R.id.tvMessage)
        val imgUser: CircleImageView = view.findViewById(R.id.userImage)
        val tvMessage = view.findViewById<TextView>(R.id.tvMessage)
        val imgMessageItemRight = view.findViewById<ImageView>(R.id.imgMessageItemRight)
        val imgMessageItemLeft = view.findViewById<ImageView>(R.id.imgMessageItemLeft)
    }
}
