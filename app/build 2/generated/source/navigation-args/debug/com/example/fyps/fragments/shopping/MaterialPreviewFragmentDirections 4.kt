package com.example.fyps.fragments.shopping

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.fyps.R
import kotlin.Int

public class MaterialPreviewFragmentDirections private constructor() {
  private data class ActionMaterialDetailsFragmentToHomeFragment(
    public val position: Int = 0
  ) : NavDirections {
    public override val actionId: Int = R.id.action_materialDetailsFragment_to_homeFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("position", this.position)
        return result
      }
  }

  public companion object {
    public fun actionMaterialDetailsFragmentToHomeFragment(position: Int = 0): NavDirections =
        ActionMaterialDetailsFragmentToHomeFragment(position)
  }
}
