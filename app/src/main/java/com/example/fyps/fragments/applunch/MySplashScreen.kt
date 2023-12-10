package com.example.fyps.fragments.applunch

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fyps.R
import com.example.fyps.activities.LunchActivity
import com.example.fyps.activities.ShoppingActivity
import com.example.fyps.databinding.FragemntSplashScreenBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

@SuppressLint("CustomSplashScreen")
class MySplashScreen : Fragment() {

    private val PLAY_SERVICES_RESOLUTION_REQUEST = 9000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragemntSplashScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(checkGooglePlayServices()){
            proceedWithAppLogic()
        } else {
            proceedWithAppLogic()// Handle the scenario where Google Play Services are not available
        }
    }


    private fun checkGooglePlayServices(): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(requireContext())
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                activity?.let { nonNullActivity ->
                    googleApiAvailability.getErrorDialog(nonNullActivity, status, PLAY_SERVICES_RESOLUTION_REQUEST)?.show()
                }
            } else {
                // Log or handle the scenario where the device is not supported
            }
            return false
        }
        return true
    }


    private fun proceedWithAppLogic() {
        val viewModel = (activity as LunchActivity).viewModel
        val isUserSignedIn = viewModel.isUserSignedIn()
        if (isUserSignedIn) {
            val intent = Intent(requireActivity(), ShoppingActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            Handler().postDelayed({
                startActivity(intent)
            }, 1500)
        } else {
            Handler().postDelayed({
                findNavController().navigate(R.id.action_mySplashScreen_to_firstScreenFragment)
            }, 1500)
        }
    }


}