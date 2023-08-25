package com.mninetytechnology.mahamillateapp.acitivities.ui.user.quiz;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityQuizLevelsBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.QuizLevelContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizLevelData;
import com.mninetytechnology.mahamillateapp.presenter.QuizLevelPresenter;

public class QuizLevelsActivity extends BaseActivity implements QuizLevelContract.ViewModel {
    private ActivityQuizLevelsBinding mBinding;
    private QuizLevelPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz_levels);
        mPresenter = new QuizLevelPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mPresenter.getLevelData();
        mBinding.toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void goToNext() {
        startActivityOnTop(QuizQuestionsActivity.class, true);
    }

    @Override
    public void setUpLevel(QuizLevelData quizLevelDataData) {

    }

    @Override
    public void showFailed(String error) {
        showErrorSnackBar(mBinding.getRoot(), error);
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(QuizMainActivity.class, true);
    }
}