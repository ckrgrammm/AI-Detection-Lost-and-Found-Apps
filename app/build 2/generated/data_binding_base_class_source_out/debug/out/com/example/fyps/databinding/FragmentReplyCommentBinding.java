// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public final class FragmentReplyCommentBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView commentDetailDateName;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final Button postDetailAddCommentBtn;

  @NonNull
  public final EditText postDetailComment;

  @NonNull
  public final ImageView postDetailUserImg;

  @NonNull
  public final TextView rating;

  @NonNull
  public final LinearLayout ratingLayout;

  @NonNull
  public final ImageView replier;

  @NonNull
  public final ImageView subjectImg;

  @NonNull
  public final TextView subjectTitle;

  @NonNull
  public final LinearLayout textTitleLayout;

  @NonNull
  public final TextView userCommentText;

  @NonNull
  public final View view;

  private FragmentReplyCommentBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView commentDetailDateName, @NonNull ImageView imageView2,
      @NonNull Button postDetailAddCommentBtn, @NonNull EditText postDetailComment,
      @NonNull ImageView postDetailUserImg, @NonNull TextView rating,
      @NonNull LinearLayout ratingLayout, @NonNull ImageView replier, @NonNull ImageView subjectImg,
      @NonNull TextView subjectTitle, @NonNull LinearLayout textTitleLayout,
      @NonNull TextView userCommentText, @NonNull View view) {
    this.rootView = rootView;
    this.commentDetailDateName = commentDetailDateName;
    this.imageView2 = imageView2;
    this.postDetailAddCommentBtn = postDetailAddCommentBtn;
    this.postDetailComment = postDetailComment;
    this.postDetailUserImg = postDetailUserImg;
    this.rating = rating;
    this.ratingLayout = ratingLayout;
    this.replier = replier;
    this.subjectImg = subjectImg;
    this.subjectTitle = subjectTitle;
    this.textTitleLayout = textTitleLayout;
    this.userCommentText = userCommentText;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentReplyCommentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentReplyCommentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_reply_comment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentReplyCommentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.comment_detail_date_name;
      TextView commentDetailDateName = ViewBindings.findChildViewById(rootView, id);
      if (commentDetailDateName == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.post_detail_add_comment_btn;
      Button postDetailAddCommentBtn = ViewBindings.findChildViewById(rootView, id);
      if (postDetailAddCommentBtn == null) {
        break missingId;
      }

      id = R.id.post_detail_comment;
      EditText postDetailComment = ViewBindings.findChildViewById(rootView, id);
      if (postDetailComment == null) {
        break missingId;
      }

      id = R.id.post_detail_user_img;
      ImageView postDetailUserImg = ViewBindings.findChildViewById(rootView, id);
      if (postDetailUserImg == null) {
        break missingId;
      }

      id = R.id.rating;
      TextView rating = ViewBindings.findChildViewById(rootView, id);
      if (rating == null) {
        break missingId;
      }

      id = R.id.ratingLayout;
      LinearLayout ratingLayout = ViewBindings.findChildViewById(rootView, id);
      if (ratingLayout == null) {
        break missingId;
      }

      id = R.id.replier;
      ImageView replier = ViewBindings.findChildViewById(rootView, id);
      if (replier == null) {
        break missingId;
      }

      id = R.id.subject_img;
      ImageView subjectImg = ViewBindings.findChildViewById(rootView, id);
      if (subjectImg == null) {
        break missingId;
      }

      id = R.id.subject_title;
      TextView subjectTitle = ViewBindings.findChildViewById(rootView, id);
      if (subjectTitle == null) {
        break missingId;
      }

      id = R.id.textTitleLayout;
      LinearLayout textTitleLayout = ViewBindings.findChildViewById(rootView, id);
      if (textTitleLayout == null) {
        break missingId;
      }

      id = R.id.user_comment_text;
      TextView userCommentText = ViewBindings.findChildViewById(rootView, id);
      if (userCommentText == null) {
        break missingId;
      }

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);
      if (view == null) {
        break missingId;
      }

      return new FragmentReplyCommentBinding((ConstraintLayout) rootView, commentDetailDateName,
          imageView2, postDetailAddCommentBtn, postDetailComment, postDetailUserImg, rating,
          ratingLayout, replier, subjectImg, subjectTitle, textTitleLayout, userCommentText, view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}