package com.example.fyps.fragments.quiz

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class PlayFragmentArgs(
  public val materialDocId: String
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("materialDocId", this.materialDocId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("materialDocId", this.materialDocId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): PlayFragmentArgs {
      bundle.setClassLoader(PlayFragmentArgs::class.java.classLoader)
      val __materialDocId : String?
      if (bundle.containsKey("materialDocId")) {
        __materialDocId = bundle.getString("materialDocId")
        if (__materialDocId == null) {
          throw IllegalArgumentException("Argument \"materialDocId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"materialDocId\" is missing and does not have an android:defaultValue")
      }
      return PlayFragmentArgs(__materialDocId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): PlayFragmentArgs {
      val __materialDocId : String?
      if (savedStateHandle.contains("materialDocId")) {
        __materialDocId = savedStateHandle["materialDocId"]
        if (__materialDocId == null) {
          throw IllegalArgumentException("Argument \"materialDocId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"materialDocId\" is missing and does not have an android:defaultValue")
      }
      return PlayFragmentArgs(__materialDocId)
    }
  }
}
