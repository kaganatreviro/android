// Generated by view binder compiler. Do not edit!
package com.example.presentation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.presentation.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView rvRestList;

  @NonNull
  public final SwipeRefreshLayout swipeRef;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView tvSubsStatusTitle;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView, @NonNull RecyclerView rvRestList,
      @NonNull SwipeRefreshLayout swipeRef, @NonNull Toolbar toolbar,
      @NonNull TextView tvSubsStatusTitle) {
    this.rootView = rootView;
    this.rvRestList = rvRestList;
    this.swipeRef = swipeRef;
    this.toolbar = toolbar;
    this.tvSubsStatusTitle = tvSubsStatusTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rv_rest_list;
      RecyclerView rvRestList = ViewBindings.findChildViewById(rootView, id);
      if (rvRestList == null) {
        break missingId;
      }

      id = R.id.swipe_ref;
      SwipeRefreshLayout swipeRef = ViewBindings.findChildViewById(rootView, id);
      if (swipeRef == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tv_subs_status_title;
      TextView tvSubsStatusTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvSubsStatusTitle == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, rvRestList, swipeRef, toolbar,
          tvSubsStatusTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
