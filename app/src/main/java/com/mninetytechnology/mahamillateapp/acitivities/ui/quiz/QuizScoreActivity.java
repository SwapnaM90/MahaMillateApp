package com.mninetytechnology.mahamillateapp.acitivities.ui.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityQuizScoreBinding;
import com.mninetytechnology.mahamillateapp.lib.AppKeys;
import com.mninetytechnology.mahamillateapp.models.contracts.QuizScoreContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizScore;
import com.mninetytechnology.mahamillateapp.presenter.QuizScorePresenter;

public class QuizScoreActivity extends BaseActivity implements QuizScoreContract.ViewModel {
    private ActivityQuizScoreBinding binding;
    private QuizScore score;
    private QuizScorePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_quiz_score);
        Intent intent = getIntent();
        if (intent.hasExtra(AppKeys.SCORE)) {
            score = intent.getParcelableExtra(AppKeys.SCORE);
            if (score != null) {
                mPresenter = new QuizScorePresenter(this,this);
                mPresenter.uploadScore(score);
            }
        }
        binding.saDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOnTop(QuizCertificateActivity.class,true);
            }
        });
    }

    @Override
    public void setUpScore() {
        binding.setQuizScore(score);
        getGlobalHelper().getSharedPreferencesHelper().setLastFirstLevelScore(String.valueOf(score.getScore()));
    }

    @Override
    public void showScoreFailed(String error) {
        showErrorSnackBar(binding.getRoot(),error);
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(QuizMainActivity.class,true);
    }
}