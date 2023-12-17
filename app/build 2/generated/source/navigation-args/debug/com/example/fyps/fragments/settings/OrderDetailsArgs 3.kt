package com.example.fyps.fragments.settings

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.fyps.model.CourseDocument
import com.example.fyps.model.Material
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class OrderDetailsArgs(
  public val material: Material,
  public val courseDocument: CourseDocument
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(Material::class.java)) {
      result.putParcelable("material", this.material as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(Material::class.java)) {
      result.putSerializable("material", this.material as Serializable)
    } else {
      throw UnsupportedOperationException(Material::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    if (Parcelable::class.java.isAssignableFrom(CourseDocument::class.java)) {
      result.putParcelable("courseDocument", this.courseDocument as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(CourseDocument::class.java)) {
      result.putSerializable("courseDocument", this.courseDocument as Serializable)
    } else {
      throw UnsupportedOperationException(CourseDocument::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(Material::class.java)) {
      result.set("material", this.material as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(Material::class.java)) {
      result.set("material", this.material as Serializable)
    } else {
      throw UnsupportedOperationException(Material::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    if (Parcelable::class.java.isAssignableFrom(CourseDocument::class.java)) {
      result.set("courseDocument", this.courseDocument as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(CourseDocument::class.java)) {
      result.set("courseDocument", this.courseDocument as Serializable)
    } else {
      throw UnsupportedOperationException(CourseDocument::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): OrderDetailsArgs {
      bundle.setClassLoader(OrderDetailsArgs::class.java.classLoader)
      val __material : Material?
      if (bundle.containsKey("material")) {
        if (Parcelable::class.java.isAssignableFrom(Material::class.java) ||
            Serializable::class.java.isAssignableFrom(Material::class.java)) {
          __material = bundle.get("material") as Material?
        } else {
          throw UnsupportedOperationException(Material::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__material == null) {
          throw IllegalArgumentException("Argument \"material\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"material\" is missing and does not have an android:defaultValue")
      }
      val __courseDocument : CourseDocument?
      if (bundle.containsKey("courseDocument")) {
        if (Parcelable::class.java.isAssignableFrom(CourseDocument::class.java) ||
            Serializable::class.java.isAssignableFrom(CourseDocument::class.java)) {
          __courseDocument = bundle.get("courseDocument") as CourseDocument?
        } else {
          throw UnsupportedOperationException(CourseDocument::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__courseDocument == null) {
          throw IllegalArgumentException("Argument \"courseDocument\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"courseDocument\" is missing and does not have an android:defaultValue")
      }
      return OrderDetailsArgs(__material, __courseDocument)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): OrderDetailsArgs {
      val __material : Material?
      if (savedStateHandle.contains("material")) {
        if (Parcelable::class.java.isAssignableFrom(Material::class.java) ||
            Serializable::class.java.isAssignableFrom(Material::class.java)) {
          __material = savedStateHandle.get<Material?>("material")
        } else {
          throw UnsupportedOperationException(Material::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__material == null) {
          throw IllegalArgumentException("Argument \"material\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"material\" is missing and does not have an android:defaultValue")
      }
      val __courseDocument : CourseDocument?
      if (savedStateHandle.contains("courseDocument")) {
        if (Parcelable::class.java.isAssignableFrom(CourseDocument::class.java) ||
            Serializable::class.java.isAssignableFrom(CourseDocument::class.java)) {
          __courseDocument = savedStateHandle.get<CourseDocument?>("courseDocument")
        } else {
          throw UnsupportedOperationException(CourseDocument::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__courseDocument == null) {
          throw IllegalArgumentException("Argument \"courseDocument\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"courseDocument\" is missing and does not have an android:defaultValue")
      }
      return OrderDetailsArgs(__material, __courseDocument)
    }
  }
}
