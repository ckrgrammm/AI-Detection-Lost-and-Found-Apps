package com.example.fyps.fragments.applunch

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class FirstScreenFragmentDirections private constructor() {
  public companion object {
    public fun actionFirstScreenFragmentToSecondScreenFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_firstScreenFragment_to_secondScreenFragment)
  }
}
