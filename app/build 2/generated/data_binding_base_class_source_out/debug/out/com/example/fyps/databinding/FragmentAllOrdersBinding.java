// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAllOrdersBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Guideline guideline1;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final ImageView imgCloseOrders;

  @NonNull
  public final ImageView imgEmptyBox;

  @NonNull
  public final ImageView imgEmptyBoxTexture;

  @NonNull
  public final ProgressBar progressbarAllOrders;

  @NonNull
  public final RecyclerView rvAllOrders;

  @NonNull
  public final Toolbar toolbarAllOrders;

  @NonNull
  public final TextView tvEmptyOrders;

  private FragmentAllOrdersBinding(@NonNull ConstraintLayout rootView,
      @NonNull Guideline guideline1, @NonNull Guideline guideline2,
      @NonNull ImageView imgCloseOrders, @NonNull ImageView imgEmptyBox,
      @NonNull ImageView imgEmptyBoxTexture, @NonNull ProgressBar progressbarAllOrders,
      @NonNull RecyclerView rvAllOrders, @NonNull Toolbar toolbarAllOrders,
      @NonNull TextView tvEmptyOrders) {
    this.rootView = rootView;
    this.guideline1 = guideline1;
    this.guideline2 = guideline2;
    this.imgCloseOrders = imgCloseOrders;
    this.imgEmptyBox = imgEmptyBox;
    this.imgEmptyBoxTexture = imgEmptyBoxTexture;
    this.progressbarAllOrders = progressbarAllOrders;
    this.rvAllOrders = rvAllOrders;
    this.toolbarAllOrders = toolbarAllOrders;
    this.tvEmptyOrders = tvEmptyOrders;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAllOrdersBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAllOrdersBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_all_orders, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAllOrdersBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
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

      id = R.id.img_close_orders;
      ImageView imgCloseOrders = ViewBindings.findChildViewById(rootView, id);
      if (imgCloseOrders == null) {
        break missingId;
      }

      id = R.id.img_empty_box;
      ImageView imgEmptyBox = ViewBindings.findChildViewById(rootView, id);
      if (imgEmptyBox == null) {
        break missingId;
      }

      id = R.id.img_empty_box_texture;
      ImageView imgEmptyBoxTexture = ViewBindings.findChildViewById(rootView, id);
      if (imgEmptyBoxTexture == null) {
        break missingId;
      }

      id = R.id.progressbar_all_orders;
      ProgressBar progressbarAllOrders = ViewBindings.findChildViewById(rootView, id);
      if (progressbarAllOrders == null) {
        break missingId;
      }

      id = R.id.rv_all_orders;
      RecyclerView rvAllOrders = ViewBindings.findChildViewById(rootView, id);
      if (rvAllOrders == null) {
        break missingId;
      }

      id = R.id.toolbar_all_orders;
      Toolbar toolbarAllOrders = ViewBindings.findChildViewById(rootView, id);
      if (toolbarAllOrders == null) {
        break missingId;
      }

      id = R.id.tv_empty_orders;
      TextView tvEmptyOrders = ViewBindings.findChildViewById(rootView, id);
      if (tvEmptyOrders == null) {
        break missingId;
      }

      return new FragmentAllOrdersBinding((ConstraintLayout) rootView, guideline1, guideline2,
          imgCloseOrders, imgEmptyBox, imgEmptyBoxTexture, progressbarAllOrders, rvAllOrders,
          toolbarAllOrders, tvEmptyOrders);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}