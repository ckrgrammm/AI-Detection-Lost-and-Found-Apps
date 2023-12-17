package com.example.fyps.fragments.applunch

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class LoginFragmentDirections private constructor() {
  public companion object {
    public fun actionLoginFragmentToRegisterFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_registerFragment)

    public fun actionLoginFragmentToGenderSelectionFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_genderSelectionFragment)
  }
}
