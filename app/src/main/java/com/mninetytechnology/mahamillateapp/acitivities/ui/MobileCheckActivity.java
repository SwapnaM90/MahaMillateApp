package com.mninetytechnology.mahamillateapp.acitivities.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityMobileCheckBinding;
import com.mninetytechnology.mahamillateapp.lib.AppKeys;
import com.mninetytechnology.mahamillateapp.models.contracts.MobileCheckContract;
import com.mninetytechnology.mahamillateapp.presenter.MobileCheckPresenter;

public class MobileCheckActivity extends BaseActivity implements MobileCheckContract.ViewModel {
    private ActivityMobileCheckBinding binding;
    private MobileCheckPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mobile_check);
        presenter = new MobileCheckPresenter(this,this);
        binding.setPresenter(presenter);
    }

    @Override
    public void sendOtp() {
        presenter.getOtp();
    }

    @Override
    public void goToNextPage(String otp) {
        Intent intent = new Intent(MobileCheckActivity.this,OtpCheckActivity.class);
        intent.putExtra(AppKeys.OTP,otp);
        intent.putExtra(AppKeys.PHONE_NUMBER,binding.edtMobileNumber.getText().toString());
        startActivityOnTop(true,intent);
    }

    @Override
    public void showResponseFailed(String error) {

    }
}