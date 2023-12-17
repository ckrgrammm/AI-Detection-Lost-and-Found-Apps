package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R
import com.example.fyps.model.CartProductsList
import com.example.fyps.model.User
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.String
import kotlin.Suppress

public class ProfileFragmentDirections private constructor() {
  private data class ActionProfileFragmentToBillingFragment(
    public val clickFlag: String,
    public val price: String? = "null",
    public val products: CartProductsList? = null
  ) : NavDirections {
    public override val actionId: Int = R.id.action_profileFragment_to_billingFragment

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

  private data class ActionProfileFragmentToEditUserInformation(
    public val user: User
  ) : NavDirections {
    public override val actionId: Int = R.id.action_profileFragment_to_editUserInformation

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(User::class.java)) {
          result.putParcelable("user", this.user as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(User::class.java)) {
          result.putSerializable("user", this.user as Serializable)
        } else {
          throw UnsupportedOperationException(User::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  public companion object {
    public fun actionProfileFragmentToItemSettingMainFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_itemSettingMainFragment)

    public fun actionProfileFragmentToBillingFragment(
      clickFlag: String,
      price: String? = "null",
      products: CartProductsList? = null
    ): NavDirections = ActionProfileFragmentToBillingFragment(clickFlag, price, products)

    public fun actionProfileFragmentToEditUserInformation(user: User): NavDirections =
        ActionProfileFragmentToEditUserInformation(user)

    public fun actionProfileFragmentToAllOrdersFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_allOrdersFragment)

    public fun actionProfileFragmentToLanguageFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_languageFragment)

    public fun actionProfileFragmentToViewPartnershipFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_viewPartnershipFragment)

    public fun actionProfileFragmentToHelpFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_helpFragment)

    public fun actionProfileFragmentToJoinPartnerFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_joinPartnerFragment)

    public fun actionProfileFragmentToAdminDashboardFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_adminDashboardFragment)

    public fun actionProfileFragmentToPassedQuizzesFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_passedQuizzesFragment)

    public fun actionProfileFragmentToRewardFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_rewardFragment)
  }
}
