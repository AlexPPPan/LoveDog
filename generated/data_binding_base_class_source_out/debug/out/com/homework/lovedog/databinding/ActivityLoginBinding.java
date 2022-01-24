// Generated by view binder compiler. Do not edit!
package com.homework.lovedog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.homework.lovedog.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CheckBox auto;

  @NonNull
  public final EditText input1;

  @NonNull
  public final EditText input2;

  private ActivityLoginBinding(@NonNull LinearLayout rootView, @NonNull CheckBox auto,
      @NonNull EditText input1, @NonNull EditText input2) {
    this.rootView = rootView;
    this.auto = auto;
    this.input1 = input1;
    this.input2 = input2;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.auto;
      CheckBox auto = ViewBindings.findChildViewById(rootView, id);
      if (auto == null) {
        break missingId;
      }

      id = R.id.input_1;
      EditText input1 = ViewBindings.findChildViewById(rootView, id);
      if (input1 == null) {
        break missingId;
      }

      id = R.id.input_2;
      EditText input2 = ViewBindings.findChildViewById(rootView, id);
      if (input2 == null) {
        break missingId;
      }

      return new ActivityLoginBinding((LinearLayout) rootView, auto, input1, input2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}