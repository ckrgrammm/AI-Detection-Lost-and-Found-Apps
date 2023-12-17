package com.example.fyps.fragments.settings

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.fyps.R
import kotlin.Int
import kotlin.String

public class ItemSettingFragmentDirections private constructor() {
  private data class ActionItemSettingMainFragmentToItemSettingFragmentToEditMaterialFragment(
    public val materialId: String
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_itemSettingMainFragment_to_itemSettingFragment_to_editMaterialFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("materialId", this.materialId)
        return result
      }
  }

  public companion object {
    public
        fun actionItemSettingMainFragmentToItemSettingFragmentToEditMaterialFragment(materialId: String):
        NavDirections =
        ActionItemSettingMainFragmentToItemSettingFragmentToEditMaterialFragment(materialId)
  }
}
