package com.mninetytechnology.mahamillateapp.acitivities.ui.user.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.google.gson.Gson;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.MainActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityMyPerformanceBinding;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;

public class MyPerformanceActivity extends BaseActivity {
    private ActivityMyPerformanceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_performance);
        Gson gson = new Gson();
        UserLoginObject user = gson.fromJson(getGlobalHelper().getSharedPreferencesHelper().getPrefLoginUser(),UserLoginObject.class);
        if (user != null) {
            binding.setUser(user);
        }
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(MainActivity.class,true);
    }
}