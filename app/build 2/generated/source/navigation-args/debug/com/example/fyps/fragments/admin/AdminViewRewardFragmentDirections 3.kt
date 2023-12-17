package com.example.fyps.fragments.admin

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R
import kotlin.Int
import kotlin.String

public class AdminViewRewardFragmentDirections private constructor() {
  private data class ActionAdminViewRewardFragmentToAdminUpdateRewardFragment(
    public val rewardName: String
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_adminViewRewardFragment_to_adminUpdateRewardFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("rewardName", this.rewardName)
        return result
      }
  }

  public companion object {
    public fun actionAdminViewRewardFragmentToAdminAddRewardFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_adminViewRewardFragment_to_adminAddRewardFragment)

    public fun actionAdminViewRewardFragmentToAdminUpdateRewardFragment(rewardName: String):
        NavDirections = ActionAdminViewRewardFragmentToAdminUpdateRewardFragment(rewardName)
  }
}
