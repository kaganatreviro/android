// Generated by view binder compiler. Do not edit!
package com.example.presentation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.presentation.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ItemBeverageCategoryTitleBinding implements ViewBinding {
  @NonNull
  private final TextView rootView;

  @NonNull
  public final TextView tvCategoryTitle;

  private ItemBeverageCategoryTitleBinding(@NonNull TextView rootView,
      @NonNull TextView tvCategoryTitle) {
    this.rootView = rootView;
    this.tvCategoryTitle = tvCategoryTitle;
  }

  @Override
  @NonNull
  public TextView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemBeverageCategoryTitleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemBeverageCategoryTitleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_beverage_category_title, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemBeverageCategoryTitleBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    TextView tvCategoryTitle = (TextView) rootView;

    return new ItemBeverageCategoryTitleBinding((TextView) rootView, tvCategoryTitle);
  }
}
