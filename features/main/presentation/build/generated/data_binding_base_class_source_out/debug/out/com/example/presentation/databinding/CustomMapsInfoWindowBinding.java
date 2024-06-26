// Generated by view binder compiler. Do not edit!
package com.example.presentation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.presentation.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CustomMapsInfoWindowBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageButton icon;

  @NonNull
  public final TextView snippet;

  @NonNull
  public final TextView title;

  private CustomMapsInfoWindowBinding(@NonNull LinearLayout rootView, @NonNull ImageButton icon,
      @NonNull TextView snippet, @NonNull TextView title) {
    this.rootView = rootView;
    this.icon = icon;
    this.snippet = snippet;
    this.title = title;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CustomMapsInfoWindowBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CustomMapsInfoWindowBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.custom_maps_info_window, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CustomMapsInfoWindowBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.icon;
      ImageButton icon = ViewBindings.findChildViewById(rootView, id);
      if (icon == null) {
        break missingId;
      }

      id = R.id.snippet;
      TextView snippet = ViewBindings.findChildViewById(rootView, id);
      if (snippet == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new CustomMapsInfoWindowBinding((LinearLayout) rootView, icon, snippet, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
