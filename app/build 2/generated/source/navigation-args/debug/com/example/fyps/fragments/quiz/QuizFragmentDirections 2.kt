package com.example.fyps.fragments.quiz

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.fyps.R
import com.example.fyps.model.CourseDocument
import com.example.fyps.model.Material
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.String
import kotlin.Suppress

public class QuizFragmentDirections private constructor() {
  private data class ActionQuizFragmentToPlayFragment(
    public val materialDocId: String,
    public val randomSetId: String
  ) : NavDirections {
    public override val actionId: Int = R.id.action_quizFragment_to_playFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("materialDocId", this.materialDocId)
        result.putString("randomSetId", this.randomSetId)
        return result
      }
  }

  private data class ActionQuizFragmentToOrderDetails(
    public val material: Material,
    public val courseDocument: CourseDocument
  ) : NavDirections {
    public override val actionId: Int = R.id.action_quizFragment_to_orderDetails

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(Material::class.java)) {
          result.putParcelable("material", this.material as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(Material::class.java)) {
          result.putSerializable("material", this.material as Serializable)
        } else {
          throw UnsupportedOperationException(Material::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (Parcelable::class.java.isAssignableFrom(CourseDocument::class.java)) {
          result.putParcelable("courseDocument", this.courseDocument as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(CourseDocument::class.java)) {
          result.putSerializable("courseDocument", this.courseDocument as Serializable)
        } else {
          throw UnsupportedOperationException(CourseDocument::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  public companion object {
    public fun actionQuizFragmentToPlayFragment(materialDocId: String, randomSetId: String):
        NavDirections = ActionQuizFragmentToPlayFragment(materialDocId, randomSetId)

    public fun actionQuizFragmentToOrderDetails(material: Material, courseDocument: CourseDocument):
        NavDirections = ActionQuizFragmentToOrderDetails(material, courseDocument)
  }
}
