package com.example.fyps.fragments.admin

import UserAdapter
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fyps.R
import com.example.fyps.databinding.FragmentAdminListBinding
import com.example.fyps.viewmodel.admin.AdminListViewModel
import com.example.fyps.model.User

class AdminListFragment : Fragment() {

    private lateinit var binding: FragmentAdminListBinding
    private lateinit var viewModel: AdminListViewModel
    private lateinit var userAdapter: UserAdapter
    lateinit var selectedUser: User
    private lateinit var dialog: Dialog



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AdminListViewModel::class.java)

        initRecyclerView()
        observeAdminUsers()

        binding.deleteImg.setOnClickListener {
            Toast.makeText(requireContext(), "Long-press to delete an admin.", Toast.LENGTH_SHORT)
                .show()
        }

        binding.addUserBtn.setOnClickListener {
            val action = AdminListFragmentDirections.actionAdminListFragmentToAdminFormFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun initRecyclerView() {
        userAdapter = UserAdapter(requireContext(), object : UserAdapter.UserClickListener {
            override fun onItemClicked(user: User) {
                // Handle the item click event
                Toast.makeText(
                    requireContext(),
                    "Clicked on: ${user.firstName}",
                    Toast.LENGTH_SHORT
                ).show()
                // Navigate or perform other actions based on the clicked item
            }
        })

        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
        }
    }


//    override fun onLongItemClicked(user: User, cardView: CardView) {
//        selectedUser = user
//        popUpDisplay(cardView)
//    }
//
//    private fun popUpDisplay(cardView: CardView) {
//
//        val popup = PopupMenu(requireContext(), cardView)
//        popup.setOnMenuItemClickListener(this)
//        popup.inflate(R.menu.pop_up_menu)
//        popup.show()
//
//    }
//
//    private fun observeUserDeletion() {
//        viewModel.getDeleteUserObservable()
//            .observe(viewLifecycleOwner, Observer<User?> { deletedUser ->
//                if (deletedUser == null) {
//                    Toast.makeText(requireContext(), "Cannot Delete User", Toast.LENGTH_SHORT)
//                        .show()
//                } else {
//                    showSuccessDialog()
//                    viewModel.fetchAdminUsers()
//
//                    // You can perform any other actions needed after successful deletion here
//                }
//            })
//    }


    private fun showSuccessDialog() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog_success)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false) // Optional
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        dialog.window?.attributes?.windowAnimations =
            R.style.DialogAnimation // Setting the animations to dialog

        val okay: Button = dialog.findViewById(R.id.btn_okay)
        val cancel: Button = dialog.findViewById(R.id.btn_cancel)

        okay.setOnClickListener {

            dialog.dismiss()

        }

        cancel.setOnClickListener {
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        dialog.show() // Showing the dialog here

    }
        private fun observeAdminUsers() {
            viewModel.adminUsers.observe(viewLifecycleOwner) { adminUsers ->
                userAdapter.setData(adminUsers)
            }
            viewModel.fetchAdminUsers()
        }
    }
