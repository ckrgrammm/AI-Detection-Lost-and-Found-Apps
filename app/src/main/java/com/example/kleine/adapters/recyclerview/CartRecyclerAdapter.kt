package com.example.kleine.adapters.recyclerview

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kleine.databinding.CartItemBinding
import com.example.kleine.model.CartProduct
import com.example.kleine.util.Constants.Companion.CART_FLAG

class CartRecyclerAdapter(
    private val itemFlag: String = CART_FLAG
) : RecyclerView.Adapter<CartRecyclerAdapter.CartRecyclerAdapterViewHolder>() {

    var onPlusClick: ((CartProduct) -> Unit)? = null
    var onMinusesClick: ((CartProduct) -> Unit)? = null
    var onItemClick: ((CartProduct) -> Unit)? = null

    inner class CartRecyclerAdapterViewHolder(val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<CartProduct>() {
        override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartRecyclerAdapterViewHolder {
        return CartRecyclerAdapterViewHolder(
            CartItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartRecyclerAdapterViewHolder, position: Int) {
        val product = differ.currentList[position]

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}