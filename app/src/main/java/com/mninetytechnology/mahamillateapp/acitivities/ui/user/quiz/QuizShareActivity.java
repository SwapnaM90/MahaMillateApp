package com.mninetytechnology.mahamillateapp.acitivities.ui.user.quiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityQuizShareBinding;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;

public class QuizShareActivity extends BaseActivity {
    private ActivityQuizShareBinding binding;
    private UserLoginObject user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_quiz_share);
        Gson gson = new Gson();
        String str_user = getGlobalHelper().getSharedPreferencesHelper().getPrefLoginUser();
        user = gson.fromJson(str_user,UserLoginObject.class);
        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_share = "Please install this app here https://play.google.com/store/apps/details?id=com.mninetytechnology.mahamillateapp " +
                        "and register with this. Please add "+user.getReferral_code()+" this code while registering";

                String str = "Get ready to put your knowledge to the test at the Maha Millet Quiz competition! " +
                        "It's time to showcase your expertise and learn more about the fascinating world of millets. " +
                        "Best of luck to all participants. You can install app form https://play.google.com/store/apps/details?id=com.mninetytechnology.mahamillateapp"+
                        " here and please add this referral code "+user.getReferral_code()+" in registration";
                sendMessage(str);
            }
        });
    }

    private void sendMessage(String message) {
        // Creating new intent
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(QuizMainActivity.class, true);
    }
}