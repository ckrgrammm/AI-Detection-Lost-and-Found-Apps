package com.example.fyps.fragments.admin

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class AdminViewPartnershipFragmentDirections private constructor() {
  public companion object {
    public fun actionAdminViewPartnershipFragmentToAdminViewPartnershipRequestFragment():
        NavDirections =
        ActionOnlyNavDirections(R.id.action_adminViewPartnershipFragment_to_adminViewPartnershipRequestFragment)
  }
}
