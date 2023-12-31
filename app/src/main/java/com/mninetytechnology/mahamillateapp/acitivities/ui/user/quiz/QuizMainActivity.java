package com.mninetytechnology.mahamillateapp.acitivities.ui.user.quiz;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.mninetytechnology.mahamillateapp.acitivities.ui.user.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityQuizMainBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.QuizMainContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizLevelData;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
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
    public void setQuiz(UserLoginObject quizLevelData) {
        int score = Integer.parseInt(quizLevelData.getScore());
        if (quizLevelData != null && score != 0) {
            mBinding.llPoints.setVisibility(View.VISIBLE);
        } else {
            mBinding.llPoints.setVisibility(View.GONE);
        }
        mBinding.setQuizLevelData(quizLevelData);
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