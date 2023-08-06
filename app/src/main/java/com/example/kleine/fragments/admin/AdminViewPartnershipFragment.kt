package com.example.kleine.fragments.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kleine.BuildConfig
import com.example.kleine.R
import com.example.kleine.activities.LunchActivity
import com.example.kleine.activities.ShoppingActivity
import com.example.kleine.databinding.FragmentAdminDashboardBinding
import com.example.kleine.databinding.FragmentAdminViewPartnershipBinding
import com.example.kleine.databinding.FragmentJoinPartnerBinding
import com.example.kleine.databinding.FragmentProfileBinding
import com.example.kleine.databinding.FragmentReplyCommentBinding
import com.example.kleine.databinding.FragmentViewPartnershipBinding
import com.example.kleine.model.User
import com.example.kleine.resource.Resource
import com.example.kleine.util.Constants.Companion.UPDATE_ADDRESS_FLAG
import com.example.kleine.viewmodel.shopping.ShoppingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class AdminViewPartnershipFragment : Fragment() {
    val TAG = "AdminViewPartnershipFragment"
    private lateinit var binding: FragmentAdminViewPartnershipBinding
    private lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as ShoppingActivity).viewModel
        viewModel.getUser()
    }

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminViewPartnershipBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewRequestClick()

    }

    private fun onViewRequestClick() {
        binding.viewRequest.setOnClickListener {
            findNavController().navigate(R.id.action_adminViewPartnershipFragment_to_adminViewPartnershipRequestFragment)
        }
    }



}