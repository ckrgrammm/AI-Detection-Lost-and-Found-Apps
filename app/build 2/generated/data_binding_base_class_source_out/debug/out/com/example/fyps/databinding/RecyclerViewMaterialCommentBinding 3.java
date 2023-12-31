// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RecyclerViewMaterialCommentBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton addCommentButton;

  @NonNull
  public final EditText addCommentEditText;

  @NonNull
  public final TextView adminReply;

  @NonNull
  public final TextView commentTimestamp;

  @NonNull
  public final TextView noCommentsTextView;

  @NonNull
  public final CircleImageView partnerImage;

  @NonNull
  public final TextView replyTimestamp;

  @NonNull
  public final TextView userComment;

  @NonNull
  public final CircleImageView userImage;

  @NonNull
  public final TextView userName;

  private RecyclerViewMaterialCommentBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton addCommentButton, @NonNull EditText addCommentEditText,
      @NonNull TextView adminReply, @NonNull TextView commentTimestamp,
      @NonNull TextView noCommentsTextView, @NonNull CircleImageView partnerImage,
      @NonNull TextView replyTimestamp, @NonNull TextView userComment,
      @NonNull CircleImageView userImage, @NonNull TextView userName) {
    this.rootView = rootView;
    this.addCommentButton = addCommentButton;
    this.addCommentEditText = addCommentEditText;
    this.adminReply = adminReply;
    this.commentTimestamp = commentTimestamp;
    this.noCommentsTextView = noCommentsTextView;
    this.partnerImage = partnerImage;
    this.replyTimestamp = replyTimestamp;
    this.userComment = userComment;
    this.userImage = userImage;
    this.userName = userName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static RecyclerViewMaterialCommentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RecyclerViewMaterialCommentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.recycler_view_material_comment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RecyclerViewMaterialCommentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addCommentButton;
      AppCompatButton addCommentButton = ViewBindings.findChildViewById(rootView, id);
      if (addCommentButton == null) {
        break missingId;
      }

      id = R.id.addCommentEditText;
      EditText addCommentEditText = ViewBindings.findChildViewById(rootView, id);
      if (addCommentEditText == null) {
        break missingId;
      }

      id = R.id.adminReply;
      TextView adminReply = ViewBindings.findChildViewById(rootView, id);
      if (adminReply == null) {
        break missingId;
      }

      id = R.id.commentTimestamp;
      TextView commentTimestamp = ViewBindings.findChildViewById(rootView, id);
      if (commentTimestamp == null) {
        break missingId;
      }

      id = R.id.noCommentsTextView;
      TextView noCommentsTextView = ViewBindings.findChildViewById(rootView, id);
      if (noCommentsTextView == null) {
        break missingId;
      }

      id = R.id.partnerImage;
      CircleImageView partnerImage = ViewBindings.findChildViewById(rootView, id);
      if (partnerImage == null) {
        break missingId;
      }

      id = R.id.replyTimestamp;
      TextView replyTimestamp = ViewBindings.findChildViewById(rootView, id);
      if (replyTimestamp == null) {
        break missingId;
      }

      id = R.id.userComment;
      TextView userComment = ViewBindings.findChildViewById(rootView, id);
      if (userComment == null) {
        break missingId;
      }

      id = R.id.userImage;
      CircleImageView userImage = ViewBindings.findChildViewById(rootView, id);
      if (userImage == null) {
        break missingId;
      }

      id = R.id.userName;
      TextView userName = ViewBindings.findChildViewById(rootView, id);
      if (userName == null) {
        break missingId;
      }

      return new RecyclerViewMaterialCommentBinding((ConstraintLayout) rootView, addCommentButton,
          addCommentEditText, adminReply, commentTimestamp, noCommentsTextView, partnerImage,
          replyTimestamp, userComment, userImage, userName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
