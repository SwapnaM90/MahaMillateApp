package com.mninetytechnology.mahamillateapp.acitivities.ui.quiz;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.mninetytechnology.mahamillateapp.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityQuizMainBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.QuizMainContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizLevelData;
import com.mninetytechnology.mahamillateapp.presenter.QuizMainPresenter;

public class QuizMainActivity extends BaseActivity implements QuizMainContract.ViewModel {
    private ActivityQuizMainBinding mBinding;
    private QuizMainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz_main);
        mPresenter = new QuizMainPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mPresenter.getQuizData();
        mBinding.pbLevel.setProgress(30);
        mBinding.llPoints.setVisibility(View.GONE);
        mBinding.toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    public void setQuiz(QuizLevelData quizLevelData) {
        if (quizLevelData != null && quizLevelData.getFirstLevel() != 0) {
            mBinding.llPoints.setVisibility(View.VISIBLE);
            mBinding.setQuizLevelData(quizLevelData);
        }
    }

    @Override
    public void showFailed(String error) {
        showErrorSnackBar(mBinding.getRoot(), error);
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(MainActivity.class, true);
    }
}