package com.example.fyps.fragments.partnership

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class EditMaterialFragmentArgs(
  public val materialId: String
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("materialId", this.materialId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("materialId", this.materialId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): EditMaterialFragmentArgs {
      bundle.setClassLoader(EditMaterialFragmentArgs::class.java.classLoader)
      val __materialId : String?
      if (bundle.containsKey("materialId")) {
        __materialId = bundle.getString("materialId")
        if (__materialId == null) {
          throw IllegalArgumentException("Argument \"materialId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"materialId\" is missing and does not have an android:defaultValue")
      }
      return EditMaterialFragmentArgs(__materialId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): EditMaterialFragmentArgs {
      val __materialId : String?
      if (savedStateHandle.contains("materialId")) {
        __materialId = savedStateHandle["materialId"]
        if (__materialId == null) {
          throw IllegalArgumentException("Argument \"materialId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"materialId\" is missing and does not have an android:defaultValue")
      }
      return EditMaterialFragmentArgs(__materialId)
    }
  }
}
