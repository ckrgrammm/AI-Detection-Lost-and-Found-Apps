package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.fyps.model.Order
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.String
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class OrderCompletionArgs(
  public val orderCompletionFlag: String,
  public val orderNumber: String? = "null",
  public val order: Order? = null
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
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

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("order_completion_flag", this.orderCompletionFlag)
    result.set("orderNumber", this.orderNumber)
    if (Parcelable::class.java.isAssignableFrom(Order::class.java)) {
      result.set("order", this.order as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(Order::class.java)) {
      result.set("order", this.order as Serializable?)
    }
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): OrderCompletionArgs {
      bundle.setClassLoader(OrderCompletionArgs::class.java.classLoader)
      val __orderCompletionFlag : String?
      if (bundle.containsKey("order_completion_flag")) {
        __orderCompletionFlag = bundle.getString("order_completion_flag")
        if (__orderCompletionFlag == null) {
          throw IllegalArgumentException("Argument \"order_completion_flag\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"order_completion_flag\" is missing and does not have an android:defaultValue")
      }
      val __orderNumber : String?
      if (bundle.containsKey("orderNumber")) {
        __orderNumber = bundle.getString("orderNumber")
      } else {
        __orderNumber = "null"
      }
      val __order : Order?
      if (bundle.containsKey("order")) {
        if (Parcelable::class.java.isAssignableFrom(Order::class.java) ||
            Serializable::class.java.isAssignableFrom(Order::class.java)) {
          __order = bundle.get("order") as Order?
        } else {
          throw UnsupportedOperationException(Order::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        __order = null
      }
      return OrderCompletionArgs(__orderCompletionFlag, __orderNumber, __order)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): OrderCompletionArgs {
      val __orderCompletionFlag : String?
      if (savedStateHandle.contains("order_completion_flag")) {
        __orderCompletionFlag = savedStateHandle["order_completion_flag"]
        if (__orderCompletionFlag == null) {
          throw IllegalArgumentException("Argument \"order_completion_flag\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"order_completion_flag\" is missing and does not have an android:defaultValue")
      }
      val __orderNumber : String?
      if (savedStateHandle.contains("orderNumber")) {
        __orderNumber = savedStateHandle["orderNumber"]
      } else {
        __orderNumber = "null"
      }
      val __order : Order?
      if (savedStateHandle.contains("order")) {
        if (Parcelable::class.java.isAssignableFrom(Order::class.java) ||
            Serializable::class.java.isAssignableFrom(Order::class.java)) {
          __order = savedStateHandle.get<Order?>("order")
        } else {
          throw UnsupportedOperationException(Order::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        __order = null
      }
      return OrderCompletionArgs(__orderCompletionFlag, __orderNumber, __order)
    }
  }
}
