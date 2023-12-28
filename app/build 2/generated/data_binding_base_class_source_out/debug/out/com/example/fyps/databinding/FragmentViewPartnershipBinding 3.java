// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import com.github.barteksc.pdfviewer.PDFView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentViewPartnershipBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button closePdfButton;

  @NonNull
  public final ConstraintLayout constraintLayout2;

  @NonNull
  public final TextView contactNumText;

  @NonNull
  public final TextView documentText1;

  @NonNull
  public final TextView documentText2;

  @NonNull
  public final TextView email;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView institutionNameText;

  @NonNull
  public final TextView institutionTypeText;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final TextView locationText;

  @NonNull
  public final PDFView pdfView;

  @NonNull
  public final LinearLayout quitPartnership;

  @NonNull
  public final LinearLayout updatePartnership;

  @NonNull
  public final TextView username;

  @NonNull
  public final LinearLayout viewMaterial;

  private FragmentViewPartnershipBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button closePdfButton, @NonNull ConstraintLayout constraintLayout2,
      @NonNull TextView contactNumText, @NonNull TextView documentText1,
      @NonNull TextView documentText2, @NonNull TextView email, @NonNull ImageView imageView,
      @NonNull TextView institutionNameText, @NonNull TextView institutionTypeText,
      @NonNull LinearLayout linearLayout, @NonNull TextView locationText, @NonNull PDFView pdfView,
      @NonNull LinearLayout quitPartnership, @NonNull LinearLayout updatePartnership,
      @NonNull TextView username, @NonNull LinearLayout viewMaterial) {
    this.rootView = rootView;
    this.closePdfButton = closePdfButton;
    this.constraintLayout2 = constraintLayout2;
    this.contactNumText = contactNumText;
    this.documentText1 = documentText1;
    this.documentText2 = documentText2;
    this.email = email;
    this.imageView = imageView;
    this.institutionNameText = institutionNameText;
    this.institutionTypeText = institutionTypeText;
    this.linearLayout = linearLayout;
    this.locationText = locationText;
    this.pdfView = pdfView;
    this.quitPartnership = quitPartnership;
    this.updatePartnership = updatePartnership;
    this.username = username;
    this.viewMaterial = viewMaterial;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentViewPartnershipBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentViewPartnershipBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_view_partnership, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentViewPartnershipBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.closePdfButton;
      Button closePdfButton = ViewBindings.findChildViewById(rootView, id);
      if (closePdfButton == null) {
        break missingId;
      }

      id = R.id.constraintLayout2;
      ConstraintLayout constraintLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout2 == null) {
        break missingId;
      }

      id = R.id.contactNumText;
      TextView contactNumText = ViewBindings.findChildViewById(rootView, id);
      if (contactNumText == null) {
        break missingId;
      }

      id = R.id.documentText1;
      TextView documentText1 = ViewBindings.findChildViewById(rootView, id);
      if (documentText1 == null) {
        break missingId;
      }

      id = R.id.documentText2;
      TextView documentText2 = ViewBindings.findChildViewById(rootView, id);
      if (documentText2 == null) {
        break missingId;
      }

      id = R.id.email;
      TextView email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.institutionNameText;
      TextView institutionNameText = ViewBindings.findChildViewById(rootView, id);
      if (institutionNameText == null) {
        break missingId;
      }

      id = R.id.institutionTypeText;
      TextView institutionTypeText = ViewBindings.findChildViewById(rootView, id);
      if (institutionTypeText == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.locationText;
      TextView locationText = ViewBindings.findChildViewById(rootView, id);
      if (locationText == null) {
        break missingId;
      }

      id = R.id.pdfView;
      PDFView pdfView = ViewBindings.findChildViewById(rootView, id);
      if (pdfView == null) {
        break missingId;
      }

      id = R.id.quit_partnership;
      LinearLayout quitPartnership = ViewBindings.findChildViewById(rootView, id);
      if (quitPartnership == null) {
        break missingId;
      }

      id = R.id.update_partnership;
      LinearLayout updatePartnership = ViewBindings.findChildViewById(rootView, id);
      if (updatePartnership == null) {
        break missingId;
      }

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      id = R.id.view_material;
      LinearLayout viewMaterial = ViewBindings.findChildViewById(rootView, id);
      if (viewMaterial == null) {
        break missingId;
      }

      return new FragmentViewPartnershipBinding((ConstraintLayout) rootView, closePdfButton,
          constraintLayout2, contactNumText, documentText1, documentText2, email, imageView,
          institutionNameText, institutionTypeText, linearLayout, locationText, pdfView,
          quitPartnership, updatePartnership, username, viewMaterial);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}