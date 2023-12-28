// Generated by data binding compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.fyps.R;
import com.example.fyps.viewmodel.quiz.PlayViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentPlayBinding extends ViewDataBinding {
  @NonNull
  public final Button btnChoose1;

  @NonNull
  public final Button btnChoose2;

  @NonNull
  public final Button btnChoose3;

  @NonNull
  public final Button btnChoose4;

  @NonNull
  public final Button btnNext;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final TextView cptQuestion;

  @NonNull
  public final ImageView imageBack;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView textQuestion;

  @NonNull
  public final TextView timerText;

  @Bindable
  protected PlayViewModel mPlayViewModel;

  protected FragmentPlayBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnChoose1, Button btnChoose2, Button btnChoose3, Button btnChoose4, Button btnNext,
      ConstraintLayout constraintLayout, TextView cptQuestion, ImageView imageBack,
      ImageView imageView, TextView textQuestion, TextView timerText) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnChoose1 = btnChoose1;
    this.btnChoose2 = btnChoose2;
    this.btnChoose3 = btnChoose3;
    this.btnChoose4 = btnChoose4;
    this.btnNext = btnNext;
    this.constraintLayout = constraintLayout;
    this.cptQuestion = cptQuestion;
    this.imageBack = imageBack;
    this.imageView = imageView;
    this.textQuestion = textQuestion;
    this.timerText = timerText;
  }

  public abstract void setPlayViewModel(@Nullable PlayViewModel playViewModel);

  @Nullable
  public PlayViewModel getPlayViewModel() {
    return mPlayViewModel;
  }

  @NonNull
  public static FragmentPlayBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_play, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentPlayBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentPlayBinding>inflateInternal(inflater, R.layout.fragment_play, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentPlayBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_play, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentPlayBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentPlayBinding>inflateInternal(inflater, R.layout.fragment_play, null, false, component);
  }

  public static FragmentPlayBinding bind(@NonNull View view) {
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
  public static FragmentPlayBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentPlayBinding)bind(component, view, R.layout.fragment_play);
  }
}