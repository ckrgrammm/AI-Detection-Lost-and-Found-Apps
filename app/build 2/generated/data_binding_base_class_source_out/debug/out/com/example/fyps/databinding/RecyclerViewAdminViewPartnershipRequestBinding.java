// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RecyclerViewAdminViewPartnershipRequestBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final Button btnApprove;

  @NonNull
  public final Button btnReject;

  @NonNull
  public final TextView contactNum;

  @NonNull
  public final LinearLayout contactText;

  @NonNull
  public final LinearLayout documentText;

  @NonNull
  public final TextView instiNameType;

  @NonNull
  public final LinearLayout instiText;

  @NonNull
  public final TextView location;

  @NonNull
  public final LinearLayout locationText;

  @NonNull
  public final TextView nameText;

  @NonNull
  public final TextView pdfFile1;

  @NonNull
  public final TextView pdfFile2;

  @NonNull
  public final TextView reason;

  @NonNull
  public final LinearLayout reasonText;

  @NonNull
  public final CircleImageView userImg;

  private RecyclerViewAdminViewPartnershipRequestBinding(@NonNull CardView rootView,
      @NonNull Button btnApprove, @NonNull Button btnReject, @NonNull TextView contactNum,
      @NonNull LinearLayout contactText, @NonNull LinearLayout documentText,
      @NonNull TextView instiNameType, @NonNull LinearLayout instiText, @NonNull TextView location,
      @NonNull LinearLayout locationText, @NonNull TextView nameText, @NonNull TextView pdfFile1,
      @NonNull TextView pdfFile2, @NonNull TextView reason, @NonNull LinearLayout reasonText,
      @NonNull CircleImageView userImg) {
    this.rootView = rootView;
    this.btnApprove = btnApprove;
    this.btnReject = btnReject;
    this.contactNum = contactNum;
    this.contactText = contactText;
    this.documentText = documentText;
    this.instiNameType = instiNameType;
    this.instiText = instiText;
    this.location = location;
    this.locationText = locationText;
    this.nameText = nameText;
    this.pdfFile1 = pdfFile1;
    this.pdfFile2 = pdfFile2;
    this.reason = reason;
    this.reasonText = reasonText;
    this.userImg = userImg;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static RecyclerViewAdminViewPartnershipRequestBinding inflate(
      @NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RecyclerViewAdminViewPartnershipRequestBinding inflate(
      @NonNull LayoutInflater inflater, @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.recycler_view_admin_view_partnership_request, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RecyclerViewAdminViewPartnershipRequestBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnApprove;
      Button btnApprove = ViewBindings.findChildViewById(rootView, id);
      if (btnApprove == null) {
        break missingId;
      }

      id = R.id.btnReject;
      Button btnReject = ViewBindings.findChildViewById(rootView, id);
      if (btnReject == null) {
        break missingId;
      }

      id = R.id.contactNum;
      TextView contactNum = ViewBindings.findChildViewById(rootView, id);
      if (contactNum == null) {
        break missingId;
      }

      id = R.id.contactText;
      LinearLayout contactText = ViewBindings.findChildViewById(rootView, id);
      if (contactText == null) {
        break missingId;
      }

      id = R.id.documentText;
      LinearLayout documentText = ViewBindings.findChildViewById(rootView, id);
      if (documentText == null) {
        break missingId;
      }

      id = R.id.instiNameType;
      TextView instiNameType = ViewBindings.findChildViewById(rootView, id);
      if (instiNameType == null) {
        break missingId;
      }

      id = R.id.instiText;
      LinearLayout instiText = ViewBindings.findChildViewById(rootView, id);
      if (instiText == null) {
        break missingId;
      }

      id = R.id.location;
      TextView location = ViewBindings.findChildViewById(rootView, id);
      if (location == null) {
        break missingId;
      }

      id = R.id.locationText;
      LinearLayout locationText = ViewBindings.findChildViewById(rootView, id);
      if (locationText == null) {
        break missingId;
      }

      id = R.id.nameText;
      TextView nameText = ViewBindings.findChildViewById(rootView, id);
      if (nameText == null) {
        break missingId;
      }

      id = R.id.pdfFile1;
      TextView pdfFile1 = ViewBindings.findChildViewById(rootView, id);
      if (pdfFile1 == null) {
        break missingId;
      }

      id = R.id.pdfFile2;
      TextView pdfFile2 = ViewBindings.findChildViewById(rootView, id);
      if (pdfFile2 == null) {
        break missingId;
      }

      id = R.id.reason;
      TextView reason = ViewBindings.findChildViewById(rootView, id);
      if (reason == null) {
        break missingId;
      }

      id = R.id.reasonText;
      LinearLayout reasonText = ViewBindings.findChildViewById(rootView, id);
      if (reasonText == null) {
        break missingId;
      }

      id = R.id.user_img;
      CircleImageView userImg = ViewBindings.findChildViewById(rootView, id);
      if (userImg == null) {
        break missingId;
      }

      return new RecyclerViewAdminViewPartnershipRequestBinding((CardView) rootView, btnApprove,
          btnReject, contactNum, contactText, documentText, instiNameType, instiText, location,
          locationText, nameText, pdfFile1, pdfFile2, reason, reasonText, userImg);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}