package com.mninetytechnology.mahamillateapp.presenter;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.OtpCheckActivity;
import com.mninetytechnology.mahamillateapp.lib.ScreenHelper;
import com.mninetytechnology.mahamillateapp.models.contracts.OtpContract;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OtpResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
