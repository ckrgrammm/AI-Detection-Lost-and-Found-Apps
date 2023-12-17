package com.example.fyps.fragments.shopping

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class HomeFragmentArgs(
  public val position: Int = 0
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("position", this.position)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("position", this.position)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): HomeFragmentArgs {
      bundle.setClassLoader(HomeFragmentArgs::class.java.classLoader)
      val __position : Int
      if (bundle.containsKey("position")) {
        __position = bundle.getInt("position")
      } else {
        __position = 0
      }
      return HomeFragmentArgs(__position)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): HomeFragmentArgs {
      val __position : Int?
      if (savedStateHandle.contains("position")) {
        __position = savedStateHandle["position"]
        if (__position == null) {
          throw IllegalArgumentException("Argument \"position\" of type integer does not support null values")
        }
      } else {
        __position = 0
      }
      return HomeFragmentArgs(__position)
    }
  }
}
