package com.example.fyps.databinding;
import com.example.fyps.R;
import com.example.fyps.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class GridLayoutBindingImpl extends GridLayoutBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.icon_item, 4);
        sViewsWithIds.put(R.id.cardViewStatus, 5);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public GridLayoutBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private GridLayoutBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[5]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.LinearLayout) bindings[0]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[2]
            );
        this.itemContainer.setTag(null);
        this.itemName.setTag(null);
        this.productStatus.setTag(null);
        this.reporterName.setTag(null);
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
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.productModel);
        super.requestRebind();
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
        com.example.fyps.model.Material productModel = mProductModel;
        java.lang.String productModelStatus = null;
        java.lang.String productModelName = null;
        java.lang.String ProductModelName1 = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (productModel != null) {
                    // read productModel.status
                    productModelStatus = productModel.getStatus();
                    // read productModel.Name
                    productModelName = productModel.getName();
                    // read productModel.name
                    ProductModelName1 = productModel.getName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.itemName, ProductModelName1);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.productStatus, productModelStatus);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.reporterName, productModelName);
        }
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