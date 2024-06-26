// Generated by view binder compiler. Do not edit!
package com.example.presentation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.presentation.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemSubscriptionPlanBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final ImageView ivYourPlan;

  @NonNull
  public final View line1;

  @NonNull
  public final RadioButton rbChecked;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvPlanDesc;

  @NonNull
  public final TextView tvPriceAndDuration;

  @NonNull
  public final TextView tvYourPlan;

  private ItemSubscriptionPlanBinding(@NonNull FrameLayout rootView, @NonNull ImageView ivYourPlan,
      @NonNull View line1, @NonNull RadioButton rbChecked, @NonNull TextView tvName,
      @NonNull TextView tvPlanDesc, @NonNull TextView tvPriceAndDuration,
      @NonNull TextView tvYourPlan) {
    this.rootView = rootView;
    this.ivYourPlan = ivYourPlan;
    this.line1 = line1;
    this.rbChecked = rbChecked;
    this.tvName = tvName;
    this.tvPlanDesc = tvPlanDesc;
    this.tvPriceAndDuration = tvPriceAndDuration;
    this.tvYourPlan = tvYourPlan;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemSubscriptionPlanBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemSubscriptionPlanBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_subscription_plan, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemSubscriptionPlanBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.iv_your_plan;
      ImageView ivYourPlan = ViewBindings.findChildViewById(rootView, id);
      if (ivYourPlan == null) {
        break missingId;
      }

      id = R.id.line1;
      View line1 = ViewBindings.findChildViewById(rootView, id);
      if (line1 == null) {
        break missingId;
      }

      id = R.id.rb_checked;
      RadioButton rbChecked = ViewBindings.findChildViewById(rootView, id);
      if (rbChecked == null) {
        break missingId;
      }

      id = R.id.tv_name;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.tv_plan_desc;
      TextView tvPlanDesc = ViewBindings.findChildViewById(rootView, id);
      if (tvPlanDesc == null) {
        break missingId;
      }

      id = R.id.tv_price_and_duration;
      TextView tvPriceAndDuration = ViewBindings.findChildViewById(rootView, id);
      if (tvPriceAndDuration == null) {
        break missingId;
      }

      id = R.id.tv_your_plan;
      TextView tvYourPlan = ViewBindings.findChildViewById(rootView, id);
      if (tvYourPlan == null) {
        break missingId;
      }

      return new ItemSubscriptionPlanBinding((FrameLayout) rootView, ivYourPlan, line1, rbChecked,
          tvName, tvPlanDesc, tvPriceAndDuration, tvYourPlan);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
