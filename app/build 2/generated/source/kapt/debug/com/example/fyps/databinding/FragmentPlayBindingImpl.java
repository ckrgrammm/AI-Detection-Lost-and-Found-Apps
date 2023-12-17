package com.example.fyps.databinding;
import com.example.fyps.R;
import com.example.fyps.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentPlayBindingImpl extends FragmentPlayBinding implements com.example.fyps.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.constraintLayout, 9);
        sViewsWithIds.put(R.id.imageView, 10);
        sViewsWithIds.put(R.id.image_back, 11);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback4;
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    @Nullable
    private final android.view.View.OnClickListener mCallback5;
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentPlayBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private FragmentPlayBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7
            , (android.widget.Button) bindings[5]
            , (android.widget.Button) bindings[6]
            , (android.widget.Button) bindings[7]
            , (android.widget.Button) bindings[8]
            , (android.widget.Button) bindings[4]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[9]
            , (android.widget.TextView) bindings[1]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            );
        this.btnChoose1.setTag(null);
        this.btnChoose2.setTag(null);
        this.btnChoose3.setTag(null);
        this.btnChoose4.setTag(null);
        this.btnNext.setTag(null);
        this.cptQuestion.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.textQuestion.setTag(null);
        this.timerText.setTag(null);
        setRootTag(root);
        // listeners
        mCallback4 = new com.example.fyps.generated.callback.OnClickListener(this, 4);
        mCallback2 = new com.example.fyps.generated.callback.OnClickListener(this, 2);
        mCallback5 = new com.example.fyps.generated.callback.OnClickListener(this, 5);
        mCallback3 = new com.example.fyps.generated.callback.OnClickListener(this, 3);
        mCallback1 = new com.example.fyps.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x100L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.playViewModel == variableId) {
            setPlayViewModel((com.example.fyps.viewmodel.quiz.PlayViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPlayViewModel(@Nullable com.example.fyps.viewmodel.quiz.PlayViewModel PlayViewModel) {
        this.mPlayViewModel = PlayViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x80L;
        }
        notifyPropertyChanged(BR.playViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangePlayViewModelBtnChoice3Text((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangePlayViewModelBtnChoice1Text((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangePlayViewModelRemainingTime((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangePlayViewModelQuestionText((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangePlayViewModelBtnChoice2Text((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 5 :
                return onChangePlayViewModelBtnChoice4Text((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangePlayViewModelCptQuestionText((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangePlayViewModelBtnChoice3Text(androidx.lifecycle.LiveData<java.lang.String> PlayViewModelBtnChoice3Text, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangePlayViewModelBtnChoice1Text(androidx.lifecycle.LiveData<java.lang.String> PlayViewModelBtnChoice1Text, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangePlayViewModelRemainingTime(androidx.lifecycle.LiveData<java.lang.String> PlayViewModelRemainingTime, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangePlayViewModelQuestionText(androidx.lifecycle.LiveData<java.lang.String> PlayViewModelQuestionText, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangePlayViewModelBtnChoice2Text(androidx.lifecycle.LiveData<java.lang.String> PlayViewModelBtnChoice2Text, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangePlayViewModelBtnChoice4Text(androidx.lifecycle.LiveData<java.lang.String> PlayViewModelBtnChoice4Text, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangePlayViewModelCptQuestionText(androidx.lifecycle.LiveData<java.lang.String> PlayViewModelCptQuestionText, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String playViewModelBtnChoice4TextGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> playViewModelBtnChoice3Text = null;
        androidx.lifecycle.LiveData<java.lang.String> playViewModelBtnChoice1Text = null;
        androidx.lifecycle.LiveData<java.lang.String> playViewModelRemainingTime = null;
        androidx.lifecycle.LiveData<java.lang.String> playViewModelQuestionText = null;
        java.lang.String playViewModelCptQuestionTextGetValue = null;
        java.lang.String playViewModelBtnChoice3TextGetValue = null;
        java.lang.String playViewModelBtnChoice1TextGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> playViewModelBtnChoice2Text = null;
        com.example.fyps.viewmodel.quiz.PlayViewModel playViewModel = mPlayViewModel;
        java.lang.String playViewModelRemainingTimeGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> playViewModelBtnChoice4Text = null;
        androidx.lifecycle.LiveData<java.lang.String> playViewModelCptQuestionText = null;
        java.lang.String playViewModelQuestionTextGetValue = null;
        java.lang.String playViewModelBtnChoice2TextGetValue = null;

        if ((dirtyFlags & 0x1ffL) != 0) {


            if ((dirtyFlags & 0x181L) != 0) {

                    if (playViewModel != null) {
                        // read playViewModel.btnChoice3Text
                        playViewModelBtnChoice3Text = playViewModel.getBtnChoice3Text();
                    }
                    updateLiveDataRegistration(0, playViewModelBtnChoice3Text);


                    if (playViewModelBtnChoice3Text != null) {
                        // read playViewModel.btnChoice3Text.getValue()
                        playViewModelBtnChoice3TextGetValue = playViewModelBtnChoice3Text.getValue();
                    }
            }
            if ((dirtyFlags & 0x182L) != 0) {

                    if (playViewModel != null) {
                        // read playViewModel.btnChoice1Text
                        playViewModelBtnChoice1Text = playViewModel.getBtnChoice1Text();
                    }
                    updateLiveDataRegistration(1, playViewModelBtnChoice1Text);


                    if (playViewModelBtnChoice1Text != null) {
                        // read playViewModel.btnChoice1Text.getValue()
                        playViewModelBtnChoice1TextGetValue = playViewModelBtnChoice1Text.getValue();
                    }
            }
            if ((dirtyFlags & 0x184L) != 0) {

                    if (playViewModel != null) {
                        // read playViewModel.remainingTime
                        playViewModelRemainingTime = playViewModel.getRemainingTime();
                    }
                    updateLiveDataRegistration(2, playViewModelRemainingTime);


                    if (playViewModelRemainingTime != null) {
                        // read playViewModel.remainingTime.getValue()
                        playViewModelRemainingTimeGetValue = playViewModelRemainingTime.getValue();
                    }
            }
            if ((dirtyFlags & 0x188L) != 0) {

                    if (playViewModel != null) {
                        // read playViewModel.questionText
                        playViewModelQuestionText = playViewModel.getQuestionText();
                    }
                    updateLiveDataRegistration(3, playViewModelQuestionText);


                    if (playViewModelQuestionText != null) {
                        // read playViewModel.questionText.getValue()
                        playViewModelQuestionTextGetValue = playViewModelQuestionText.getValue();
                    }
            }
            if ((dirtyFlags & 0x190L) != 0) {

                    if (playViewModel != null) {
                        // read playViewModel.btnChoice2Text
                        playViewModelBtnChoice2Text = playViewModel.getBtnChoice2Text();
                    }
                    updateLiveDataRegistration(4, playViewModelBtnChoice2Text);


                    if (playViewModelBtnChoice2Text != null) {
                        // read playViewModel.btnChoice2Text.getValue()
                        playViewModelBtnChoice2TextGetValue = playViewModelBtnChoice2Text.getValue();
                    }
            }
            if ((dirtyFlags & 0x1a0L) != 0) {

                    if (playViewModel != null) {
                        // read playViewModel.btnChoice4Text
                        playViewModelBtnChoice4Text = playViewModel.getBtnChoice4Text();
                    }
                    updateLiveDataRegistration(5, playViewModelBtnChoice4Text);


                    if (playViewModelBtnChoice4Text != null) {
                        // read playViewModel.btnChoice4Text.getValue()
                        playViewModelBtnChoice4TextGetValue = playViewModelBtnChoice4Text.getValue();
                    }
            }
            if ((dirtyFlags & 0x1c0L) != 0) {

                    if (playViewModel != null) {
                        // read playViewModel.cptQuestionText
                        playViewModelCptQuestionText = playViewModel.getCptQuestionText();
                    }
                    updateLiveDataRegistration(6, playViewModelCptQuestionText);


                    if (playViewModelCptQuestionText != null) {
                        // read playViewModel.cptQuestionText.getValue()
                        playViewModelCptQuestionTextGetValue = playViewModelCptQuestionText.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x100L) != 0) {
            // api target 1

            this.btnChoose1.setOnClickListener(mCallback2);
            this.btnChoose2.setOnClickListener(mCallback3);
            this.btnChoose3.setOnClickListener(mCallback4);
            this.btnChoose4.setOnClickListener(mCallback5);
            this.btnNext.setOnClickListener(mCallback1);
        }
        if ((dirtyFlags & 0x182L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnChoose1, playViewModelBtnChoice1TextGetValue);
        }
        if ((dirtyFlags & 0x190L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnChoose2, playViewModelBtnChoice2TextGetValue);
        }
        if ((dirtyFlags & 0x181L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnChoose3, playViewModelBtnChoice3TextGetValue);
        }
        if ((dirtyFlags & 0x1a0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnChoose4, playViewModelBtnChoice4TextGetValue);
        }
        if ((dirtyFlags & 0x1c0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.cptQuestion, playViewModelCptQuestionTextGetValue);
        }
        if ((dirtyFlags & 0x188L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textQuestion, playViewModelQuestionTextGetValue);
        }
        if ((dirtyFlags & 0x184L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.timerText, playViewModelRemainingTimeGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 4: {
                // localize variables for thread safety
                // playViewModel
                com.example.fyps.viewmodel.quiz.PlayViewModel playViewModel = mPlayViewModel;
                // playViewModel != null
                boolean playViewModelJavaLangObjectNull = false;



                playViewModelJavaLangObjectNull = (playViewModel) != (null);
                if (playViewModelJavaLangObjectNull) {



                    playViewModel.clickChoose(callbackArg_0);
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // playViewModel
                com.example.fyps.viewmodel.quiz.PlayViewModel playViewModel = mPlayViewModel;
                // playViewModel != null
                boolean playViewModelJavaLangObjectNull = false;



                playViewModelJavaLangObjectNull = (playViewModel) != (null);
                if (playViewModelJavaLangObjectNull) {



                    playViewModel.clickChoose(callbackArg_0);
                }
                break;
            }
            case 5: {
                // localize variables for thread safety
                // playViewModel
                com.example.fyps.viewmodel.quiz.PlayViewModel playViewModel = mPlayViewModel;
                // playViewModel != null
                boolean playViewModelJavaLangObjectNull = false;



                playViewModelJavaLangObjectNull = (playViewModel) != (null);
                if (playViewModelJavaLangObjectNull) {



                    playViewModel.clickChoose(callbackArg_0);
                }
                break;
            }
            case 3: {
                // localize variables for thread safety
                // playViewModel
                com.example.fyps.viewmodel.quiz.PlayViewModel playViewModel = mPlayViewModel;
                // playViewModel != null
                boolean playViewModelJavaLangObjectNull = false;



                playViewModelJavaLangObjectNull = (playViewModel) != (null);
                if (playViewModelJavaLangObjectNull) {



                    playViewModel.clickChoose(callbackArg_0);
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // playViewModel
                com.example.fyps.viewmodel.quiz.PlayViewModel playViewModel = mPlayViewModel;
                // playViewModel != null
                boolean playViewModelJavaLangObjectNull = false;



                playViewModelJavaLangObjectNull = (playViewModel) != (null);
                if (playViewModelJavaLangObjectNull) {


                    playViewModel.nextQuestion();
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): playViewModel.btnChoice3Text
        flag 1 (0x2L): playViewModel.btnChoice1Text
        flag 2 (0x3L): playViewModel.remainingTime
        flag 3 (0x4L): playViewModel.questionText
        flag 4 (0x5L): playViewModel.btnChoice2Text
        flag 5 (0x6L): playViewModel.btnChoice4Text
        flag 6 (0x7L): playViewModel.cptQuestionText
        flag 7 (0x8L): playViewModel
        flag 8 (0x9L): null
    flag mapping end*/
    //end
}