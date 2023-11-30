package com.example.fyps.fragments.shopping

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fyps.BuildConfig
import com.example.fyps.R
import com.example.fyps.activities.LunchActivity
import com.example.fyps.activities.ShoppingActivity
import com.example.fyps.adapters.recyclerview.MaterialAdapter
import com.example.fyps.databinding.FragmentProfileBinding
import com.example.fyps.model.Material
import com.example.fyps.model.User
import com.example.fyps.resource.Resource
import com.example.fyps.util.Constants.Companion.UPDATE_ADDRESS_FLAG
import com.example.fyps.viewmodel.shopping.ShoppingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment() {
    val TAG = "ProfileFragment"
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ShoppingViewModel
    private lateinit var materialAdapter: MaterialAdapter

    private lateinit var enrolledMaterials: List<Material>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val status = document.getString("status")
                        handleUserStatus(status)
                    }
                }
        }

        return binding.root

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as ShoppingActivity).viewModel
        viewModel.getUser()
    }




    private fun handleUserStatus(status: String?) {
        when (status) {
            "ADMINS" -> {
//                binding.adminOrders.visibility = View.VISIBLE
                binding.linearDashboard.visibility = View.VISIBLE
                binding.linearJoinPartnership.visibility = View.GONE
            }
            "PARTNERS" -> {
//                binding.adminOrders.visibility = View.GONE
                binding.linearDashboard.visibility = View.GONE
                binding.linearJoinPartnership.visibility = View.GONE
                binding.linearViewPartnership.visibility = View.VISIBLE
            }
            else -> {
                binding.linearJoinPartnership.visibility = View.VISIBLE
                binding.linearViewPartnership.visibility = View.GONE
                binding.linearDashboard.visibility = View.GONE
//                binding.linearAdmin.visibility = View.GONE
            }
        }

        // Call after handling user status to avoid overriding visibility
        checkPartnershipAndDisplaySettings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        materialAdapter = MaterialAdapter()



        onHomeClick()
        onLogoutClick()
        onBillingAndAddressesClick()
        onProfileClick()
        onAllOrderClick()
        onJoinPartnershipCLick()
        onViewPartnershipClick()
        onAdminClick()
        onHelpClick()

        observeProfile()
        binding.tvVersionCode.text =
            "${resources.getText(R.string.g_version)} ${BuildConfig.VERSION_NAME}"


        checkPartnershipAndDisplaySettings()


        onPassedQuizzesClick()
        onRewardClick()
    }


    private fun checkPartnershipAndDisplaySettings() {
        val currentUserID = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()

        if (currentUserID != null) {
            Log.d(TAG, "Checking for Materials with partnershipsID: $currentUserID")
            db.collection("Materials")
                .whereEqualTo("partnershipsID", currentUserID)
                .limit(1)
                .get()
                .addOnSuccessListener { documents ->
                    if (!documents.isEmpty) {
                        Log.d(TAG, "Document with partnershipsID found")
                        binding.linearItemSetting.visibility = View.VISIBLE
                        binding.itemSetting.visibility = View.VISIBLE

                    } else {
                        Log.d(TAG, "No document with partnershipsID found")
                        binding.linearItemSetting.visibility = View.GONE
                        binding.itemSetting.visibility = View.GONE

                    }
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error checking for partnership ID: ", e)
                    binding.linearItemSetting.visibility = View.GONE
                    binding.itemSetting.visibility = View.GONE

                }
        } else {
            Log.d(TAG, "No user ID found")
            binding.linearItemSetting.visibility = View.GONE
            binding.itemSetting.visibility = View.GONE

        }
    }



    private fun onPassedQuizzesClick() {
        binding.linearPassedQuizzes.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_passedQuizzesFragment)
        }
    }

    private fun onRewardClick() {
        binding.linearReward.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_rewardFragment)
        }
    }
    private fun onHelpClick() {
        binding.linearHelp.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_helpFragment)
        }
    }

    private fun onAdminClick() {
        binding.linearDashboard.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_adminDashboardFragment)
        }
    }


    private fun onJoinPartnershipCLick() {
        binding.linearJoinPartnership.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_joinPartnerFragment)
        }
    }
    private fun onViewPartnershipClick() {
        binding.linearViewPartnership.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_viewPartnershipFragment)
        }
    }


    private fun onAllOrderClick() {
        binding.allOrders.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_allOrdersFragment)
        }
    }


    private fun onProfileClick() {
        binding.constraintProfile.setOnClickListener {
            user?.let {
                val bundle = Bundle()
                bundle.putParcelable("user",user)
                findNavController().navigate(R.id.action_profileFragment_to_editUserInformation,bundle)
            }
        }


    }

    var user: User?=null
    private fun observeProfile() {
        viewModel.profile.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading()
                    return@observe
                }

                is Resource.Success -> {
                    hideLoading()
                    val user = response.data
                    this.user = user
                    binding.apply {
                        tvUserName.text = user?.firstName + " " + user?.lastName
                        Glide.with(requireView()).load(user?.imagePath)
                            .error(R.drawable.ic_default_profile_picture).into(binding.imgUser)
                    }
                    return@observe
                }

                is Resource.Error -> {
                    hideLoading()
                    Toast.makeText(
                        activity,
                        resources.getText(R.string.error_occurred),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e(TAG, response.message.toString())
                    return@observe
                }
            }
        }
    }

    private fun hideLoading() {
        binding.apply {
            binding.progressbarSettings.visibility = View.GONE
            constraintParnet.visibility = View.VISIBLE
        }
    }

    private fun showLoading() {
        binding.apply {
            binding.progressbarSettings.visibility = View.VISIBLE
            constraintParnet.visibility = View.INVISIBLE
        }
    }

    private fun onBillingAndAddressesClick() {
        binding.linearBilling.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("clickFlag", UPDATE_ADDRESS_FLAG)
            findNavController().navigate(R.id.action_profileFragment_to_billingFragment, bundle)
        }
    }

    private fun onLogoutClick() {

        binding.linearOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context, LunchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun onHomeClick() {
        val btm = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        btm?.menu?.getItem(0)?.setOnMenuItemClickListener {
            activity?.onBackPressed()
            true
        }
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation?.visibility = View.VISIBLE
    }


}