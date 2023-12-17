package com.example.fyps.fragments.quiz

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class PlayFragmentDirections private constructor() {
  public companion object {
    public fun actionPlayFragmentToResultFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_playFragment_to_resultFragment)

    public fun actionPlayFragmentToQuizFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_playFragment_to_quizFragment)
  }
}
