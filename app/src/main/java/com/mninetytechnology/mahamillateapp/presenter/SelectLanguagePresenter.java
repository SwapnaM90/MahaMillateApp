package com.mninetytechnology.mahamillateapp.presenter;


import android.view.View;

import com.mninetytechnology.mahamillateapp.MainActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.SelectLanguageContract;

/**
 * Created by Swapna Thakur on 3/1/2022.
 * Presenter for splash to check last login date
 */
public class SelectLanguagePresenter implements SelectLanguageContract.Presenter {
    private final SelectLanguageContract.ViewModel mViewModel;
    public View rootView;
    private static final String TAG = "SplashPresenter";

    public SelectLanguagePresenter(SelectLanguageContract.ViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    @Override
    public void continueNext() {
        mViewModel.startAnotherActivity(MainActivity.class, true);
    }
}
