// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ColorAndSizesBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CircleImageView imgContent;

  @NonNull
  public final ImageView imgDone;

  @NonNull
  public final CircleImageView imgShadow;

  @NonNull
  public final TextView tvSize;

  private ColorAndSizesBinding(@NonNull ConstraintLayout rootView,
      @NonNull CircleImageView imgContent, @NonNull ImageView imgDone,
      @NonNull CircleImageView imgShadow, @NonNull TextView tvSize) {
    this.rootView = rootView;
    this.imgContent = imgContent;
    this.imgDone = imgDone;
    this.imgShadow = imgShadow;
    this.tvSize = tvSize;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ColorAndSizesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ColorAndSizesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.color_and_sizes, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ColorAndSizesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_content;
      CircleImageView imgContent = ViewBindings.findChildViewById(rootView, id);
      if (imgContent == null) {
        break missingId;
      }

      id = R.id.img_done;
      ImageView imgDone = ViewBindings.findChildViewById(rootView, id);
      if (imgDone == null) {
        break missingId;
      }

      id = R.id.img_shadow;
      CircleImageView imgShadow = ViewBindings.findChildViewById(rootView, id);
      if (imgShadow == null) {
        break missingId;
      }

      id = R.id.tv_size;
      TextView tvSize = ViewBindings.findChildViewById(rootView, id);
      if (tvSize == null) {
        break missingId;
      }

      return new ColorAndSizesBinding((ConstraintLayout) rootView, imgContent, imgDone, imgShadow,
          tvSize);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}