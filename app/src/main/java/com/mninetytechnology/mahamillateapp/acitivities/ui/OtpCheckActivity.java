package com.mninetytechnology.mahamillateapp.acitivities.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mninetytechnology.mahamillateapp.Helpers.GlobalHelper;
import com.mninetytechnology.mahamillateapp.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityOtpCheckBinding;
import com.mninetytechnology.mahamillateapp.lib.AppKeys;
import com.mninetytechnology.mahamillateapp.lib.ScreenHelper;
import com.mninetytechnology.mahamillateapp.listeners.MessageListener;
import com.mninetytechnology.mahamillateapp.models.contracts.OtpContract;
import com.mninetytechnology.mahamillateapp.presenter.OtpPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class OtpCheckActivity extends BaseActivity implements OtpContract.ViewModel, MessageListener {
    public ActivityOtpCheckBinding mBinding;
    private String otp;
    private String phone_number;
    private OtpPresenter mPresenter;
    private GlobalHelper helper;
    private static final String TAG = "OtpCheckActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mBinding = ActivityOtpCheckBinding.inflate(getLayoutInflater());
            setContentView(mBinding.getRoot());
            Intent intent = getIntent();
            FirebaseApp.initializeApp(this);
            if(intent.hasExtra(AppKeys.OTP)) {
                otp = intent.getStringExtra(AppKeys.OTP);
                phone_number = intent.getStringExtra(AppKeys.PHONE_NUMBER);
            }
            helper = new GlobalHelper(this);
            mPresenter = new OtpPresenter(this,OtpCheckActivity.this);
            mPresenter.rootView = mBinding.getRoot();
            mBinding.setPresenter(mPresenter);
            mPresenter.otp = otp;
        } catch (Exception e) {
            Log.e(TAG, "onCreate: "+e.getMessage());
        }
    }

    @Override
    public void getOtp() {
        //save date to shared preference
        try {
            if(isInternetConnected()) {
                ScreenHelper.showGreenSnackBar(mBinding.getRoot(),getResources().getString(R.string.sucessfull_login));
                goToDashboard();
            } else {
                showNotInternetConnected(new OnInternetConnectedListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        } catch (Exception e) {
            Log.e(TAG, "getOtp: "+e.getMessage());
        }
    }


    /**
     * @date 3-3-2022
     * navigates to dashboard activity
     */
    private void goToDashboard() {
        Intent intent = new Intent(OtpCheckActivity.this,RegistrationActivity.class);
        intent.putExtra(AppKeys.PHONE_NUMBER,phone_number);
        startActivityOnTop(false,intent);
    }

    @Override
    public void showOtpFailed(String error) {
        ScreenHelper.showErrorSnackBar(mBinding.getRoot(),error);
    }

    @Override
    public void messageReceived(String message) {
        Log.i("OTPCA::", "OTP_: " + message);
        if (!message.equalsIgnoreCase("")) {
            if (message.contains(":")) {
                String[] data = message.split(":");
                message = data[1];
                mBinding.pinview.setValue(message);
            }
            Log.i("OCA:","OTP Received : " + message);
        } else {
            Log.i("OCA:","OTP not Received : ");
        }
    }
}