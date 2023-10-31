package com.example.fyps.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fyps.R
import com.example.fyps.firebaseDatabase.FirebaseDb
import com.example.fyps.viewmodel.lunchapp.KleineViewModel
import com.example.fyps.viewmodel.lunchapp.ViewModelProviderFactory

class LunchActivity : AppCompatActivity() {
    val viewModel by lazy {
        val firebaseDb = FirebaseDb()
        val viewModelFactory = ViewModelProviderFactory(firebaseDb)
        ViewModelProvider(this,viewModelFactory)[KleineViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)

        supportActionBar?.hide()

//        val random = Random.nextInt(from = 10000, until = 99999)

    }



}