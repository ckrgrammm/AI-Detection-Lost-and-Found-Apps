package com.example.fyps.fragments.admin

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R

public class AdminListFragmentDirections private constructor() {
  public companion object {
    public fun actionAdminListFragmentToAdminFormFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_adminListFragment_to_adminFormFragment)
  }
}
