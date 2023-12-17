package com.example.fyps.databinding;
import com.example.fyps.R;
import com.example.fyps.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentAdminUpdateRewardBindingImpl extends FragmentAdminUpdateRewardBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imgRewardPreview, 5);
        sViewsWithIds.put(R.id.btnUpdate, 6);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener txtRedeemLimitandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of adminUpdateRewardViewModel.redeemLimit.getValue()
            //         is adminUpdateRewardViewModel.redeemLimit.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(txtRedeemLimit);
            // localize variables for thread safety
            // adminUpdateRewardViewModel.redeemLimit.getValue()
            java.lang.String adminUpdateRewardViewModelRedeemLimitGetValue = null;
            // adminUpdateRewardViewModel.redeemLimit
            androidx.lifecycle.MutableLiveData<java.lang.String> adminUpdateRewardViewModelRedeemLimit = null;
            // adminUpdateRewardViewModel
            com.example.fyps.viewmodel.admin.AdminUpdateRewardViewModel adminUpdateRewardViewModel = mAdminUpdateRewardViewModel;
            // adminUpdateRewardViewModel != null
            boolean adminUpdateRewardViewModelJavaLangObjectNull = false;
            // adminUpdateRewardViewModel.redeemLimit != null
            boolean adminUpdateRewardViewModelRedeemLimitJavaLangObjectNull = false;



            adminUpdateRewardViewModelJavaLangObjectNull = (adminUpdateRewardViewModel) != (null);
            if (adminUpdateRewardViewModelJavaLangObjectNull) {


                adminUpdateRewardViewModelRedeemLimit = adminUpdateRewardViewModel.getRedeemLimit();

                adminUpdateRewardViewModelRedeemLimitJavaLangObjectNull = (adminUpdateRewardViewModelRedeemLimit) != (null);
                if (adminUpdateRewardViewModelRedeemLimitJavaLangObjectNull) {




                    adminUpdateRewardViewModelRedeemLimit.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener txtRewardDescriptionandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of adminUpdateRewardViewModel.rewardDescription.getValue()
            //         is adminUpdateRewardViewModel.rewardDescription.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(txtRewardDescription);
            // localize variables for thread safety
            // adminUpdateRewardViewModel.rewardDescription
            androidx.lifecycle.MutableLiveData<java.lang.String> adminUpdateRewardViewModelRewardDescription = null;
            // adminUpdateRewardViewModel.rewardDescription != null
            boolean adminUpdateRewardViewModelRewardDescriptionJavaLangObjectNull = false;
            // adminUpdateRewardViewModel.rewardDescription.getValue()
            java.lang.String adminUpdateRewardViewModelRewardDescriptionGetValue = null;
            // adminUpdateRewardViewModel
            com.example.fyps.viewmodel.admin.AdminUpdateRewardViewModel adminUpdateRewardViewModel = mAdminUpdateRewardViewModel;
            // adminUpdateRewardViewModel != null
            boolean adminUpdateRewardViewModelJavaLangObjectNull = false;



            adminUpdateRewardViewModelJavaLangObjectNull = (adminUpdateRewardViewModel) != (null);
            if (adminUpdateRewardViewModelJavaLangObjectNull) {


                adminUpdateRewardViewModelRewardDescription = adminUpdateRewardViewModel.getRewardDescription();

                adminUpdateRewardViewModelRewardDescriptionJavaLangObjectNull = (adminUpdateRewardViewModelRewardDescription) != (null);
                if (adminUpdateRewardViewModelRewardDescriptionJavaLangObjectNull) {




                    adminUpdateRewardViewModelRewardDescription.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener txtRewardNameandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of adminUpdateRewardViewModel.rewardName.getValue()
            //         is adminUpdateRewardViewModel.rewardName.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(txtRewardName);
            // localize variables for thread safety
            // adminUpdateRewardViewModel.rewardName
            androidx.lifecycle.MutableLiveData<java.lang.String> adminUpdateRewardViewModelRewardName = null;
            // adminUpdateRewardViewModel.rewardName != null
            boolean adminUpdateRewardViewModelRewardNameJavaLangObjectNull = false;
            // adminUpdateRewardViewModel
            com.example.fyps.viewmodel.admin.AdminUpdateRewardViewModel adminUpdateRewardViewModel = mAdminUpdateRewardViewModel;
            // adminUpdateRewardViewModel != null
            boolean adminUpdateRewardViewModelJavaLangObjectNull = false;
            // adminUpdateRewardViewModel.rewardName.getValue()
            java.lang.String adminUpdateRewardViewModelRewardNameGetValue = null;



            adminUpdateRewardViewModelJavaLangObjectNull = (adminUpdateRewardViewModel) != (null);
            if (adminUpdateRewardViewModelJavaLangObjectNull) {


                adminUpdateRewardViewModelRewardName = adminUpdateRewardViewModel.getRewardName();

                adminUpdateRewardViewModelRewardNameJavaLangObjectNull = (adminUpdateRewardViewModelRewardName) != (null);
                if (adminUpdateRewardViewModelRewardNameJavaLangObjectNull) {




                    adminUpdateRewardViewModelRewardName.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener txtRewardPointsandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of adminUpdateRewardViewModel.rewardPoints.getValue()
            //         is adminUpdateRewardViewModel.rewardPoints.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(txtRewardPoints);
            // localize variables for thread safety
            // adminUpdateRewardViewModel.rewardPoints != null
            boolean adminUpdateRewardViewModelRewardPointsJavaLangObjectNull = false;
            // adminUpdateRewardViewModel.rewardPoints
            androidx.lifecycle.MutableLiveData<java.lang.String> adminUpdateRewardViewModelRewardPoints = null;
            // adminUpdateRewardViewModel.rewardPoints.getValue()
            java.lang.String adminUpdateRewardViewModelRewardPointsGetValue = null;
            // adminUpdateRewardViewModel
            com.example.fyps.viewmodel.admin.AdminUpdateRewardViewModel adminUpdateRewardViewModel = mAdminUpdateRewardViewModel;
            // adminUpdateRewardViewModel != null
            boolean adminUpdateRewardViewModelJavaLangObjectNull = false;



            adminUpdateRewardViewModelJavaLangObjectNull = (adminUpdateRewardViewModel) != (null);
            if (adminUpdateRewardViewModelJavaLangObjectNull) {


                adminUpdateRewardViewModelRewardPoints = adminUpdateRewardViewModel.getRewardPoints();

                adminUpdateRewardViewModelRewardPointsJavaLangObjectNull = (adminUpdateRewardViewModelRewardPoints) != (null);
                if (adminUpdateRewardViewModelRewardPointsJavaLangObjectNull) {




                    adminUpdateRewardViewModelRewardPoints.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentAdminUpdateRewardBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private FragmentAdminUpdateRewardBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (android.widget.Button) bindings[6]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[1]
            , (android.widget.EditText) bindings[3]
            );
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.txtRedeemLimit.setTag(null);
        this.txtRewardDescription.setTag(null);
        this.txtRewardName.setTag(null);
        this.txtRewardPoints.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20L;
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
        if (BR.adminUpdateRewardViewModel == variableId) {
            setAdminUpdateRewardViewModel((com.example.fyps.viewmodel.admin.AdminUpdateRewardViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setAdminUpdateRewardViewModel(@Nullable com.example.fyps.viewmodel.admin.AdminUpdateRewardViewModel AdminUpdateRewardViewModel) {
        this.mAdminUpdateRewardViewModel = AdminUpdateRewardViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.adminUpdateRewardViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeAdminUpdateRewardViewModelRewardName((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeAdminUpdateRewardViewModelRewardPoints((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeAdminUpdateRewardViewModelRedeemLimit((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeAdminUpdateRewardViewModelRewardDescription((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeAdminUpdateRewardViewModelRewardName(androidx.lifecycle.MutableLiveData<java.lang.String> AdminUpdateRewardViewModelRewardName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeAdminUpdateRewardViewModelRewardPoints(androidx.lifecycle.MutableLiveData<java.lang.String> AdminUpdateRewardViewModelRewardPoints, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeAdminUpdateRewardViewModelRedeemLimit(androidx.lifecycle.MutableLiveData<java.lang.String> AdminUpdateRewardViewModelRedeemLimit, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeAdminUpdateRewardViewModelRewardDescription(androidx.lifecycle.MutableLiveData<java.lang.String> AdminUpdateRewardViewModelRewardDescription, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
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
        java.lang.String adminUpdateRewardViewModelRedeemLimitGetValue = null;
        java.lang.String adminUpdateRewardViewModelRewardPointsGetValue = null;
        java.lang.String adminUpdateRewardViewModelRewardDescriptionGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> adminUpdateRewardViewModelRewardName = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> adminUpdateRewardViewModelRewardPoints = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> adminUpdateRewardViewModelRedeemLimit = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> adminUpdateRewardViewModelRewardDescription = null;
        com.example.fyps.viewmodel.admin.AdminUpdateRewardViewModel adminUpdateRewardViewModel = mAdminUpdateRewardViewModel;
        java.lang.String adminUpdateRewardViewModelRewardNameGetValue = null;

        if ((dirtyFlags & 0x3fL) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (adminUpdateRewardViewModel != null) {
                        // read adminUpdateRewardViewModel.rewardName
                        adminUpdateRewardViewModelRewardName = adminUpdateRewardViewModel.getRewardName();
                    }
                    updateLiveDataRegistration(0, adminUpdateRewardViewModelRewardName);


                    if (adminUpdateRewardViewModelRewardName != null) {
                        // read adminUpdateRewardViewModel.rewardName.getValue()
                        adminUpdateRewardViewModelRewardNameGetValue = adminUpdateRewardViewModelRewardName.getValue();
                    }
            }
            if ((dirtyFlags & 0x32L) != 0) {

                    if (adminUpdateRewardViewModel != null) {
                        // read adminUpdateRewardViewModel.rewardPoints
                        adminUpdateRewardViewModelRewardPoints = adminUpdateRewardViewModel.getRewardPoints();
                    }
                    updateLiveDataRegistration(1, adminUpdateRewardViewModelRewardPoints);


                    if (adminUpdateRewardViewModelRewardPoints != null) {
                        // read adminUpdateRewardViewModel.rewardPoints.getValue()
                        adminUpdateRewardViewModelRewardPointsGetValue = adminUpdateRewardViewModelRewardPoints.getValue();
                    }
            }
            if ((dirtyFlags & 0x34L) != 0) {

                    if (adminUpdateRewardViewModel != null) {
                        // read adminUpdateRewardViewModel.redeemLimit
                        adminUpdateRewardViewModelRedeemLimit = adminUpdateRewardViewModel.getRedeemLimit();
                    }
                    updateLiveDataRegistration(2, adminUpdateRewardViewModelRedeemLimit);


                    if (adminUpdateRewardViewModelRedeemLimit != null) {
                        // read adminUpdateRewardViewModel.redeemLimit.getValue()
                        adminUpdateRewardViewModelRedeemLimitGetValue = adminUpdateRewardViewModelRedeemLimit.getValue();
                    }
            }
            if ((dirtyFlags & 0x38L) != 0) {

                    if (adminUpdateRewardViewModel != null) {
                        // read adminUpdateRewardViewModel.rewardDescription
                        adminUpdateRewardViewModelRewardDescription = adminUpdateRewardViewModel.getRewardDescription();
                    }
                    updateLiveDataRegistration(3, adminUpdateRewardViewModelRewardDescription);


                    if (adminUpdateRewardViewModelRewardDescription != null) {
                        // read adminUpdateRewardViewModel.rewardDescription.getValue()
                        adminUpdateRewardViewModelRewardDescriptionGetValue = adminUpdateRewardViewModelRewardDescription.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x34L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtRedeemLimit, adminUpdateRewardViewModelRedeemLimitGetValue);
        }
        if ((dirtyFlags & 0x20L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.txtRedeemLimit, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, txtRedeemLimitandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.txtRewardDescription, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, txtRewardDescriptionandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.txtRewardName, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, txtRewardNameandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.txtRewardPoints, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, txtRewardPointsandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x38L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtRewardDescription, adminUpdateRewardViewModelRewardDescriptionGetValue);
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtRewardName, adminUpdateRewardViewModelRewardNameGetValue);
        }
        if ((dirtyFlags & 0x32L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtRewardPoints, adminUpdateRewardViewModelRewardPointsGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): adminUpdateRewardViewModel.rewardName
        flag 1 (0x2L): adminUpdateRewardViewModel.rewardPoints
        flag 2 (0x3L): adminUpdateRewardViewModel.redeemLimit
        flag 3 (0x4L): adminUpdateRewardViewModel.rewardDescription
        flag 4 (0x5L): adminUpdateRewardViewModel
        flag 5 (0x6L): null
    flag mapping end*/
    //end
}