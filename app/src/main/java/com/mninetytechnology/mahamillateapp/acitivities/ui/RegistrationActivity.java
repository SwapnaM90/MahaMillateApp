package com.mninetytechnology.mahamillateapp.acitivities.ui;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.mninetytechnology.mahamillateapp.Helpers.GlobalHelper;
import com.mninetytechnology.mahamillateapp.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.custom.RoundedCornerEditText;
import com.mninetytechnology.mahamillateapp.databinding.ActivityRegistrationBinding;
import com.mninetytechnology.mahamillateapp.lib.AppKeys;
import com.mninetytechnology.mahamillateapp.lib.ScreenHelper;
import com.mninetytechnology.mahamillateapp.models.contracts.RegisterContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.SingleClass;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.presenter.RegisterPresenter;

import java.util.List;

public class RegistrationActivity extends BaseActivity implements RegisterContract.ViewModel {
    private ActivityRegistrationBinding binding;
    private RegisterPresenter presenter;

    private GlobalHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_registration);
        helper = new GlobalHelper(this);
        presenter = new RegisterPresenter(this,this);
        binding.setPresenter(presenter);
        //presenter.getClassData();
    }

    @Override
    public void register(UserLoginObject userLoginObject,String token) {
        try {
            //save user id and role in shared preference
            helper.getSharedPreferencesHelper().setLoginServerUserId(userLoginObject.get_id());
            helper.getSharedPreferencesHelper().setLoginServerUserClass(userLoginObject.getClass_text());
            helper.getSharedPreferencesHelper().setLoginKey(token);

            //start otp check activity
            Intent intent = new Intent(this, OtpCheckActivity.class);
            intent.putExtra(AppKeys.loginUserId,userLoginObject.get_id());
            intent.putExtra(AppKeys.PHONE_NUMBER, RoundedCornerEditText.getMy_text(binding.edtPhoneNumber));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityOnTop(false,intent);
        } catch (Exception e) {
            Log.d(TAG, "login: "+e.getMessage());
            ScreenHelper.showErrorSnackBar(binding.getRoot(),e.getMessage());
        }
    }

    @Override
    public void showRegisterFailed(String error) {
        ScreenHelper.showErrorSnackBar(binding.getRoot(),error);
    }

    @Override
    public void setUpClass(List<SingleClass> classes) {
        ArrayAdapter<SingleClass> adapter = new ArrayAdapter<>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,classes);
        binding.sprClassText.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(LoginActivity.class,true);
    }
}