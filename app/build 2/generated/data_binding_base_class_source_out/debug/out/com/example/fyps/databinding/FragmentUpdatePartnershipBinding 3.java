// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentUpdatePartnershipBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button reqBtnRequest;

  @NonNull
  public final EditText reqContactNo;

  @NonNull
  public final TextView reqContactNoText;

  @NonNull
  public final EditText reqInstiName;

  @NonNull
  public final TextView reqInstiNameText;

  @NonNull
  public final EditText reqInstiType;

  @NonNull
  public final TextView reqInstiTypeText;

  @NonNull
  public final TextView reqLocText;

  @NonNull
  public final EditText reqLocation;

  private FragmentUpdatePartnershipBinding(@NonNull LinearLayout rootView,
      @NonNull Button reqBtnRequest, @NonNull EditText reqContactNo,
      @NonNull TextView reqContactNoText, @NonNull EditText reqInstiName,
      @NonNull TextView reqInstiNameText, @NonNull EditText reqInstiType,
      @NonNull TextView reqInstiTypeText, @NonNull TextView reqLocText,
      @NonNull EditText reqLocation) {
    this.rootView = rootView;
    this.reqBtnRequest = reqBtnRequest;
    this.reqContactNo = reqContactNo;
    this.reqContactNoText = reqContactNoText;
    this.reqInstiName = reqInstiName;
    this.reqInstiNameText = reqInstiNameText;
    this.reqInstiType = reqInstiType;
    this.reqInstiTypeText = reqInstiTypeText;
    this.reqLocText = reqLocText;
    this.reqLocation = reqLocation;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentUpdatePartnershipBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentUpdatePartnershipBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_update_partnership, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentUpdatePartnershipBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.reqBtnRequest;
      Button reqBtnRequest = ViewBindings.findChildViewById(rootView, id);
      if (reqBtnRequest == null) {
        break missingId;
      }

      id = R.id.reqContactNo;
      EditText reqContactNo = ViewBindings.findChildViewById(rootView, id);
      if (reqContactNo == null) {
        break missingId;
      }

      id = R.id.reqContactNoText;
      TextView reqContactNoText = ViewBindings.findChildViewById(rootView, id);
      if (reqContactNoText == null) {
        break missingId;
      }

      id = R.id.reqInstiName;
      EditText reqInstiName = ViewBindings.findChildViewById(rootView, id);
      if (reqInstiName == null) {
        break missingId;
      }

      id = R.id.reqInstiNameText;
      TextView reqInstiNameText = ViewBindings.findChildViewById(rootView, id);
      if (reqInstiNameText == null) {
        break missingId;
      }

      id = R.id.reqInstiType;
      EditText reqInstiType = ViewBindings.findChildViewById(rootView, id);
      if (reqInstiType == null) {
        break missingId;
      }

      id = R.id.reqInstiTypeText;
      TextView reqInstiTypeText = ViewBindings.findChildViewById(rootView, id);
      if (reqInstiTypeText == null) {
        break missingId;
      }

      id = R.id.reqLocText;
      TextView reqLocText = ViewBindings.findChildViewById(rootView, id);
      if (reqLocText == null) {
        break missingId;
      }

      id = R.id.reqLocation;
      EditText reqLocation = ViewBindings.findChildViewById(rootView, id);
      if (reqLocation == null) {
        break missingId;
      }

      return new FragmentUpdatePartnershipBinding((LinearLayout) rootView, reqBtnRequest,
          reqContactNo, reqContactNoText, reqInstiName, reqInstiNameText, reqInstiType,
          reqInstiTypeText, reqLocText, reqLocation);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
