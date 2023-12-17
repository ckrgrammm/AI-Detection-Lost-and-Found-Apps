package com.example.fyps.fragments.reward

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R
import com.example.fyps.model.CartProductsList
import java.io.Serializable
import kotlin.Int
import kotlin.String
import kotlin.Suppress

public class RewardFragmentDirections private constructor() {
  private data class ActionRewardFragmentToBillingFragment(
    public val clickFlag: String,
    public val price: String? = "null",
    public val products: CartProductsList? = null
  ) : NavDirections {
    public override val actionId: Int = R.id.action_rewardFragment_to_billingFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        result.putString("clickFlag", this.clickFlag)
        result.putString("price", this.price)
        if (Parcelable::class.java.isAssignableFrom(CartProductsList::class.java)) {
          result.putParcelable("products", this.products as Parcelable?)
        } else if (Serializable::class.java.isAssignableFrom(CartProductsList::class.java)) {
          result.putSerializable("products", this.products as Serializable?)
        }
        return result
      }
  }

  public companion object {
    public fun actionRewardFragmentToBillingFragment(
      clickFlag: String,
      price: String? = "null",
      products: CartProductsList? = null
    ): NavDirections = ActionRewardFragmentToBillingFragment(clickFlag, price, products)

    public fun actionRewardFragmentToProfileFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_rewardFragment_to_profileFragment)
  }
}
