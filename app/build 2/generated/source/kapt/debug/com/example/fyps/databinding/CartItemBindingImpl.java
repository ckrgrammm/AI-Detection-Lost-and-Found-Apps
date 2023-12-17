package com.example.fyps.databinding;
import com.example.fyps.R;
import com.example.fyps.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CartItemBindingImpl extends CartItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.img_cart_product, 2);
        sViewsWithIds.put(R.id.tv_quantity, 3);
        sViewsWithIds.put(R.id.line, 4);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CartItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private CartItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[0]
            , (android.widget.ImageView) bindings[2]
            , (android.view.View) bindings[4]
            , (android.widget.TextView) bindings[1]
            , (android.widget.ImageView) bindings[3]
            );
        this.cardView.setTag(null);
        this.tvCartProductName.setTag(null);
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
        if (BR.courseDocument == variableId) {
            setCourseDocument((com.example.fyps.model.CourseDocument) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setCourseDocument(@Nullable com.example.fyps.model.CourseDocument CourseDocument) {
        this.mCourseDocument = CourseDocument;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.courseDocument);
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
        com.example.fyps.model.CourseDocument courseDocument = mCourseDocument;
        java.lang.String courseDocumentDocumentUrl = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (courseDocument != null) {
                    // read courseDocument.documentUrl
                    courseDocumentDocumentUrl = courseDocument.getDocumentUrl();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCartProductName, courseDocumentDocumentUrl);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): courseDocument
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}