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

public final class FragmentItemSettingBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Guideline guideline1;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final ImageView imgEmptyBox;

  @NonNull
  public final ImageView imgEmptyBoxTexture;

  @NonNull
  public final ProgressBar progressbarAllOrders;

  @NonNull
  public final RecyclerView rvItemSettings;

  @NonNull
  public final Toolbar toolbarAllOrders;

  @NonNull
  public final TextView tvEmptyOrders;

  private FragmentItemSettingBinding(@NonNull ConstraintLayout rootView,
      @NonNull Guideline guideline1, @NonNull Guideline guideline2, @NonNull ImageView imgEmptyBox,
      @NonNull ImageView imgEmptyBoxTexture, @NonNull ProgressBar progressbarAllOrders,
      @NonNull RecyclerView rvItemSettings, @NonNull Toolbar toolbarAllOrders,
      @NonNull TextView tvEmptyOrders) {
    this.rootView = rootView;
    this.guideline1 = guideline1;
    this.guideline2 = guideline2;
    this.imgEmptyBox = imgEmptyBox;
    this.imgEmptyBoxTexture = imgEmptyBoxTexture;
    this.progressbarAllOrders = progressbarAllOrders;
    this.rvItemSettings = rvItemSettings;
    this.toolbarAllOrders = toolbarAllOrders;
    this.tvEmptyOrders = tvEmptyOrders;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentItemSettingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentItemSettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_item_setting, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentItemSettingBinding bind(@NonNull View rootView) {
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

      id = R.id.rvItemSettings;
      RecyclerView rvItemSettings = ViewBindings.findChildViewById(rootView, id);
      if (rvItemSettings == null) {
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

      return new FragmentItemSettingBinding((ConstraintLayout) rootView, guideline1, guideline2,
          imgEmptyBox, imgEmptyBoxTexture, progressbarAllOrders, rvItemSettings, toolbarAllOrders,
          tvEmptyOrders);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
