package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.fyps.model.Material
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class MaterialPreviewFragmentArgs(
  public val material: Material?
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(Material::class.java)) {
      result.putParcelable("material", this.material as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(Material::class.java)) {
      result.putSerializable("material", this.material as Serializable?)
    } else {
      throw UnsupportedOperationException(Material::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(Material::class.java)) {
      result.set("material", this.material as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(Material::class.java)) {
      result.set("material", this.material as Serializable?)
    } else {
      throw UnsupportedOperationException(Material::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): MaterialPreviewFragmentArgs {
      bundle.setClassLoader(MaterialPreviewFragmentArgs::class.java.classLoader)
      val __material : Material?
      if (bundle.containsKey("material")) {
        if (Parcelable::class.java.isAssignableFrom(Material::class.java) ||
            Serializable::class.java.isAssignableFrom(Material::class.java)) {
          __material = bundle.get("material") as Material?
        } else {
          throw UnsupportedOperationException(Material::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"material\" is missing and does not have an android:defaultValue")
      }
      return MaterialPreviewFragmentArgs(__material)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        MaterialPreviewFragmentArgs {
      val __material : Material?
      if (savedStateHandle.contains("material")) {
        if (Parcelable::class.java.isAssignableFrom(Material::class.java) ||
            Serializable::class.java.isAssignableFrom(Material::class.java)) {
          __material = savedStateHandle.get<Material?>("material")
        } else {
          throw UnsupportedOperationException(Material::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"material\" is missing and does not have an android:defaultValue")
      }
      return MaterialPreviewFragmentArgs(__material)
    }
  }
}
