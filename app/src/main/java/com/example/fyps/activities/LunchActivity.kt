package com.example.fyps.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fyps.R
import com.example.fyps.firebaseDatabase.FirebaseDb
import com.example.fyps.model.Status
import com.example.fyps.viewmodel.lunchapp.KleineViewModel
import com.example.fyps.viewmodel.lunchapp.ViewModelProviderFactory
import com.google.firebase.auth.FirebaseAuth

class LunchActivity : AppCompatActivity() {

    val viewModel by lazy {
        val firebaseDb = FirebaseDb()
        val viewModelFactory = ViewModelProviderFactory(firebaseDb)
        ViewModelProvider(this, viewModelFactory)[KleineViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)

        supportActionBar?.hide()

        // Check if the user's role is "Users" and redirect if necessary
        checkUserRoleAndRedirect()
    }

    private fun checkUserRoleAndRedirect() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let { uid ->
            viewModel.getUserStatus(uid) { status ->
                if (status == Status.USERS.name) {
                    // User has not selected a role, redirect to role selection
                    val intent = Intent(this, RequestRolesActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                // If the role is not "Users", continue with LunchActivity
            }
        }
    }





}