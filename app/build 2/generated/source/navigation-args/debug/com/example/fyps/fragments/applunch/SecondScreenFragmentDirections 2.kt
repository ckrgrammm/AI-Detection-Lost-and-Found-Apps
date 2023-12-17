package com.example.fyps.fragments.applunch

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class SecondScreenFragmentDirections private constructor() {
  public companion object {
    public fun actionSecondScreenFragmentToRegisterFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_secondScreenFragment_to_registerFragment)

    public fun actionSecondScreenFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_secondScreenFragment_to_loginFragment)
  }
}
