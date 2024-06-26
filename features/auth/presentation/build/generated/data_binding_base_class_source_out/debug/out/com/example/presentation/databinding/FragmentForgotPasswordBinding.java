// Generated by view binder compiler. Do not edit!
package com.example.presentation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.presentation.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentForgotPasswordBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageButton btnBack;

  @NonNull
  public final TextView btnResentCode;

  @NonNull
  public final MaterialButton btnSend;

  @NonNull
  public final EditText etEnterPin;

  @NonNull
  public final TextInputEditText etInputEmail;

  @NonNull
  public final TextInputLayout etLayoutEmail;

  @NonNull
  public final LinearLayout flPinConfirm;

  @NonNull
  public final FrameLayout flProgressBar;

  @NonNull
  public final LinearLayout flResetPassword;

  @NonNull
  public final Toolbar toolBar;

  @NonNull
  public final TextView tvResentCode;

  @NonNull
  public final TextView tvTitle;

  @NonNull
  public final TextView tvTitleEmail;

  @NonNull
  public final TextView tvTitleFragment;

  @NonNull
  public final TextView tvTitlePin;

  private FragmentForgotPasswordBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatImageButton btnBack, @NonNull TextView btnResentCode,
      @NonNull MaterialButton btnSend, @NonNull EditText etEnterPin,
      @NonNull TextInputEditText etInputEmail, @NonNull TextInputLayout etLayoutEmail,
      @NonNull LinearLayout flPinConfirm, @NonNull FrameLayout flProgressBar,
      @NonNull LinearLayout flResetPassword, @NonNull Toolbar toolBar,
      @NonNull TextView tvResentCode, @NonNull TextView tvTitle, @NonNull TextView tvTitleEmail,
      @NonNull TextView tvTitleFragment, @NonNull TextView tvTitlePin) {
    this.rootView = rootView;
    this.btnBack = btnBack;
    this.btnResentCode = btnResentCode;
    this.btnSend = btnSend;
    this.etEnterPin = etEnterPin;
    this.etInputEmail = etInputEmail;
    this.etLayoutEmail = etLayoutEmail;
    this.flPinConfirm = flPinConfirm;
    this.flProgressBar = flProgressBar;
    this.flResetPassword = flResetPassword;
    this.toolBar = toolBar;
    this.tvResentCode = tvResentCode;
    this.tvTitle = tvTitle;
    this.tvTitleEmail = tvTitleEmail;
    this.tvTitleFragment = tvTitleFragment;
    this.tvTitlePin = tvTitlePin;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentForgotPasswordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentForgotPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_forgot_password, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentForgotPasswordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_back;
      AppCompatImageButton btnBack = ViewBindings.findChildViewById(rootView, id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.btn_resent_code;
      TextView btnResentCode = ViewBindings.findChildViewById(rootView, id);
      if (btnResentCode == null) {
        break missingId;
      }

      id = R.id.btn_send;
      MaterialButton btnSend = ViewBindings.findChildViewById(rootView, id);
      if (btnSend == null) {
        break missingId;
      }

      id = R.id.et_enter_pin;
      EditText etEnterPin = ViewBindings.findChildViewById(rootView, id);
      if (etEnterPin == null) {
        break missingId;
      }

      id = R.id.et_input_email;
      TextInputEditText etInputEmail = ViewBindings.findChildViewById(rootView, id);
      if (etInputEmail == null) {
        break missingId;
      }

      id = R.id.et_layout_email;
      TextInputLayout etLayoutEmail = ViewBindings.findChildViewById(rootView, id);
      if (etLayoutEmail == null) {
        break missingId;
      }

      id = R.id.fl_pin_confirm;
      LinearLayout flPinConfirm = ViewBindings.findChildViewById(rootView, id);
      if (flPinConfirm == null) {
        break missingId;
      }

      id = R.id.fl_progress_bar;
      FrameLayout flProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (flProgressBar == null) {
        break missingId;
      }

      id = R.id.fl_reset_password;
      LinearLayout flResetPassword = ViewBindings.findChildViewById(rootView, id);
      if (flResetPassword == null) {
        break missingId;
      }

      id = R.id.toolBar;
      Toolbar toolBar = ViewBindings.findChildViewById(rootView, id);
      if (toolBar == null) {
        break missingId;
      }

      id = R.id.tv_resent_code;
      TextView tvResentCode = ViewBindings.findChildViewById(rootView, id);
      if (tvResentCode == null) {
        break missingId;
      }

      id = R.id.tv_title;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      id = R.id.tv_title_email;
      TextView tvTitleEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvTitleEmail == null) {
        break missingId;
      }

      id = R.id.tv_title_fragment;
      TextView tvTitleFragment = ViewBindings.findChildViewById(rootView, id);
      if (tvTitleFragment == null) {
        break missingId;
      }

      id = R.id.tv_title_pin;
      TextView tvTitlePin = ViewBindings.findChildViewById(rootView, id);
      if (tvTitlePin == null) {
        break missingId;
      }

      return new FragmentForgotPasswordBinding((ConstraintLayout) rootView, btnBack, btnResentCode,
          btnSend, etEnterPin, etInputEmail, etLayoutEmail, flPinConfirm, flProgressBar,
          flResetPassword, toolBar, tvResentCode, tvTitle, tvTitleEmail, tvTitleFragment,
          tvTitlePin);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
