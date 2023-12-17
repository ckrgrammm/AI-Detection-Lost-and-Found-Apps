package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R
import com.example.fyps.model.Address
import com.example.fyps.model.Order
import java.io.Serializable
import kotlin.Int
import kotlin.String
import kotlin.Suppress

public class BillingFragmentDirections private constructor() {
  private data class ActionBillingFragmentToAddressFragment(
    public val address: Address? = null
  ) : NavDirections {
    public override val actionId: Int = R.id.action_billingFragment_to_addressFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(Address::class.java)) {
          result.putParcelable("address", this.address as Parcelable?)
        } else if (Serializable::class.java.isAssignableFrom(Address::class.java)) {
          result.putSerializable("address", this.address as Serializable?)
        }
        return result
      }
  }

  private data class ActionBillingFragmentToOrderCompletion(
    public val orderCompletionFlag: String,
    public val orderNumber: String? = "null",
    public val order: Order? = null
  ) : NavDirections {
    public override val actionId: Int = R.id.action_billingFragment_to_orderCompletion

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        result.putString("order_completion_flag", this.orderCompletionFlag)
        result.putString("orderNumber", this.orderNumber)
        if (Parcelable::class.java.isAssignableFrom(Order::class.java)) {
          result.putParcelable("order", this.order as Parcelable?)
        } else if (Serializable::class.java.isAssignableFrom(Order::class.java)) {
          result.putSerializable("order", this.order as Serializable?)
        }
        return result
      }
  }

  public companion object {
    public fun actionBillingFragmentToAddressFragment(address: Address? = null): NavDirections =
        ActionBillingFragmentToAddressFragment(address)

    public fun actionBillingFragmentToOrderCompletion(
      orderCompletionFlag: String,
      orderNumber: String? = "null",
      order: Order? = null
    ): NavDirections = ActionBillingFragmentToOrderCompletion(orderCompletionFlag, orderNumber,
        order)

    public fun actionBillingFragmentToRewardFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_billingFragment_to_rewardFragment)
  }
}
