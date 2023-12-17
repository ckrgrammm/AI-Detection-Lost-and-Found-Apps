// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class PartnerItemSetsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView setName;

  @NonNull
  public final TextView setNumber;

  private PartnerItemSetsBinding(@NonNull ConstraintLayout rootView, @NonNull TextView setName,
      @NonNull TextView setNumber) {
    this.rootView = rootView;
    this.setName = setName;
    this.setNumber = setNumber;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PartnerItemSetsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PartnerItemSetsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.partner_item_sets, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PartnerItemSetsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.setName;
      TextView setName = ViewBindings.findChildViewById(rootView, id);
      if (setName == null) {
        break missingId;
      }

      id = R.id.setNumber;
      TextView setNumber = ViewBindings.findChildViewById(rootView, id);
      if (setNumber == null) {
        break missingId;
      }

      return new PartnerItemSetsBinding((ConstraintLayout) rootView, setName, setNumber);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
