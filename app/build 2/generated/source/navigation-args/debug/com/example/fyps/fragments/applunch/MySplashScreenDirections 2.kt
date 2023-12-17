package com.example.fyps.fragments.applunch

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class MySplashScreenDirections private constructor() {
  public companion object {
    public fun actionMySplashScreenToFirstScreenFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_mySplashScreen_to_firstScreenFragment)
  }
}
