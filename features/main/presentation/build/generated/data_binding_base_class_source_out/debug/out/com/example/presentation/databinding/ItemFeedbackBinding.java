// Generated by view binder compiler. Do not edit!
package com.example.presentation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.core_ui.extensions.ExpandableTextView;
import com.example.presentation.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemFeedbackBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final View line1;

  @NonNull
  public final ExpandableTextView tvFeedback;

  @NonNull
  public final TextView tvPostTime;

  @NonNull
  public final TextView tvReplied;

  @NonNull
  public final TextView tvUserName;

  private ItemFeedbackBinding(@NonNull ConstraintLayout rootView, @NonNull View line1,
      @NonNull ExpandableTextView tvFeedback, @NonNull TextView tvPostTime,
      @NonNull TextView tvReplied, @NonNull TextView tvUserName) {
    this.rootView = rootView;
    this.line1 = line1;
    this.tvFeedback = tvFeedback;
    this.tvPostTime = tvPostTime;
    this.tvReplied = tvReplied;
    this.tvUserName = tvUserName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemFeedbackBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemFeedbackBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_feedback, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemFeedbackBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.line1;
      View line1 = ViewBindings.findChildViewById(rootView, id);
      if (line1 == null) {
        break missingId;
      }

      id = R.id.tv_feedback;
      ExpandableTextView tvFeedback = ViewBindings.findChildViewById(rootView, id);
      if (tvFeedback == null) {
        break missingId;
      }

      id = R.id.tv_post_time;
      TextView tvPostTime = ViewBindings.findChildViewById(rootView, id);
      if (tvPostTime == null) {
        break missingId;
      }

      id = R.id.tv_replied;
      TextView tvReplied = ViewBindings.findChildViewById(rootView, id);
      if (tvReplied == null) {
        break missingId;
      }

      id = R.id.tv_user_name;
      TextView tvUserName = ViewBindings.findChildViewById(rootView, id);
      if (tvUserName == null) {
        break missingId;
      }

      return new ItemFeedbackBinding((ConstraintLayout) rootView, line1, tvFeedback, tvPostTime,
          tvReplied, tvUserName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}