// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class UpdatePopupBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnUpdate;

  @NonNull
  public final EditText txtCourse;

  @NonNull
  public final EditText txtEmail;

  @NonNull
  public final EditText txtImageUrl;

  @NonNull
  public final EditText txtName;

  private UpdatePopupBinding(@NonNull LinearLayout rootView, @NonNull Button btnUpdate,
      @NonNull EditText txtCourse, @NonNull EditText txtEmail, @NonNull EditText txtImageUrl,
      @NonNull EditText txtName) {
    this.rootView = rootView;
    this.btnUpdate = btnUpdate;
    this.txtCourse = txtCourse;
    this.txtEmail = txtEmail;
    this.txtImageUrl = txtImageUrl;
    this.txtName = txtName;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static UpdatePopupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static UpdatePopupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.update_popup, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static UpdatePopupBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnUpdate;
      Button btnUpdate = ViewBindings.findChildViewById(rootView, id);
      if (btnUpdate == null) {
        break missingId;
      }

      id = R.id.txtCourse;
      EditText txtCourse = ViewBindings.findChildViewById(rootView, id);
      if (txtCourse == null) {
        break missingId;
      }

      id = R.id.txtEmail;
      EditText txtEmail = ViewBindings.findChildViewById(rootView, id);
      if (txtEmail == null) {
        break missingId;
      }

      id = R.id.txtImageUrl;
      EditText txtImageUrl = ViewBindings.findChildViewById(rootView, id);
      if (txtImageUrl == null) {
        break missingId;
      }

      id = R.id.txtName;
      EditText txtName = ViewBindings.findChildViewById(rootView, id);
      if (txtName == null) {
        break missingId;
      }

      return new UpdatePopupBinding((LinearLayout) rootView, btnUpdate, txtCourse, txtEmail,
          txtImageUrl, txtName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
