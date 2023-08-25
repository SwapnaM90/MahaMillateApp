package com.mninetytechnology.mahamillateapp.presenter;

import android.text.TextUtils;
import android.view.View;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.OtpCheckActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.OtpContract;

/**
 * Created by Swapna Thakur on 3/1/2022.
 */
public class OtpPresenter implements OtpContract.Presenter {
    private final OtpContract.ViewModel mViewModel;
    private final OtpCheckActivity mActivity;
    public View rootView;
    public String otp;
    private static final String TAG = "OtpPresenter";

    public OtpPresenter(OtpContract.ViewModel mViewModel, OtpCheckActivity mActivity) {
        this.mViewModel = mViewModel;
        this.mActivity = mActivity;
        //StartFirebaseLogin();
    }

    private boolean validate() {
        if (TextUtils.isEmpty(mActivity.mBinding.pinview.getValue())) {
            mViewModel.showOtpFailed(mActivity.getResources().getString(R.string.empty_otp));
            return false;
        } else if (mActivity.mBinding.pinview.getValue().length() < 4) {
            mViewModel.showOtpFailed(mActivity.getResources().getString(R.string.fill_complete_otp));
            return false;
        } else if (!(mActivity.mBinding.pinview.getValue().equalsIgnoreCase(otp))) {
            mViewModel.showOtpFailed(mActivity.getResources().getString(R.string.enter_valid_otp));
            return false;
        }

        return true;
    }

    @Override
    public void verifyOtp() {
        if (validate()) {
            mViewModel.getOtp();
        }
    }
}
