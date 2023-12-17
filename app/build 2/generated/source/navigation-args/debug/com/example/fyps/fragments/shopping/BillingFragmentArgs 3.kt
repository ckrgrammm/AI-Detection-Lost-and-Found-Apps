package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.fyps.model.CartProductsList
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.String
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class BillingFragmentArgs(
  public val clickFlag: String,
  public val price: String? = "null",
  public val products: CartProductsList? = null
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
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

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("clickFlag", this.clickFlag)
    result.set("price", this.price)
    if (Parcelable::class.java.isAssignableFrom(CartProductsList::class.java)) {
      result.set("products", this.products as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(CartProductsList::class.java)) {
      result.set("products", this.products as Serializable?)
    }
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): BillingFragmentArgs {
      bundle.setClassLoader(BillingFragmentArgs::class.java.classLoader)
      val __clickFlag : String?
      if (bundle.containsKey("clickFlag")) {
        __clickFlag = bundle.getString("clickFlag")
        if (__clickFlag == null) {
          throw IllegalArgumentException("Argument \"clickFlag\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"clickFlag\" is missing and does not have an android:defaultValue")
      }
      val __price : String?
      if (bundle.containsKey("price")) {
        __price = bundle.getString("price")
      } else {
        __price = "null"
      }
      val __products : CartProductsList?
      if (bundle.containsKey("products")) {
        if (Parcelable::class.java.isAssignableFrom(CartProductsList::class.java) ||
            Serializable::class.java.isAssignableFrom(CartProductsList::class.java)) {
          __products = bundle.get("products") as CartProductsList?
        } else {
          throw UnsupportedOperationException(CartProductsList::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        __products = null
      }
      return BillingFragmentArgs(__clickFlag, __price, __products)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): BillingFragmentArgs {
      val __clickFlag : String?
      if (savedStateHandle.contains("clickFlag")) {
        __clickFlag = savedStateHandle["clickFlag"]
        if (__clickFlag == null) {
          throw IllegalArgumentException("Argument \"clickFlag\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"clickFlag\" is missing and does not have an android:defaultValue")
      }
      val __price : String?
      if (savedStateHandle.contains("price")) {
        __price = savedStateHandle["price"]
      } else {
        __price = "null"
      }
      val __products : CartProductsList?
      if (savedStateHandle.contains("products")) {
        if (Parcelable::class.java.isAssignableFrom(CartProductsList::class.java) ||
            Serializable::class.java.isAssignableFrom(CartProductsList::class.java)) {
          __products = savedStateHandle.get<CartProductsList?>("products")
        } else {
          throw UnsupportedOperationException(CartProductsList::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        __products = null
      }
      return BillingFragmentArgs(__clickFlag, __price, __products)
    }
  }
}
