package com.example.fyps.fragments.quiz

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class SetsFragmentDirections private constructor() {
  public companion object {
    public fun actionSetsFragmentToQuestionFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_setsFragment_to_questionFragment)
  }
}
