package com.mninetytechnology.mahamillateapp.presenter;

import android.database.Observable;
import android.util.Log;

import androidx.databinding.ObservableField;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.MobileCheckActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.MobileCheckContract;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OtpResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 19/7/2023.
 */
public class MobileCheckPresenter implements MobileCheckContract.Presenter {
    private final MobileCheckActivity mActivity;
    private final MobileCheckContract.ViewModel mViewModel;

    public ObservableField<String> mobile;

    public MobileCheckPresenter(MobileCheckActivity mActivity, MobileCheckContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
        mobile = new ObservableField<>();
    }

    @Override
    public void validateData() {
        if (mobile.get().trim().isEmpty()) {
            mViewModel.showResponseFailed(mActivity.getResources().getString(R.string.empty_phone_number));
        } else if (mobile.get().trim().length() != 10) {
            mViewModel.showResponseFailed(mActivity.getResources().getString(R.string.invalid_mobile_number));
        } else {
            mViewModel.sendOtp();
        }
    }

    @Override
    public void getOtp() {
        if(mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);
            String otp = generatePin();
            RetrofitClient.getApiService().getOtp(mobile.get(),otp).enqueue(new Callback<OtpResponseModel>() {
                @Override
                public void onResponse(Call<OtpResponseModel> call, Response<OtpResponseModel> response) {
                    mActivity.dismissProgressDialog();
                    if (response.code() == 200 && response.body() != null) {
                        if (response.body().getErrorMessage().equalsIgnoreCase("Done")) {
                            mViewModel.goToNextPage(otp);
                        } else {
                            mViewModel.showResponseFailed(response.body().getErrorMessage());
                        }
                    } else {
                        mViewModel.showResponseFailed(mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<OtpResponseModel> call, Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showResponseFailed(mActivity.getResources().getString(R.string.invalid_response));
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    private String generatePin() {
        Random random = new Random();
        String generatedPassword = String.format("%04d", random.nextInt(10000));

        Log.d("MyApp", "Generated Password : " + generatedPassword);
        return generatedPassword;
    }
}
