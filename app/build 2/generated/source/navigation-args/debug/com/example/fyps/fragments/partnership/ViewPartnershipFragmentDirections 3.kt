package com.example.fyps.fragments.partnership

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class ViewPartnershipFragmentDirections private constructor() {
  public companion object {
    public fun actionViewPartnershipFragmentToPartnershipViewMaterialFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_viewPartnershipFragment_to_partnershipViewMaterialFragment)

    public fun actionViewPartnershipFragmentToUpdatePartnershipFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_viewPartnershipFragment_to_updatePartnershipFragment)
  }
}
