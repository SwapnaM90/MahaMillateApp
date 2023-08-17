package com.mninetytechnology.mahamillateapp.acitivities.ui;

import android.os.Bundle;

import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivitySelectLanguageBinding;
import com.mninetytechnology.mahamillateapp.models.contracts.SelectLanguageContract;
import com.mninetytechnology.mahamillateapp.presenter.SelectLanguagePresenter;

public class SelectLanguageActivity extends BaseActivity implements SelectLanguageContract.ViewModel {
    private ActivitySelectLanguageBinding mBinding;
    private SelectLanguagePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySelectLanguageBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mPresenter = new SelectLanguagePresenter(this);
        mPresenter.rootView = mBinding.getRoot();
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void startAnotherActivity(Class<?> cls, boolean finishCurrent) {
        startActivityOnTop(cls, finishCurrent);
    }

}