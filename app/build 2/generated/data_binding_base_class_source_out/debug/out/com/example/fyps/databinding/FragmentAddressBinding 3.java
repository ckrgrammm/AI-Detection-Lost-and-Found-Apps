// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAddressBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton btnAddNewAddress;

  @NonNull
  public final AppCompatButton btnDelete;

  @NonNull
  public final EditText edAddressTitle;

  @NonNull
  public final EditText edCity;

  @NonNull
  public final EditText edFullName;

  @NonNull
  public final EditText edPhone;

  @NonNull
  public final EditText edState;

  @NonNull
  public final EditText edStreet;

  @NonNull
  public final Guideline guideline1;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final ImageView imgAddressClose;

  @NonNull
  public final View line;

  @NonNull
  public final LinearLayout linear;

  @NonNull
  public final ProgressBar progressbarAddress;

  @NonNull
  public final Toolbar toolbarAddress;

  @NonNull
  public final TextView tvPaymentMethods;

  private FragmentAddressBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton btnAddNewAddress, @NonNull AppCompatButton btnDelete,
      @NonNull EditText edAddressTitle, @NonNull EditText edCity, @NonNull EditText edFullName,
      @NonNull EditText edPhone, @NonNull EditText edState, @NonNull EditText edStreet,
      @NonNull Guideline guideline1, @NonNull Guideline guideline2,
      @NonNull ImageView imgAddressClose, @NonNull View line, @NonNull LinearLayout linear,
      @NonNull ProgressBar progressbarAddress, @NonNull Toolbar toolbarAddress,
      @NonNull TextView tvPaymentMethods) {
    this.rootView = rootView;
    this.btnAddNewAddress = btnAddNewAddress;
    this.btnDelete = btnDelete;
    this.edAddressTitle = edAddressTitle;
    this.edCity = edCity;
    this.edFullName = edFullName;
    this.edPhone = edPhone;
    this.edState = edState;
    this.edStreet = edStreet;
    this.guideline1 = guideline1;
    this.guideline2 = guideline2;
    this.imgAddressClose = imgAddressClose;
    this.line = line;
    this.linear = linear;
    this.progressbarAddress = progressbarAddress;
    this.toolbarAddress = toolbarAddress;
    this.tvPaymentMethods = tvPaymentMethods;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAddressBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_address, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAddressBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_add_new_address;
      AppCompatButton btnAddNewAddress = ViewBindings.findChildViewById(rootView, id);
      if (btnAddNewAddress == null) {
        break missingId;
      }

      id = R.id.btn_delete;
      AppCompatButton btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.ed_address_title;
      EditText edAddressTitle = ViewBindings.findChildViewById(rootView, id);
      if (edAddressTitle == null) {
        break missingId;
      }

      id = R.id.ed_city;
      EditText edCity = ViewBindings.findChildViewById(rootView, id);
      if (edCity == null) {
        break missingId;
      }

      id = R.id.ed_full_name;
      EditText edFullName = ViewBindings.findChildViewById(rootView, id);
      if (edFullName == null) {
        break missingId;
      }

      id = R.id.ed_phone;
      EditText edPhone = ViewBindings.findChildViewById(rootView, id);
      if (edPhone == null) {
        break missingId;
      }

      id = R.id.ed_state;
      EditText edState = ViewBindings.findChildViewById(rootView, id);
      if (edState == null) {
        break missingId;
      }

      id = R.id.ed_street;
      EditText edStreet = ViewBindings.findChildViewById(rootView, id);
      if (edStreet == null) {
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

      id = R.id.img_address_close;
      ImageView imgAddressClose = ViewBindings.findChildViewById(rootView, id);
      if (imgAddressClose == null) {
        break missingId;
      }

      id = R.id.line;
      View line = ViewBindings.findChildViewById(rootView, id);
      if (line == null) {
        break missingId;
      }

      id = R.id.linear;
      LinearLayout linear = ViewBindings.findChildViewById(rootView, id);
      if (linear == null) {
        break missingId;
      }

      id = R.id.progressbar_address;
      ProgressBar progressbarAddress = ViewBindings.findChildViewById(rootView, id);
      if (progressbarAddress == null) {
        break missingId;
      }

      id = R.id.toolbar_address;
      Toolbar toolbarAddress = ViewBindings.findChildViewById(rootView, id);
      if (toolbarAddress == null) {
        break missingId;
      }

      id = R.id.tv_payment_methods;
      TextView tvPaymentMethods = ViewBindings.findChildViewById(rootView, id);
      if (tvPaymentMethods == null) {
        break missingId;
      }

      return new FragmentAddressBinding((ConstraintLayout) rootView, btnAddNewAddress, btnDelete,
          edAddressTitle, edCity, edFullName, edPhone, edState, edStreet, guideline1, guideline2,
          imgAddressClose, line, linear, progressbarAddress, toolbarAddress, tvPaymentMethods);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}