package com.example.kleine.fragments.reward

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kleine.R
import com.example.kleine.adapters.recyclerview.RedeemRewardAdapter
import com.example.kleine.databinding.FragmentRedeemRewardBinding
import com.example.kleine.model.Reward
import com.example.kleine.resource.NetworkReceiver
import com.example.kleine.viewmodel.reward.RedeemRewardViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RedeemRewardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RedeemRewardFragment : Fragment() {
    private lateinit var binding: FragmentRedeemRewardBinding
    private lateinit var viewModel: RedeemRewardViewModel
    private var isNetworkAvailable: Boolean = false
    private var tryAgainButtonClicked = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_redeem_reward, container,false)

        viewModel = ViewModelProvider(this).get(RedeemRewardViewModel::class.java)

        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.rewards.observe(viewLifecycleOwner, Observer { rewards ->
            if (isNetworkAvailable) {
                val adapter = RedeemRewardAdapter(rewards) { selectedReward ->
                    handleRedemption(selectedReward)
                }
                binding.voucherRecyclerView.adapter = adapter
                binding.voucherRecyclerView.visibility = View.VISIBLE
                binding.textViewTotalPoints.visibility = View.VISIBLE
                binding.labelShippingVoucherTextView.visibility = View.VISIBLE
                binding.noInternetLayout.visibility = View.GONE
            } else {
                binding.voucherRecyclerView.visibility = View.GONE
                binding.textViewTotalPoints.visibility = View.GONE
                binding.labelShippingVoucherTextView.visibility = View.GONE
                binding.noInternetLayout.visibility = View.VISIBLE
            }
        })

        binding.tryAgainButton.setOnClickListener {
            tryAgainButtonClicked = true
            checkAndUpdateNetworkAvailability()
            viewModel.loadRewards()
        }

        viewModel.userPoints.observe(viewLifecycleOwner, Observer { points ->
            binding.textViewTotalPoints.text = getString(R.string.total_points, points)
        })

        viewModel.redemptionSuccessful.observe(viewLifecycleOwner, Observer { isSuccessful ->
            if (isSuccessful) {
                Toast.makeText(context, "Reward redeemed successfully!", Toast.LENGTH_SHORT).show()
                viewModel.redemptionSuccessful.value = false // Reset the value to prevent showing the Toast again on configuration changes
            }
        })

        viewModel.noEnoughPoints.observe(viewLifecycleOwner, Observer { isSuccessful ->
            if (isSuccessful) {
                Toast.makeText(context, "No Enough Points!", Toast.LENGTH_SHORT).show()
                viewModel.noEnoughPoints.value = false // Reset the value to prevent showing the Toast again on configuration changes
            }
        })

        return binding.root
    }

    private fun handleRedemption(selectedReward: Reward) {
        // Check network availability before redeeming
        checkAndUpdateNetworkAvailability()

        if (!isNetworkAvailable) {
            Toast.makeText(context, "No internet connection. Please try again.", Toast.LENGTH_SHORT).show()
            return
        }

        val userPoints = viewModel.userPoints.value ?: 0
        if (userPoints >= selectedReward.rewardPoints) {
            viewModel.redeemReward(selectedReward)
        } else {
            Toast.makeText(context, "You do not have enough points to redeem this reward.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun updateLayoutVisibility() {
        if (isNetworkAvailable) {
            binding.voucherRecyclerView.visibility = View.VISIBLE
            binding.textViewTotalPoints.visibility = View.VISIBLE
            binding.labelShippingVoucherTextView.visibility = View.VISIBLE
            binding.noInternetLayout.visibility = View.GONE
        } else {
            binding.voucherRecyclerView.visibility = View.GONE
            binding.textViewTotalPoints.visibility = View.GONE
            binding.labelShippingVoucherTextView.visibility = View.GONE
            binding.noInternetLayout.visibility = View.VISIBLE
        }
    }

    private fun checkAndUpdateNetworkAvailability() {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo

        isNetworkAvailable = activeNetwork?.isConnectedOrConnecting == true
        if (tryAgainButtonClicked) {
            updateLayoutVisibility()
            tryAgainButtonClicked = false
        }
    }

    // Receiver callbacks
    private val networkReceiver = NetworkReceiver(
        onNetworkAvailable = {
            // Update isNetworkAvailable but don't call updateLayoutVisibility() directly.
            isNetworkAvailable = true
        },
        onNetworkUnavailable = {
            // Update isNetworkAvailable but don't call updateLayoutVisibility() directly.
            isNetworkAvailable = false
        }
    )

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        requireContext().registerReceiver(networkReceiver, filter)
        checkAndUpdateNetworkAvailability()
        updateLayoutVisibility()
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(networkReceiver)
    }

}