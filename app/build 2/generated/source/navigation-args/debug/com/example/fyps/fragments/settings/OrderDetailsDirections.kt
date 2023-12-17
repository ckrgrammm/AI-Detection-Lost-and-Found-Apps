package com.example.fyps.fragments.settings

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R
import kotlin.Int
import kotlin.String

public class OrderDetailsDirections private constructor() {
  private data class ActionOrderDetailsToQuizFragment(
    public val materialDocId: String
  ) : NavDirections {
    public override val actionId: Int = R.id.action_orderDetails_to_quizFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("materialDocId", this.materialDocId)
        return result
      }
  }

  public companion object {
    public fun actionOrderDetailsToQuizFragment(materialDocId: String): NavDirections =
        ActionOrderDetailsToQuizFragment(materialDocId)

    public fun actionOrderDetailsToProfileFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_orderDetails_to_profileFragment)
  }
}
