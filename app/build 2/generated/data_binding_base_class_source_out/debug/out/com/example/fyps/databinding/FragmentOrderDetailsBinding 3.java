// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentOrderDetailsBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final AppCompatButton btnChat;

  @NonNull
  public final Guideline guideline1;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final ImageView imgCloseOrder;

  @NonNull
  public final View line1;

  @NonNull
  public final LinearLayout linearAddress;

  @NonNull
  public final LinearLayout linearStepsView;

  @NonNull
  public final LinearProgressIndicator progressbarOrder;

  @NonNull
  public final AppCompatButton ratingBtn;

  @NonNull
  public final Toolbar toolbarAllOrderDetails;

  @NonNull
  public final TextView tvOrderId;

  @NonNull
  public final TextView tvProducts;

  @NonNull
  public final TextView tvShoppingAddresses;

  private FragmentOrderDetailsBinding(@NonNull NestedScrollView rootView,
      @NonNull AppCompatButton btnChat, @NonNull Guideline guideline1,
      @NonNull Guideline guideline2, @NonNull ImageView imgCloseOrder, @NonNull View line1,
      @NonNull LinearLayout linearAddress, @NonNull LinearLayout linearStepsView,
      @NonNull LinearProgressIndicator progressbarOrder, @NonNull AppCompatButton ratingBtn,
      @NonNull Toolbar toolbarAllOrderDetails, @NonNull TextView tvOrderId,
      @NonNull TextView tvProducts, @NonNull TextView tvShoppingAddresses) {
    this.rootView = rootView;
    this.btnChat = btnChat;
    this.guideline1 = guideline1;
    this.guideline2 = guideline2;
    this.imgCloseOrder = imgCloseOrder;
    this.line1 = line1;
    this.linearAddress = linearAddress;
    this.linearStepsView = linearStepsView;
    this.progressbarOrder = progressbarOrder;
    this.ratingBtn = ratingBtn;
    this.toolbarAllOrderDetails = toolbarAllOrderDetails;
    this.tvOrderId = tvOrderId;
    this.tvProducts = tvProducts;
    this.tvShoppingAddresses = tvShoppingAddresses;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentOrderDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentOrderDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_order_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentOrderDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnChat;
      AppCompatButton btnChat = ViewBindings.findChildViewById(rootView, id);
      if (btnChat == null) {
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

      id = R.id.img_close_order;
      ImageView imgCloseOrder = ViewBindings.findChildViewById(rootView, id);
      if (imgCloseOrder == null) {
        break missingId;
      }

      id = R.id.line1;
      View line1 = ViewBindings.findChildViewById(rootView, id);
      if (line1 == null) {
        break missingId;
      }

      id = R.id.linear_address;
      LinearLayout linearAddress = ViewBindings.findChildViewById(rootView, id);
      if (linearAddress == null) {
        break missingId;
      }

      id = R.id.linear_steps_view;
      LinearLayout linearStepsView = ViewBindings.findChildViewById(rootView, id);
      if (linearStepsView == null) {
        break missingId;
      }

      id = R.id.progressbar_order;
      LinearProgressIndicator progressbarOrder = ViewBindings.findChildViewById(rootView, id);
      if (progressbarOrder == null) {
        break missingId;
      }

      id = R.id.ratingBtn;
      AppCompatButton ratingBtn = ViewBindings.findChildViewById(rootView, id);
      if (ratingBtn == null) {
        break missingId;
      }

      id = R.id.toolbar_all_order_details;
      Toolbar toolbarAllOrderDetails = ViewBindings.findChildViewById(rootView, id);
      if (toolbarAllOrderDetails == null) {
        break missingId;
      }

      id = R.id.tv_order_id;
      TextView tvOrderId = ViewBindings.findChildViewById(rootView, id);
      if (tvOrderId == null) {
        break missingId;
      }

      id = R.id.tv_products;
      TextView tvProducts = ViewBindings.findChildViewById(rootView, id);
      if (tvProducts == null) {
        break missingId;
      }

      id = R.id.tv_shopping_addresses;
      TextView tvShoppingAddresses = ViewBindings.findChildViewById(rootView, id);
      if (tvShoppingAddresses == null) {
        break missingId;
      }

      return new FragmentOrderDetailsBinding((NestedScrollView) rootView, btnChat, guideline1,
          guideline2, imgCloseOrder, line1, linearAddress, linearStepsView, progressbarOrder,
          ratingBtn, toolbarAllOrderDetails, tvOrderId, tvProducts, tvShoppingAddresses);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
