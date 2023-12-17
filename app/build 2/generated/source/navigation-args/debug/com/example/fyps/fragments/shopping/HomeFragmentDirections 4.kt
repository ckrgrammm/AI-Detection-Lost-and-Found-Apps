package com.example.fyps.fragments.shopping

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R
import com.example.fyps.model.Material
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.String
import kotlin.Suppress

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeFragmentToCategoryDetails(
    public val category: String
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_categoryDetails

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("category", this.category)
        return result
      }
  }

  private data class ActionHomeFragmentToMaterialDetailsFragment(
    public val material: Material?
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_materialDetailsFragment

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
    public fun actionHomeFragmentToAddMaterialFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_homeFragment_to_addMaterialFragment)

    public fun actionHomeFragmentToCategoryDetails(category: String): NavDirections =
        ActionHomeFragmentToCategoryDetails(category)

    public fun actionHomeFragmentToSearchFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_homeFragment_to_searchFragment)

    public fun actionHomeFragmentToMaterialDetailsFragment(material: Material?): NavDirections =
        ActionHomeFragmentToMaterialDetailsFragment(material)
  }
}
