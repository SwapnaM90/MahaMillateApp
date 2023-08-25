package com.mninetytechnology.mahamillateapp.presenter;


import android.view.View;

import com.mninetytechnology.mahamillateapp.acitivities.ui.LoginActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.SelectUserContract;

/**
 * Created by Swapna Thakur on 3/1/2022.
 * Presenter for splash to check last login date
 */
public class SelectUserPresenter implements SelectUserContract.Presenter {
    private final SelectUserContract.ViewModel mViewModel;
    public View rootView;
    private static final String TAG = "SplashPresenter";

    public SelectUserPresenter(SelectUserContract.ViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    @Override
    public void continueNext() {
        mViewModel.startAnotherActivity(LoginActivity.class, true);
    }
}
