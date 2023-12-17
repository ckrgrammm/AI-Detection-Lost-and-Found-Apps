package com.example.fyps.fragments.quiz

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class QuestionFragmentDirections private constructor() {
  public companion object {
    public fun actionQuestionFragmentToAddUpdateQuestionFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_questionFragment_to_addUpdateQuestionFragment)
  }
}
