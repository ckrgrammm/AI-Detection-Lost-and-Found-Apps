package com.example.fyps.fragments.partnership

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.fyps.R
import kotlin.Int
import kotlin.String

public class PartnershipViewMaterialFragmentDirections private constructor() {
  private data class ActionPartnershipViewMaterialFragmentToPartnershipViewMaterialDetailFragment(
    public val documentId: String
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_partnershipViewMaterialFragment_to_partnershipViewMaterialDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("documentId", this.documentId)
        return result
      }
  }

  private data class ActionPartnershipViewMaterialFragmentToEditMaterialFragment(
    public val materialId: String
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_partnershipViewMaterialFragment_to_editMaterialFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("materialId", this.materialId)
        return result
      }
  }

  public companion object {
    public
        fun actionPartnershipViewMaterialFragmentToPartnershipViewMaterialDetailFragment(documentId: String):
        NavDirections =
        ActionPartnershipViewMaterialFragmentToPartnershipViewMaterialDetailFragment(documentId)

    public fun actionPartnershipViewMaterialFragmentToSetsFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_partnershipViewMaterialFragment_to_setsFragment)

    public fun actionPartnershipViewMaterialFragmentToEditMaterialFragment(materialId: String):
        NavDirections = ActionPartnershipViewMaterialFragmentToEditMaterialFragment(materialId)
  }
}
