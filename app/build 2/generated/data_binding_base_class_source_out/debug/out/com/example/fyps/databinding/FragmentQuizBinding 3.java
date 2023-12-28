// Generated by data binding compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.fyps.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentQuizBinding extends ViewDataBinding {
  @NonNull
  public final Button btnExit;

  @NonNull
  public final Button btnPlay;

  @NonNull
  public final TextView materialName;

  protected FragmentQuizBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnExit, Button btnPlay, TextView materialName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnExit = btnExit;
    this.btnPlay = btnPlay;
    this.materialName = materialName;
  }

  @NonNull
  public static FragmentQuizBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentQuizBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentQuizBinding>inflateInternal(inflater, R.layout.fragment_quiz, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentQuizBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentQuizBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentQuizBinding>inflateInternal(inflater, R.layout.fragment_quiz, null, false, component);
  }

  public static FragmentQuizBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentQuizBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentQuizBinding)bind(component, view, R.layout.fragment_quiz);
  }
}