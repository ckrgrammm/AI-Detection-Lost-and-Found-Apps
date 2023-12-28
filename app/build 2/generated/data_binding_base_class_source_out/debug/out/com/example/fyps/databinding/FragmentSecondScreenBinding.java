// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class FragmentSecondScreenBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton btnSplashRegister;

  @NonNull
  public final AppCompatButton btnSplashSignIn;

  @NonNull
  public final TextView tvAppName;

  @NonNull
  public final TextView tvBestQuality;

  @NonNull
  public final TextView tvRightPlace;

  private FragmentSecondScreenBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton btnSplashRegister, @NonNull AppCompatButton btnSplashSignIn,
      @NonNull TextView tvAppName, @NonNull TextView tvBestQuality,
      @NonNull TextView tvRightPlace) {
    this.rootView = rootView;
    this.btnSplashRegister = btnSplashRegister;
    this.btnSplashSignIn = btnSplashSignIn;
    this.tvAppName = tvAppName;
    this.tvBestQuality = tvBestQuality;
    this.tvRightPlace = tvRightPlace;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSecondScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSecondScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_second_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSecondScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_splash_register;
      AppCompatButton btnSplashRegister = ViewBindings.findChildViewById(rootView, id);
      if (btnSplashRegister == null) {
        break missingId;
      }

      id = R.id.btn_splash_sign_in;
      AppCompatButton btnSplashSignIn = ViewBindings.findChildViewById(rootView, id);
      if (btnSplashSignIn == null) {
        break missingId;
      }

      id = R.id.tv_app_name;
      TextView tvAppName = ViewBindings.findChildViewById(rootView, id);
      if (tvAppName == null) {
        break missingId;
      }

      id = R.id.tv_best_quality;
      TextView tvBestQuality = ViewBindings.findChildViewById(rootView, id);
      if (tvBestQuality == null) {
        break missingId;
      }

      id = R.id.tv_right_place;
      TextView tvRightPlace = ViewBindings.findChildViewById(rootView, id);
      if (tvRightPlace == null) {
        break missingId;
      }

      return new FragmentSecondScreenBinding((ConstraintLayout) rootView, btnSplashRegister,
          btnSplashSignIn, tvAppName, tvBestQuality, tvRightPlace);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}