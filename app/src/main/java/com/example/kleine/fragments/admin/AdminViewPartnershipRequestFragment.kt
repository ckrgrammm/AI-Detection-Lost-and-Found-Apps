package com.example.kleine.fragments.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.activities.LunchActivity
import com.example.kleine.activities.ShoppingActivity
import com.example.kleine.databinding.FragmentAdminDashboardBinding
import com.example.kleine.databinding.FragmentAdminViewPartnershipRequestBinding
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


class AdminViewPartnershipRequestFragment : Fragment() {
    val TAG = "AdminViewPartnershipRequestFragment"
    private lateinit var binding: FragmentAdminViewPartnershipRequestBinding
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
        binding = FragmentAdminViewPartnershipRequestBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val includedLayout = view.findViewById<LinearLayout>(R.id.partnershipData)
        val approveButton = includedLayout.findViewById<Button>(R.id.btnApprove)
        val rejectButton = includedLayout.findViewById<Button>(R.id.btnReject)

        approveButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Approve Partnership")
                .setMessage("Are you sure you want to approve this user as partner?")
                .setPositiveButton(android.R.string.yes) { dialog, which ->
                    //TODO: Implement your logic to disable the material here.
                }
                .setNegativeButton(android.R.string.no, null)
                .show()
        }

        rejectButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Reject Partnership")
                .setMessage("Are you sure you want to reject this user?")
                .setPositiveButton(android.R.string.yes) { dialog, which ->
                    //TODO: Implement your logic to disable the material here.
                }
                .setNegativeButton(android.R.string.no, null)
                .show()
        }

    }



}