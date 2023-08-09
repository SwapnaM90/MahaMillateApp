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
    public String loginId;
    public View rootView;
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
        } else if (!(mActivity.mBinding.pinview.getValue().equalsIgnoreCase(mActivity.verificationCode))) {
            mViewModel.showOtpFailed(mActivity.getResources().getString(R.string.enter_valid_otp));
            return false;
        }

        return true;
    }


    @Override
    public void sendOtp(String phone_number) {
        if(mActivity.isInternetConnected()) {
//            StartFirebaseLogin();
//            PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                    phone_number,                     // Phone number to verify
//                    60,                           // Timeout duration
//                    TimeUnit.SECONDS,                // Unit of timeout
//                    mActivity,        // Activity (for callback binding)
//                    mCallback);
//            try {
//                mActivity.startProgressDialog(mActivity);
//                String otp = generatePin();
//                String text = getOtpTextScreen(otp);
//                //mActivity.disableForm();
//                RetrofitClient.getApiService().getOtp(phone_number,text).enqueue(new Callback<OtpResponseModel>() {
//                    @Override
//                    public void onResponse(@NonNull Call<OtpResponseModel> call, @NonNull Response<OtpResponseModel> response) {
//                        if (response.code() == 200 || response.code() == 201) {
//                            if (response.body().getErrorMessage().equalsIgnoreCase("Done")) {
//                                mViewModel.saveOtp(otp);
//                            } else {
//                                mViewModel.showOtpFailed(response.body().getErrorMessage());
//                            }
//                        } else {
//                            mViewModel.showOtpFailed(""+mActivity.getResources().getString(R.string.invalid_response));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Call<OtpResponseModel> call, @NonNull Throwable t) {
//                        mViewModel.showOtpFailed(t.getMessage());
//                    }
//                });
//            } catch (Exception e) {
//                Log.e(TAG, "sendOtp: "+e.getMessage());
//            }


        } else {
            mActivity.showNotInternetConnected(new BaseActivity.OnInternetConnectedListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    public void verifyOtp() {
        if (validate()) {
            mViewModel.getOtp();
        }
    }

//    private String getOtpTextScreen(String otp) {
//        String str = "Hello%20Champ,%20Welcome%20to%20MahaMillet.%20use%20this%20OTP%20"+otp+"%20to%20log%20in%20to%20your%20Mahamillet%20App.%20This%20OTP%20will%20be%20valid%20for%20the%20next%203%20mins.%20sminfo";
//        return str;
//    }
//
//    private String generatePin() {
//        Random random = new Random();
//        String generatedPassword = String.format("%04d", random.nextInt(10000));
//
//        Log.d("MyApp", "Generated Password : " + generatedPassword);
//        return generatedPassword;
//    }

}
