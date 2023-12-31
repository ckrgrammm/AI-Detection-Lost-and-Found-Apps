// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AdminDashboardBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final MaterialCardView addEbook;

  @NonNull
  public final MaterialCardView delete;

  @NonNull
  public final MaterialCardView faculty;

  @NonNull
  public final MaterialCardView logout;

  @NonNull
  public final MaterialCardView news;

  @NonNull
  public final MaterialCardView student;

  @NonNull
  public final MaterialCardView uploadImage;

  @NonNull
  public final MaterialCardView uploadNotice;

  private AdminDashboardBinding(@NonNull ScrollView rootView, @NonNull MaterialCardView addEbook,
      @NonNull MaterialCardView delete, @NonNull MaterialCardView faculty,
      @NonNull MaterialCardView logout, @NonNull MaterialCardView news,
      @NonNull MaterialCardView student, @NonNull MaterialCardView uploadImage,
      @NonNull MaterialCardView uploadNotice) {
    this.rootView = rootView;
    this.addEbook = addEbook;
    this.delete = delete;
    this.faculty = faculty;
    this.logout = logout;
    this.news = news;
    this.student = student;
    this.uploadImage = uploadImage;
    this.uploadNotice = uploadNotice;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static AdminDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AdminDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.admin_dashboard, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AdminDashboardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addEbook;
      MaterialCardView addEbook = ViewBindings.findChildViewById(rootView, id);
      if (addEbook == null) {
        break missingId;
      }

      id = R.id.delete;
      MaterialCardView delete = ViewBindings.findChildViewById(rootView, id);
      if (delete == null) {
        break missingId;
      }

      id = R.id.faculty;
      MaterialCardView faculty = ViewBindings.findChildViewById(rootView, id);
      if (faculty == null) {
        break missingId;
      }

      id = R.id.logout;
      MaterialCardView logout = ViewBindings.findChildViewById(rootView, id);
      if (logout == null) {
        break missingId;
      }

      id = R.id.news;
      MaterialCardView news = ViewBindings.findChildViewById(rootView, id);
      if (news == null) {
        break missingId;
      }

      id = R.id.student;
      MaterialCardView student = ViewBindings.findChildViewById(rootView, id);
      if (student == null) {
        break missingId;
      }

      id = R.id.uploadImage;
      MaterialCardView uploadImage = ViewBindings.findChildViewById(rootView, id);
      if (uploadImage == null) {
        break missingId;
      }

      id = R.id.uploadNotice;
      MaterialCardView uploadNotice = ViewBindings.findChildViewById(rootView, id);
      if (uploadNotice == null) {
        break missingId;
      }

      return new AdminDashboardBinding((ScrollView) rootView, addEbook, delete, faculty, logout,
          news, student, uploadImage, uploadNotice);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
