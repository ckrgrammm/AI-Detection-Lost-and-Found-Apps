package com.example.fyps.fragments.admin

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class AdminDashboardFragmentDirections private constructor() {
  public companion object {
    public fun actionAdminDashboardFragmentToAdminViewPartnershipFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_adminDashboardFragment_to_adminViewPartnershipFragment)

    public fun actionAdminDashboardFragmentToAdminViewRewardFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_adminDashboardFragment_to_adminViewRewardFragment)

    public fun actionAdminDashboardFragmentToAdminOverviewFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_adminDashboardFragment_to_adminOverviewFragment)

    public fun actionAdminDashboardFragmentToAdminListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_adminDashboardFragment_to_adminListFragment)
  }
}
