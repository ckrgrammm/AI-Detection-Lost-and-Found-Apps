package com.example.kleine.fragments.shopping

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kleine.BuildConfig
import com.example.kleine.R
import com.example.kleine.activities.LunchActivity
import com.example.kleine.activities.ShoppingActivity
import com.example.kleine.adapters.recyclerview.AllOrdersAdapter
import com.example.kleine.adapters.recyclerview.MaterialAdapter
import com.example.kleine.databinding.FragmentProfileBinding
import com.example.kleine.model.Enrollment
import com.example.kleine.model.Material
import com.example.kleine.model.User
import com.example.kleine.resource.Resource
import com.example.kleine.util.Constants.Companion.UPDATE_ADDRESS_FLAG
import com.example.kleine.viewmodel.shopping.ShoppingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment() {
    val TAG = "ProfileFragment"
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ShoppingViewModel
    private lateinit var materialAdapter: MaterialAdapter

    private lateinit var enrolledMaterials: List<Material>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as ShoppingActivity).viewModel
        viewModel.getUser()
    }
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
                        Log.d(TAG, "User Status: $status")
                        if (status == "ADMINS") {
                            // Admin user, show the fragment
                            binding.adminOrders.visibility = View.VISIBLE
                            binding.linearAdmin.visibility = View.VISIBLE
                            binding.linearJoinPartnership.visibility = View.GONE
                        }else if(status == "PARTNERS"){
                            binding.adminOrders.visibility = View.GONE
                            binding.linearAdmin.visibility = View.GONE
                            binding.linearJoinPartnership.visibility = View.GONE
                            binding.linearViewPartnership.visibility = View.VISIBLE
                        }else{
                            binding.linearJoinPartnership.visibility = View.VISIBLE
                            binding.linearViewPartnership.visibility = View.GONE
                            binding.adminOrders.visibility = View.GONE
                            binding.linearAdmin.visibility = View.GONE
                        }
                    }
                }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        materialAdapter = MaterialAdapter()

        onHomeClick()
        onLogoutClick()
        onBillingAndAddressesClick()
        onProfileClick()
        onAllOrderClick()
        onTrackOrderClick()
        onJoinPartnershipCLick()
        onViewPartnershipClick()
        onAdminClick()
        onHelpClick()

        observeProfile()
        binding.tvVersionCode.text =
            "${resources.getText(R.string.g_version)} ${BuildConfig.VERSION_NAME}"


        onTempCommentClick()
        onTempRatingClick()
    }

    private fun onTempCommentClick() {
        binding.linearComment.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("documentId", "5VlLkBQaULeJlawqN8Rq")
            findNavController().navigate(R.id.action_profileFragment_to_tempCommentFragment, bundle)
        }
    }
    private fun onTempRatingClick() {
        binding.linearRating.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("documentId", "5VlLkBQaULeJlawqN8Rq")
            findNavController().navigate(R.id.action_profileFragment_to_tempRatingFragment, bundle)
        }
    }
    private fun onHelpClick() {
        binding.linearHelp.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_helpFragment)
        }
    }

    private fun onAdminClick() {
        binding.linearAdmin.setOnClickListener {
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

    private fun onTrackOrderClick() {
        binding.linearTrackOrder.setOnClickListener {
            val snackBar = requireActivity().findViewById<CoordinatorLayout>(R.id.snackBar_coordinator)
            Snackbar.make(snackBar,resources.getText(R.string.g_coming_soon),Snackbar.LENGTH_SHORT).show()
        }
    }



    private fun onAllOrderClick() {
        binding.allOrders.setOnClickListener {
            fetchUserEnrollments()
            findNavController().navigate(R.id.action_profileFragment_to_allOrdersFragment)
        }
    }



    private fun fetchUserEnrollments() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("enrollments")
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val enrollments = querySnapshot.documents.mapNotNull { document ->
                        document.toObject(Enrollment::class.java)
                    }
                    val materialIds = enrollments.map { it.materialId }
                    fetchMaterialsInBatches(materialIds)
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error fetching user enrollments", exception)
                    // Handle the error appropriately
                }
        } else {
            // Handle the case where the user is not logged in
        }
    }

    private fun fetchMaterialsInBatches(materialIds: List<String>) {
        val batchLimit = 10
        val batches = (materialIds.size + batchLimit - 1) / batchLimit
        val firestore = FirebaseFirestore.getInstance()

        for (i in 0 until batches) {
            val startIndex = i * batchLimit
            val endIndex = minOf((i + 1) * batchLimit, materialIds.size)
            val batchIds = materialIds.subList(startIndex, endIndex)

            firestore.collection("Materials")
                .whereIn("id", batchIds)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val materials = querySnapshot.documents.mapNotNull { document ->
                        document.toObject(Material::class.java)
                    }
                    displayMaterials(materials)
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error fetching materials", exception)
                    // Handle the error appropriately
                }
        }
    }




    private fun displayMaterials(materials: List<Material>) {
        // Update your UI with the fetched materials
        // For example, update the RecyclerView Adapter with the materials
        materialAdapter.differ.submitList(materials)
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