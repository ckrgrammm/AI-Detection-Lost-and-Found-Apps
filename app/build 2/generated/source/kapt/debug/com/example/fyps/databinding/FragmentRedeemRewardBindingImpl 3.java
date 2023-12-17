package com.example.fyps.databinding;
import com.example.fyps.R;
import com.example.fyps.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentRedeemRewardBindingImpl extends FragmentRedeemRewardBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.addressBar, 1);
        sViewsWithIds.put(R.id.addressTextView, 2);
        sViewsWithIds.put(R.id.addressArrow, 3);
        sViewsWithIds.put(R.id.textViewTotalPoints, 4);
        sViewsWithIds.put(R.id.labelShippingVoucherTextView, 5);
        sViewsWithIds.put(R.id.voucherRecyclerView, 6);
        sViewsWithIds.put(R.id.textViewNoVoucherMsg, 7);
        sViewsWithIds.put(R.id.noInternetLayout, 8);
        sViewsWithIds.put(R.id.noInternetRelativeLayout, 9);
        sViewsWithIds.put(R.id.no_internet_image, 10);
        sViewsWithIds.put(R.id.no_internet_heading, 11);
        sViewsWithIds.put(R.id.no_internet_text, 12);
        sViewsWithIds.put(R.id.try_again_button, 13);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentRedeemRewardBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private FragmentRedeemRewardBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[3]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[11]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ScrollView) bindings[8]
            , (android.widget.RelativeLayout) bindings[9]
            , (android.widget.TextView) bindings[12]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[4]
            , (com.google.android.material.button.MaterialButton) bindings[13]
            , (androidx.recyclerview.widget.RecyclerView) bindings[6]
            );
        this.redeemRewardLayout.setTag(null);
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