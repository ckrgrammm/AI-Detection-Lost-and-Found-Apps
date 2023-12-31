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

public final class FragmentJoinPartnerBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnJoin;

  @NonNull
  public final Button btnRequest;

  @NonNull
  public final EditText contactNo;

  @NonNull
  public final TextView contactNoText;

  @NonNull
  public final TextView docText;

  @NonNull
  public final Button documentUploadBtn;

  @NonNull
  public final EditText instiName;

  @NonNull
  public final TextView instiNameText;

  @NonNull
  public final EditText instiType;

  @NonNull
  public final TextView instiTypeText;

  @NonNull
  public final TextView locText;

  @NonNull
  public final EditText location;

  @NonNull
  public final EditText reason;

  @NonNull
  public final TextView reasonText;

  @NonNull
  public final TextView rejectText;

  @NonNull
  public final TextView requestedText;

  private FragmentJoinPartnerBinding(@NonNull LinearLayout rootView, @NonNull Button btnJoin,
      @NonNull Button btnRequest, @NonNull EditText contactNo, @NonNull TextView contactNoText,
      @NonNull TextView docText, @NonNull Button documentUploadBtn, @NonNull EditText instiName,
      @NonNull TextView instiNameText, @NonNull EditText instiType, @NonNull TextView instiTypeText,
      @NonNull TextView locText, @NonNull EditText location, @NonNull EditText reason,
      @NonNull TextView reasonText, @NonNull TextView rejectText, @NonNull TextView requestedText) {
    this.rootView = rootView;
    this.btnJoin = btnJoin;
    this.btnRequest = btnRequest;
    this.contactNo = contactNo;
    this.contactNoText = contactNoText;
    this.docText = docText;
    this.documentUploadBtn = documentUploadBtn;
    this.instiName = instiName;
    this.instiNameText = instiNameText;
    this.instiType = instiType;
    this.instiTypeText = instiTypeText;
    this.locText = locText;
    this.location = location;
    this.reason = reason;
    this.reasonText = reasonText;
    this.rejectText = rejectText;
    this.requestedText = requestedText;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentJoinPartnerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentJoinPartnerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_join__partner_, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentJoinPartnerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnJoin;
      Button btnJoin = ViewBindings.findChildViewById(rootView, id);
      if (btnJoin == null) {
        break missingId;
      }

      id = R.id.btnRequest;
      Button btnRequest = ViewBindings.findChildViewById(rootView, id);
      if (btnRequest == null) {
        break missingId;
      }

      id = R.id.contactNo;
      EditText contactNo = ViewBindings.findChildViewById(rootView, id);
      if (contactNo == null) {
        break missingId;
      }

      id = R.id.contactNoText;
      TextView contactNoText = ViewBindings.findChildViewById(rootView, id);
      if (contactNoText == null) {
        break missingId;
      }

      id = R.id.docText;
      TextView docText = ViewBindings.findChildViewById(rootView, id);
      if (docText == null) {
        break missingId;
      }

      id = R.id.documentUploadBtn;
      Button documentUploadBtn = ViewBindings.findChildViewById(rootView, id);
      if (documentUploadBtn == null) {
        break missingId;
      }

      id = R.id.instiName;
      EditText instiName = ViewBindings.findChildViewById(rootView, id);
      if (instiName == null) {
        break missingId;
      }

      id = R.id.instiNameText;
      TextView instiNameText = ViewBindings.findChildViewById(rootView, id);
      if (instiNameText == null) {
        break missingId;
      }

      id = R.id.instiType;
      EditText instiType = ViewBindings.findChildViewById(rootView, id);
      if (instiType == null) {
        break missingId;
      }

      id = R.id.instiTypeText;
      TextView instiTypeText = ViewBindings.findChildViewById(rootView, id);
      if (instiTypeText == null) {
        break missingId;
      }

      id = R.id.locText;
      TextView locText = ViewBindings.findChildViewById(rootView, id);
      if (locText == null) {
        break missingId;
      }

      id = R.id.location;
      EditText location = ViewBindings.findChildViewById(rootView, id);
      if (location == null) {
        break missingId;
      }

      id = R.id.reason;
      EditText reason = ViewBindings.findChildViewById(rootView, id);
      if (reason == null) {
        break missingId;
      }

      id = R.id.reasonText;
      TextView reasonText = ViewBindings.findChildViewById(rootView, id);
      if (reasonText == null) {
        break missingId;
      }

      id = R.id.rejectText;
      TextView rejectText = ViewBindings.findChildViewById(rootView, id);
      if (rejectText == null) {
        break missingId;
      }

      id = R.id.requestedText;
      TextView requestedText = ViewBindings.findChildViewById(rootView, id);
      if (requestedText == null) {
        break missingId;
      }

      return new FragmentJoinPartnerBinding((LinearLayout) rootView, btnJoin, btnRequest, contactNo,
          contactNoText, docText, documentUploadBtn, instiName, instiNameText, instiType,
          instiTypeText, locText, location, reason, reasonText, rejectText, requestedText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
