// Generated by data binding compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.fyps.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentAddUpdateQuestionBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout answerContainer;

  @NonNull
  public final ImageView arrowBack;

  @NonNull
  public final Button btnUploadQuestion;

  @NonNull
  public final EditText editTextText2;

  @NonNull
  public final EditText editTextText3;

  @NonNull
  public final EditText editTextText4;

  @NonNull
  public final EditText editTextText5;

  @NonNull
  public final EditText inputQuestion;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final RadioGroup optionContainer;

  @NonNull
  public final RadioButton radioButton;

  @NonNull
  public final RadioButton radioButton2;

  @NonNull
  public final RadioButton radioButton3;

  @NonNull
  public final RadioButton radioButton4;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final Toolbar toolbar;

  protected FragmentAddUpdateQuestionBinding(Object _bindingComponent, View _root,
      int _localFieldCount, LinearLayout answerContainer, ImageView arrowBack,
      Button btnUploadQuestion, EditText editTextText2, EditText editTextText3,
      EditText editTextText4, EditText editTextText5, EditText inputQuestion,
      LinearLayout linearLayout, RadioGroup optionContainer, RadioButton radioButton,
      RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4,
      TextView textView3, Toolbar toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.answerContainer = answerContainer;
    this.arrowBack = arrowBack;
    this.btnUploadQuestion = btnUploadQuestion;
    this.editTextText2 = editTextText2;
    this.editTextText3 = editTextText3;
    this.editTextText4 = editTextText4;
    this.editTextText5 = editTextText5;
    this.inputQuestion = inputQuestion;
    this.linearLayout = linearLayout;
    this.optionContainer = optionContainer;
    this.radioButton = radioButton;
    this.radioButton2 = radioButton2;
    this.radioButton3 = radioButton3;
    this.radioButton4 = radioButton4;
    this.textView3 = textView3;
    this.toolbar = toolbar;
  }

  @NonNull
  public static FragmentAddUpdateQuestionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_add_update_question, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentAddUpdateQuestionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentAddUpdateQuestionBinding>inflateInternal(inflater, R.layout.fragment_add_update_question, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentAddUpdateQuestionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_add_update_question, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentAddUpdateQuestionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentAddUpdateQuestionBinding>inflateInternal(inflater, R.layout.fragment_add_update_question, null, false, component);
  }

  public static FragmentAddUpdateQuestionBinding bind(@NonNull View view) {
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
  public static FragmentAddUpdateQuestionBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (FragmentAddUpdateQuestionBinding)bind(component, view, R.layout.fragment_add_update_question);
  }
}
