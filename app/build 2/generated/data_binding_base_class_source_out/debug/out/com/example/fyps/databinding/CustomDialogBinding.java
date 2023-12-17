// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CustomDialogBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton btnCancel;

  @NonNull
  public final AppCompatButton btnOkay;

  @NonNull
  public final View divider;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView2;

  private CustomDialogBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton btnCancel, @NonNull AppCompatButton btnOkay, @NonNull View divider,
      @NonNull ImageView imageView, @NonNull TextView textView, @NonNull TextView textView2) {
    this.rootView = rootView;
    this.btnCancel = btnCancel;
    this.btnOkay = btnOkay;
    this.divider = divider;
    this.imageView = imageView;
    this.textView = textView;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CustomDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CustomDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.custom_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CustomDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_cancel;
      AppCompatButton btnCancel = ViewBindings.findChildViewById(rootView, id);
      if (btnCancel == null) {
        break missingId;
      }

      id = R.id.btn_okay;
      AppCompatButton btnOkay = ViewBindings.findChildViewById(rootView, id);
      if (btnOkay == null) {
        break missingId;
      }

      id = R.id.divider;
      View divider = ViewBindings.findChildViewById(rootView, id);
      if (divider == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      return new CustomDialogBinding((ConstraintLayout) rootView, btnCancel, btnOkay, divider,
          imageView, textView, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
