package com.example.fyps.fragments.partnership

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.fyps.R
import kotlin.Int
import kotlin.String

public class PartnershipViewMaterialDetailFragmentDirections private constructor() {
  private data class ActionPartnershipViewMaterialDetailFragmentToReplyCommentFragment(
    public val documentId: String
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_partnershipViewMaterialDetailFragment_to_replyCommentFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("documentId", this.documentId)
        return result
      }
  }

  public companion object {
    public
        fun actionPartnershipViewMaterialDetailFragmentToReplyCommentFragment(documentId: String):
        NavDirections =
        ActionPartnershipViewMaterialDetailFragmentToReplyCommentFragment(documentId)
  }
}
