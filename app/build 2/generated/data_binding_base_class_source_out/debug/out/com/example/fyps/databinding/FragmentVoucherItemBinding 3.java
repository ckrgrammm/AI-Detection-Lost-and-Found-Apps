// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentVoucherItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final Button buttonRedeem;

  @NonNull
  public final Guideline guideline3;

  @NonNull
  public final ImageView imageViewVoucher;

  @NonNull
  public final TextView textViewRewardDescription;

  @NonNull
  public final TextView textViewVoucherName;

  private FragmentVoucherItemBinding(@NonNull CardView rootView, @NonNull Button buttonRedeem,
      @NonNull Guideline guideline3, @NonNull ImageView imageViewVoucher,
      @NonNull TextView textViewRewardDescription, @NonNull TextView textViewVoucherName) {
    this.rootView = rootView;
    this.buttonRedeem = buttonRedeem;
    this.guideline3 = guideline3;
    this.imageViewVoucher = imageViewVoucher;
    this.textViewRewardDescription = textViewRewardDescription;
    this.textViewVoucherName = textViewVoucherName;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentVoucherItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentVoucherItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_voucher_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentVoucherItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonRedeem;
      Button buttonRedeem = ViewBindings.findChildViewById(rootView, id);
      if (buttonRedeem == null) {
        break missingId;
      }

      id = R.id.guideline3;
      Guideline guideline3 = ViewBindings.findChildViewById(rootView, id);
      if (guideline3 == null) {
        break missingId;
      }

      id = R.id.imageViewVoucher;
      ImageView imageViewVoucher = ViewBindings.findChildViewById(rootView, id);
      if (imageViewVoucher == null) {
        break missingId;
      }

      id = R.id.textViewRewardDescription;
      TextView textViewRewardDescription = ViewBindings.findChildViewById(rootView, id);
      if (textViewRewardDescription == null) {
        break missingId;
      }

      id = R.id.textViewVoucherName;
      TextView textViewVoucherName = ViewBindings.findChildViewById(rootView, id);
      if (textViewVoucherName == null) {
        break missingId;
      }

      return new FragmentVoucherItemBinding((CardView) rootView, buttonRedeem, guideline3,
          imageViewVoucher, textViewRewardDescription, textViewVoucherName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
