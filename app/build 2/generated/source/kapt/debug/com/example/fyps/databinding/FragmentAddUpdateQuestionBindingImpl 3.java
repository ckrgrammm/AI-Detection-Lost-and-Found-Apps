package com.example.fyps.databinding;
import com.example.fyps.R;
import com.example.fyps.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentAddUpdateQuestionBindingImpl extends FragmentAddUpdateQuestionBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 1);
        sViewsWithIds.put(R.id.arrow_back, 2);
        sViewsWithIds.put(R.id.inputQuestion, 3);
        sViewsWithIds.put(R.id.textView3, 4);
        sViewsWithIds.put(R.id.linearLayout, 5);
        sViewsWithIds.put(R.id.optionContainer, 6);
        sViewsWithIds.put(R.id.radioButton, 7);
        sViewsWithIds.put(R.id.radioButton2, 8);
        sViewsWithIds.put(R.id.radioButton3, 9);
        sViewsWithIds.put(R.id.radioButton4, 10);
        sViewsWithIds.put(R.id.answerContainer, 11);
        sViewsWithIds.put(R.id.editTextText2, 12);
        sViewsWithIds.put(R.id.editTextText3, 13);
        sViewsWithIds.put(R.id.editTextText4, 14);
        sViewsWithIds.put(R.id.editTextText5, 15);
        sViewsWithIds.put(R.id.btnUploadQuestion, 16);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentAddUpdateQuestionBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }
    private FragmentAddUpdateQuestionBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.Button) bindings[16]
            , (android.widget.EditText) bindings[12]
            , (android.widget.EditText) bindings[13]
            , (android.widget.EditText) bindings[14]
            , (android.widget.EditText) bindings[15]
            , (android.widget.EditText) bindings[3]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.RadioGroup) bindings[6]
            , (android.widget.RadioButton) bindings[7]
            , (android.widget.RadioButton) bindings[8]
            , (android.widget.RadioButton) bindings[9]
            , (android.widget.RadioButton) bindings[10]
            , (android.widget.TextView) bindings[4]
            , (androidx.appcompat.widget.Toolbar) bindings[1]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}