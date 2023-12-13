package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.fyps.adapters.UserAdapter
import com.example.fyps.adapters.recyclerview.CartRecyclerAdapter
import com.example.fyps.databinding.ActivityUsersBinding
import com.example.fyps.viewmodel.shopping.cart.CartViewModel


private const val TAG = "ChatFragment"
class chatFragment : Fragment() {
    private lateinit var binding: ActivityUsersBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("tag","abc")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityUsersBinding.inflate(inflater)
        return binding.root
        Log.d("tag","abc")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}