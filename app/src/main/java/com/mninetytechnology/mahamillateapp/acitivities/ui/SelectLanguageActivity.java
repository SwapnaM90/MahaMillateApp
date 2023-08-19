package com.mninetytechnology.mahamillateapp.acitivities.ui;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivitySelectLanguageBinding;
import com.mninetytechnology.mahamillateapp.lib.AppKeys;
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
        mBinding.rgSelectLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_hindi) {
                    saveLanguage(AppKeys.HINDI);
                }else if (i == R.id.rb_english) {
                    saveLanguage(AppKeys.ENGLISH);
                }else if (i == R.id.rb_marathi) {
                    saveLanguage(AppKeys.MARATHI);
                }
            }
        });
    }

    /**
     * saves language in shared pref
     * @param language
     */
    private void saveLanguage(String language) {
        getGlobalHelper().getSharedPreferencesHelper().setQuizLanguage(language);
    }

    @Override
    public void startAnotherActivity(Class<?> cls, boolean finishCurrent) {
        startActivityOnTop(cls, finishCurrent);
    }

}