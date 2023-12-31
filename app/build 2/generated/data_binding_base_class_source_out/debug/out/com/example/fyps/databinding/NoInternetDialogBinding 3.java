// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NoInternetDialogBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatButton btnOk;

  @NonNull
  public final ImageView noInternet;

  @NonNull
  public final TextView noInternetText;

  @NonNull
  public final ImageView shadow;

  private NoInternetDialogBinding(@NonNull RelativeLayout rootView, @NonNull AppCompatButton btnOk,
      @NonNull ImageView noInternet, @NonNull TextView noInternetText, @NonNull ImageView shadow) {
    this.rootView = rootView;
    this.btnOk = btnOk;
    this.noInternet = noInternet;
    this.noInternetText = noInternetText;
    this.shadow = shadow;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NoInternetDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NoInternetDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.no_internet_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NoInternetDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_ok;
      AppCompatButton btnOk = ViewBindings.findChildViewById(rootView, id);
      if (btnOk == null) {
        break missingId;
      }

      id = R.id.noInternet;
      ImageView noInternet = ViewBindings.findChildViewById(rootView, id);
      if (noInternet == null) {
        break missingId;
      }

      id = R.id.noInternetText;
      TextView noInternetText = ViewBindings.findChildViewById(rootView, id);
      if (noInternetText == null) {
        break missingId;
      }

      id = R.id.shadow;
      ImageView shadow = ViewBindings.findChildViewById(rootView, id);
      if (shadow == null) {
        break missingId;
      }

      return new NoInternetDialogBinding((RelativeLayout) rootView, btnOk, noInternet,
          noInternetText, shadow);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
