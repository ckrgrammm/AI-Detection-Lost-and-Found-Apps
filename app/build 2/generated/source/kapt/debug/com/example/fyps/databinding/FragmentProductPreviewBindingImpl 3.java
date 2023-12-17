package com.example.fyps.databinding;
import com.example.fyps.R;
import com.example.fyps.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentProductPreviewBindingImpl extends FragmentProductPreviewBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.allMaterialComment, 2);
        sViewsWithIds.put(R.id.viewpager_card, 3);
        sViewsWithIds.put(R.id.materialImage, 4);
        sViewsWithIds.put(R.id.guideline1, 5);
        sViewsWithIds.put(R.id.guideline2, 6);
        sViewsWithIds.put(R.id.linear_product_name, 7);
        sViewsWithIds.put(R.id.tv_product_name, 8);
        sViewsWithIds.put(R.id.linear_product_description, 9);
        sViewsWithIds.put(R.id.tv_product_description, 10);
        sViewsWithIds.put(R.id.line, 11);
        sViewsWithIds.put(R.id.linear_status_category, 12);
        sViewsWithIds.put(R.id.tv_color, 13);
        sViewsWithIds.put(R.id.tv_size, 14);
        sViewsWithIds.put(R.id.tv_size_unit, 15);
        sViewsWithIds.put(R.id.linear_venue_datetime, 16);
        sViewsWithIds.put(R.id.tv_venue, 17);
        sViewsWithIds.put(R.id.tv_dateTime, 18);
        sViewsWithIds.put(R.id.locationLayout, 19);
        sViewsWithIds.put(R.id.progressbar, 20);
        sViewsWithIds.put(R.id.btnEnroll, 21);
    }
    // views
    @NonNull
    private final androidx.core.widget.NestedScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentProductPreviewBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }
    private FragmentProductPreviewBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (bindings[2] != null) ? com.example.fyps.databinding.MaterialCommentBinding.bind((android.view.View) bindings[2]) : null
            , (androidx.appcompat.widget.AppCompatButton) bindings[21]
            , (androidx.constraintlayout.widget.Guideline) bindings[5]
            , (androidx.constraintlayout.widget.Guideline) bindings[6]
            , (android.view.View) bindings[11]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.ImageView) bindings[4]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[1]
            , (android.widget.ProgressBar) bindings[20]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[17]
            , (androidx.cardview.widget.CardView) bindings[3]
            );
        this.mboundView0 = (androidx.core.widget.NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.productView.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.productModel == variableId) {
            setProductModel((com.example.fyps.model.Material) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setProductModel(@Nullable com.example.fyps.model.Material ProductModel) {
        this.mProductModel = ProductModel;
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
        flag 0 (0x1L): productModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}