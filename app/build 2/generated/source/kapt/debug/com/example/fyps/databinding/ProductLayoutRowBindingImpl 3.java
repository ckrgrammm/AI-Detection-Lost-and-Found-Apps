package com.example.fyps.databinding;
import com.example.fyps.R;
import com.example.fyps.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ProductLayoutRowBindingImpl extends ProductLayoutRowBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageView, 5);
        sViewsWithIds.put(R.id.buttonHeart, 6);
        sViewsWithIds.put(R.id.cardView2, 7);
        sViewsWithIds.put(R.id.linearLayoutCompat3, 8);
        sViewsWithIds.put(R.id.imageView2, 9);
        sViewsWithIds.put(R.id.textView8, 10);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ProductLayoutRowBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private ProductLayoutRowBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageButton) bindings[6]
            , (androidx.cardview.widget.CardView) bindings[7]
            , (android.widget.TextView) bindings[3]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[9]
            , (androidx.appcompat.widget.LinearLayoutCompat) bindings[8]
            , (android.widget.TextView) bindings[4]
            , (androidx.cardview.widget.CardView) bindings[0]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[10]
            );
        this.expTv.setTag(null);
        this.product.setTag(null);
        this.productCard.setTag(null);
        this.productCategory.setTag(null);
        this.productTitle.setTag(null);
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
        java.lang.String productModelStatus = null;
        java.lang.String stringValueOfProductModelRating = null;
        java.lang.String productModelName = null;
        com.example.fyps.model.Material productModel = mProductModel;
        java.lang.String productModelDesc = null;
        double productModelRating = 0.0;

        if ((dirtyFlags & 0x3L) != 0) {



                if (productModel != null) {
                    // read productModel.status
                    productModelStatus = productModel.getStatus();
                    // read productModel.name
                    productModelName = productModel.getName();
                    // read productModel.desc
                    productModelDesc = productModel.getDesc();
                    // read productModel.rating
                    productModelRating = productModel.getRating();
                }


                // read String.valueOf(productModel.rating)
                stringValueOfProductModelRating = java.lang.String.valueOf(productModelRating);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.expTv, stringValueOfProductModelRating);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.product, productModelName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.productCategory, productModelStatus);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.productTitle, productModelDesc);
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