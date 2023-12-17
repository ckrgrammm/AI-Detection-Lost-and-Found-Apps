package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.fyps.model.Address
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class AddressFragmentArgs(
  public val address: Address? = null
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(Address::class.java)) {
      result.putParcelable("address", this.address as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(Address::class.java)) {
      result.putSerializable("address", this.address as Serializable?)
    }
    return result
  }

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(Address::class.java)) {
      result.set("address", this.address as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(Address::class.java)) {
      result.set("address", this.address as Serializable?)
    }
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): AddressFragmentArgs {
      bundle.setClassLoader(AddressFragmentArgs::class.java.classLoader)
      val __address : Address?
      if (bundle.containsKey("address")) {
        if (Parcelable::class.java.isAssignableFrom(Address::class.java) ||
            Serializable::class.java.isAssignableFrom(Address::class.java)) {
          __address = bundle.get("address") as Address?
        } else {
          throw UnsupportedOperationException(Address::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        __address = null
      }
      return AddressFragmentArgs(__address)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): AddressFragmentArgs {
      val __address : Address?
      if (savedStateHandle.contains("address")) {
        if (Parcelable::class.java.isAssignableFrom(Address::class.java) ||
            Serializable::class.java.isAssignableFrom(Address::class.java)) {
          __address = savedStateHandle.get<Address?>("address")
        } else {
          throw UnsupportedOperationException(Address::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        __address = null
      }
      return AddressFragmentArgs(__address)
    }
  }
}
