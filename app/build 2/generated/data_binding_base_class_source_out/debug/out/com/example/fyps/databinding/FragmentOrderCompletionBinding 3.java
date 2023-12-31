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
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentOrderCompletionBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton btnCompletionAction;

  @NonNull
  public final Guideline guideline1;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final Guideline guideline3;

  @NonNull
  public final Guideline guideline4;

  @NonNull
  public final Guideline guideline5;

  @NonNull
  public final Guideline guideline6;

  @NonNull
  public final ImageView imgCloseOrderCompletion;

  @NonNull
  public final ImageView imgErrorTexture;

  @NonNull
  public final TextView tvOrderFailed;

  @NonNull
  public final TextView tvOrderTrack;

  @NonNull
  public final TextView tvPaymentExplanation;

  private FragmentOrderCompletionBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton btnCompletionAction, @NonNull Guideline guideline1,
      @NonNull Guideline guideline2, @NonNull Guideline guideline3, @NonNull Guideline guideline4,
      @NonNull Guideline guideline5, @NonNull Guideline guideline6,
      @NonNull ImageView imgCloseOrderCompletion, @NonNull ImageView imgErrorTexture,
      @NonNull TextView tvOrderFailed, @NonNull TextView tvOrderTrack,
      @NonNull TextView tvPaymentExplanation) {
    this.rootView = rootView;
    this.btnCompletionAction = btnCompletionAction;
    this.guideline1 = guideline1;
    this.guideline2 = guideline2;
    this.guideline3 = guideline3;
    this.guideline4 = guideline4;
    this.guideline5 = guideline5;
    this.guideline6 = guideline6;
    this.imgCloseOrderCompletion = imgCloseOrderCompletion;
    this.imgErrorTexture = imgErrorTexture;
    this.tvOrderFailed = tvOrderFailed;
    this.tvOrderTrack = tvOrderTrack;
    this.tvPaymentExplanation = tvPaymentExplanation;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentOrderCompletionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentOrderCompletionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_order_completion, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentOrderCompletionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_completion_action;
      AppCompatButton btnCompletionAction = ViewBindings.findChildViewById(rootView, id);
      if (btnCompletionAction == null) {
        break missingId;
      }

      id = R.id.guideline1;
      Guideline guideline1 = ViewBindings.findChildViewById(rootView, id);
      if (guideline1 == null) {
        break missingId;
      }

      id = R.id.guideline2;
      Guideline guideline2 = ViewBindings.findChildViewById(rootView, id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.guideline3;
      Guideline guideline3 = ViewBindings.findChildViewById(rootView, id);
      if (guideline3 == null) {
        break missingId;
      }

      id = R.id.guideline4;
      Guideline guideline4 = ViewBindings.findChildViewById(rootView, id);
      if (guideline4 == null) {
        break missingId;
      }

      id = R.id.guideline5;
      Guideline guideline5 = ViewBindings.findChildViewById(rootView, id);
      if (guideline5 == null) {
        break missingId;
      }

      id = R.id.guideline6;
      Guideline guideline6 = ViewBindings.findChildViewById(rootView, id);
      if (guideline6 == null) {
        break missingId;
      }

      id = R.id.img_close_order_completion;
      ImageView imgCloseOrderCompletion = ViewBindings.findChildViewById(rootView, id);
      if (imgCloseOrderCompletion == null) {
        break missingId;
      }

      id = R.id.img_error_texture;
      ImageView imgErrorTexture = ViewBindings.findChildViewById(rootView, id);
      if (imgErrorTexture == null) {
        break missingId;
      }

      id = R.id.tv_order_failed;
      TextView tvOrderFailed = ViewBindings.findChildViewById(rootView, id);
      if (tvOrderFailed == null) {
        break missingId;
      }

      id = R.id.tv_order_track;
      TextView tvOrderTrack = ViewBindings.findChildViewById(rootView, id);
      if (tvOrderTrack == null) {
        break missingId;
      }

      id = R.id.tv_payment_explanation;
      TextView tvPaymentExplanation = ViewBindings.findChildViewById(rootView, id);
      if (tvPaymentExplanation == null) {
        break missingId;
      }

      return new FragmentOrderCompletionBinding((ConstraintLayout) rootView, btnCompletionAction,
          guideline1, guideline2, guideline3, guideline4, guideline5, guideline6,
          imgCloseOrderCompletion, imgErrorTexture, tvOrderFailed, tvOrderTrack,
          tvPaymentExplanation);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
