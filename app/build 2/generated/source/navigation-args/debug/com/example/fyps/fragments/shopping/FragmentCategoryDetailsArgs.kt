package com.example.fyps.fragments.shopping

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class FragmentCategoryDetailsArgs(
  public val category: String
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("category", this.category)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("category", this.category)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): FragmentCategoryDetailsArgs {
      bundle.setClassLoader(FragmentCategoryDetailsArgs::class.java.classLoader)
      val __category : String?
      if (bundle.containsKey("category")) {
        __category = bundle.getString("category")
        if (__category == null) {
          throw IllegalArgumentException("Argument \"category\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"category\" is missing and does not have an android:defaultValue")
      }
      return FragmentCategoryDetailsArgs(__category)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        FragmentCategoryDetailsArgs {
      val __category : String?
      if (savedStateHandle.contains("category")) {
        __category = savedStateHandle["category"]
        if (__category == null) {
          throw IllegalArgumentException("Argument \"category\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"category\" is missing and does not have an android:defaultValue")
      }
      return FragmentCategoryDetailsArgs(__category)
    }
  }
}
