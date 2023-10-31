package com.example.fyps.fragments.admin

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fyps.R
import com.example.fyps.activities.ShoppingActivity
import com.example.fyps.databinding.FragmentAdminDashboardBinding
import com.example.fyps.viewmodel.shopping.ShoppingViewModel


class AdminDashboardFragment() : Fragment(), Parcelable {
    val TAG = "AdminDashboardFragment"
    private lateinit var binding: FragmentAdminDashboardBinding
    private lateinit var viewModel: ShoppingViewModel

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as ShoppingActivity).viewModel
        viewModel.getUser()
    }

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewPartnershipDataClick()
        onViewRewardDataClick()

    }

    private fun onViewPartnershipDataClick() {
        binding.viewPartnershipData.setOnClickListener {
            findNavController().navigate(R.id.action_adminDashboardFragment_to_adminViewPartnershipFragment)
        }
    }

    private fun onViewRewardDataClick() {
        binding.viewRewardData.setOnClickListener {
            findNavController().navigate(R.id.action_adminDashboardFragment_to_adminViewRewardFragment)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AdminDashboardFragment> {
        override fun createFromParcel(parcel: Parcel): AdminDashboardFragment {
            return AdminDashboardFragment(parcel)
        }

        override fun newArray(size: Int): Array<AdminDashboardFragment?> {
            return arrayOfNulls(size)
        }
    }


}