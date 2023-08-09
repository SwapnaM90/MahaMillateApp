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
    private String loginUserId;
    private String phone_number;
    private OtpPresenter mPresenter;
    private GlobalHelper helper;
    private static final String TAG = "OtpCheckActivity";

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    FirebaseAuth auth;
    public String verificationCode = "1234";

    //public String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mBinding = ActivityOtpCheckBinding.inflate(getLayoutInflater());
            setContentView(mBinding.getRoot());
            Intent intent = getIntent();
            FirebaseApp.initializeApp(this);
            if(intent.hasExtra(AppKeys.loginUserId)) {
                loginUserId = intent.getStringExtra(AppKeys.loginUserId);
                phone_number = intent.getStringExtra(AppKeys.PHONE_NUMBER);
            }
            //mBinding.pinview.setValue("1111");
            helper = new GlobalHelper(this);
            mPresenter = new OtpPresenter(this,OtpCheckActivity.this);
            mPresenter.rootView = mBinding.getRoot();
            mPresenter.loginId = loginUserId;
            mBinding.setPresenter(mPresenter);
            //mPresenter.sendOtp(phone_number);

            //StartFirebaseLogin();
//            PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                    phone_number,                     // Phone number to verify
//                    60,                           // Timeout duration
//                    TimeUnit.SECONDS,                // Unit of timeout
//                    OtpCheckActivity.this,        // Activity (for callback binding)
//                    mCallback);
        } catch (Exception e) {
            Log.e(TAG, "onCreate: "+e.getMessage());
        }
    }

    @Override
    public void getOtp() {
        //save date to shared preference
        try {
            Calendar calendar = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String dateTime = sdf.format(calendar.getTime());
            helper.getSharedPreferencesHelper().setLoginDateTimeData(dateTime);
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

    @Override
    public void saveOtp(String otp) {
        //this.otp = otp;
    }

    /**
     * @date 3-3-2022
     * navigates to dashboard activity
     */
    private void goToDashboard() {
        startActivityOnTop(MainActivity.class,false);
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

    private void StartFirebaseLogin() {

        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(OtpCheckActivity.this,"verification completed",Toast.LENGTH_SHORT).show();
                //mViewModel.getOtp();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(OtpCheckActivity.this,"verification failed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(OtpCheckActivity.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };
    }
}