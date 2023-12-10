import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fyps.R
import com.example.fyps.model.User

class UserAdapter(
    private val context: Context,
    private val listener: UserClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList = mutableListOf<User>()

    fun setData(arrData: List<User>) {
        userList = arrData.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycleview_customer, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.userName.text = user.firstName + " " + user.lastName
        holder.userEmail.text = user.email

        holder.detailButton.setOnClickListener {
            listener.onItemClicked(user)
        }
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userLayout: CardView = itemView.findViewById(R.id.customer_layout)
        val userName: TextView = itemView.findViewById(R.id.tv_customerName)
        val userEmail: TextView = itemView.findViewById(R.id.tv_customerEmail)
        val detailButton: ImageView = itemView.findViewById(R.id.cust_detail_btn)
    }

    interface UserClickListener {
        fun onItemClicked(user: User)
    }
}
