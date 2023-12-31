// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHome3232Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FrameLayout fragmeMicrohpone;

  @NonNull
  public final FrameLayout frameAdd;

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
  public final Guideline guideline8;

  @NonNull
  public final CardView homeCard;

  @NonNull
  public final ImageView imgAdd;

  @NonNull
  public final ImageView imgMic;

  @NonNull
  public final ImageView linearSvg;

  @NonNull
  public final TextView textSale;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView tvSearch;

  private FragmentHome3232Binding(@NonNull ConstraintLayout rootView,
      @NonNull FrameLayout fragmeMicrohpone, @NonNull FrameLayout frameAdd,
      @NonNull Guideline guideline1, @NonNull Guideline guideline2, @NonNull Guideline guideline3,
      @NonNull Guideline guideline4, @NonNull Guideline guideline5, @NonNull Guideline guideline8,
      @NonNull CardView homeCard, @NonNull ImageView imgAdd, @NonNull ImageView imgMic,
      @NonNull ImageView linearSvg, @NonNull TextView textSale, @NonNull TextView textView3,
      @NonNull TextView tvSearch) {
    this.rootView = rootView;
    this.fragmeMicrohpone = fragmeMicrohpone;
    this.frameAdd = frameAdd;
    this.guideline1 = guideline1;
    this.guideline2 = guideline2;
    this.guideline3 = guideline3;
    this.guideline4 = guideline4;
    this.guideline5 = guideline5;
    this.guideline8 = guideline8;
    this.homeCard = homeCard;
    this.imgAdd = imgAdd;
    this.imgMic = imgMic;
    this.linearSvg = linearSvg;
    this.textSale = textSale;
    this.textView3 = textView3;
    this.tvSearch = tvSearch;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHome3232Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHome3232Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home3232, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHome3232Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fragme_microhpone;
      FrameLayout fragmeMicrohpone = ViewBindings.findChildViewById(rootView, id);
      if (fragmeMicrohpone == null) {
        break missingId;
      }

      id = R.id.frame_add;
      FrameLayout frameAdd = ViewBindings.findChildViewById(rootView, id);
      if (frameAdd == null) {
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

      id = R.id.guideline8;
      Guideline guideline8 = ViewBindings.findChildViewById(rootView, id);
      if (guideline8 == null) {
        break missingId;
      }

      id = R.id.home_card;
      CardView homeCard = ViewBindings.findChildViewById(rootView, id);
      if (homeCard == null) {
        break missingId;
      }

      id = R.id.img_add;
      ImageView imgAdd = ViewBindings.findChildViewById(rootView, id);
      if (imgAdd == null) {
        break missingId;
      }

      id = R.id.img_mic;
      ImageView imgMic = ViewBindings.findChildViewById(rootView, id);
      if (imgMic == null) {
        break missingId;
      }

      id = R.id.linear_svg;
      ImageView linearSvg = ViewBindings.findChildViewById(rootView, id);
      if (linearSvg == null) {
        break missingId;
      }

      id = R.id.text_sale;
      TextView textSale = ViewBindings.findChildViewById(rootView, id);
      if (textSale == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.tv_search;
      TextView tvSearch = ViewBindings.findChildViewById(rootView, id);
      if (tvSearch == null) {
        break missingId;
      }

      return new FragmentHome3232Binding((ConstraintLayout) rootView, fragmeMicrohpone, frameAdd,
          guideline1, guideline2, guideline3, guideline4, guideline5, guideline8, homeCard, imgAdd,
          imgMic, linearSvg, textSale, textView3, tvSearch);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
