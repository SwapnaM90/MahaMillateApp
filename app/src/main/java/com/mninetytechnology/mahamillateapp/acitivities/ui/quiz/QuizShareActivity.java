package com.mninetytechnology.mahamillateapp.acitivities.ui.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;

public class QuizShareActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_share);
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(QuizMainActivity.class,true);
    }
}