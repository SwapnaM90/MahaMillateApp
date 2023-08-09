package com.mninetytechnology.mahamillateapp.acitivities.ui.quiz;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityQuizCertificateBinding;

public class QuizCertificateActivity extends BaseActivity {
    private ActivityQuizCertificateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_quiz_certificate);
        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOnTop(QuizShareActivity.class,true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(QuizMainActivity.class,true);
    }
}