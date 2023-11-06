package com.example.fyps.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fyps.R
import com.example.fyps.databinding.ActivityRequestRolesBinding
import com.example.fyps.firebaseDatabase.FirebaseDb
import com.example.fyps.model.Status
import com.example.fyps.viewmodel.lunchapp.KleineViewModel
import com.example.fyps.viewmodel.lunchapp.ViewModelProviderFactory
import com.google.firebase.auth.FirebaseAuth

class RequestRolesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestRolesBinding
    private var selectedRole: Status? = null
    private lateinit var viewModel: KleineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestRolesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create an instance of FirebaseDb
        val firebaseDbInstance = FirebaseDb() // Replace with the actual way to obtain an instance

        // Create an instance of ViewModelProviderFactory
        val factory = ViewModelProviderFactory(firebaseDbInstance)

        // Get the ViewModel from the factory
        viewModel = ViewModelProvider(this, factory).get(KleineViewModel::class.java)

        // Set up click listeners for the finder (maleImage) and reporter (femaleImage) buttons
        binding.maleImage.setOnClickListener {
            selectedRole = Status.FINDERS
            binding.maleImage.setImageResource(R.drawable.gender_male_click)
            // Reset female image and text
            binding.femaleImage.setImageResource(R.drawable.gender_female)
            binding.femaleText.setTextColor(resources.getColor(android.R.color.black))
        }

        binding.femaleImage.setOnClickListener {
            selectedRole = Status.REPORTERS
            binding.femaleImage.setImageResource(R.drawable.gender_male_click)
            // Reset male image and text
            binding.maleImage.setImageResource(R.drawable.gender_male)
            binding.maleText.setTextColor(resources.getColor(android.R.color.black))
        }

        // Set up click listener for the next button
        binding.nextButton.setOnClickListener {
            selectedRole?.let { role ->
                updateUserRole(role)
            } ?: run {
                Toast.makeText(this, "Please select a role first.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateUserRole(selectedRole: Status) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {
            viewModel.updateUserRole(it, selectedRole).addOnSuccessListener {
                // Role updated successfully, navigate to the LunchActivity
                val intent = Intent(this, LunchActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                // Handle error, perhaps show a toast message
                Toast.makeText(this, "Failed to update role: ${it.message}", Toast.LENGTH_LONG).show()
            }
        } ?: run {
            Toast.makeText(this, "User not logged in.", Toast.LENGTH_LONG).show()
        }
    }

}
