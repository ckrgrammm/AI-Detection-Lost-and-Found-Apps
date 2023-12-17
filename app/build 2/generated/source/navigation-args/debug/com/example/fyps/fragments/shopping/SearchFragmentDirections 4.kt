package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.fyps.R
import com.example.fyps.model.Material
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class SearchFragmentDirections private constructor() {
  private data class ActionSearchFragmentToHomeFragment(
    public val position: Int = 0
  ) : NavDirections {
    public override val actionId: Int = R.id.action_searchFragment_to_homeFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("position", this.position)
        return result
      }
  }

  private data class ActionSearchFragmentToMaterialPreviewFragment(
    public val material: Material?
  ) : NavDirections {
    public override val actionId: Int = R.id.action_searchFragment_to_materialPreviewFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
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
  }

  public companion object {
    public fun actionSearchFragmentToHomeFragment(position: Int = 0): NavDirections =
        ActionSearchFragmentToHomeFragment(position)

    public fun actionSearchFragmentToMaterialPreviewFragment(material: Material?): NavDirections =
        ActionSearchFragmentToMaterialPreviewFragment(material)
  }
}
