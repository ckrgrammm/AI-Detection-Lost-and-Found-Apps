package com.example.fyps.fragments.settings

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class ItemSettingMainFragmentDirections private constructor() {
  public companion object {
    public fun actionProfileFragmentToItemSettingMainFragmentToItemSettingFragment(): NavDirections
        =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_itemSettingMainFragment_to_itemSettingFragment)
  }
}
