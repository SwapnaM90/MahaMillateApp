package com.mninetytechnology.mahamillateapp.presenter;


import static com.mninetytechnology.mahamillateapp.utils.CommonUtils.getCurrentDateOnly;

import android.util.Log;
import android.view.View;

import com.mninetytechnology.mahamillateapp.Helpers.GlobalHelper;
import com.mninetytechnology.mahamillateapp.MainActivity;
import com.mninetytechnology.mahamillateapp.acitivities.base.IntroActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.LoginActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.RegistrationActivity;
import com.mninetytechnology.mahamillateapp.lib.ScreenHelper;
import com.mninetytechnology.mahamillateapp.models.contracts.SplashContract;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Swapna Thakur on 3/1/2022.
 * Presenter for splash to check last login date
 */
public class SplashPresenter implements SplashContract.Presenter {
    private final SplashContract.ViewModel mViewModel;
    public View rootView;
    private static final String TAG = "SplashPresenter";

    public SplashPresenter(SplashContract.ViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    /**
     * Checks last login date
     * @param helper
     */
    @Override
    public void checkLastLoginDate(GlobalHelper helper) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
        String loginDate = helper.getSharedPreferencesHelper().getLoginDateTimeData();
        //String loginDate = "2019/10/28";
        String currentDate = getCurrentDateOnly();
        if (!loginDate.isEmpty()) {
            try {
                    Date currentD = myFormat.parse(currentDate);
                    Date loginD = myFormat.parse(loginDate);
                    long difference = currentD.getTime() - loginD.getTime();
                    float daysBetween = (difference / (1000 * 60 * 60 * 24));
                    /* You can also convert the milliseconds to days using this method
                     * float daysBetween = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS) */
                    if (daysBetween < 15) {
                        mViewModel.startAnotherActivity(MainActivity.class, true);
                        Log.i("SplashActivity::", "direct start");
                    } else {
                        try {
                            mViewModel.startAnotherActivity(LoginActivity.class, true);
                        } catch (Exception e) {
                            Log.e(TAG, "checkLastLoginDate: " + e.getMessage());
                        }
                        Log.i("SplashActivity::", "login expired start");
                    }
                } catch (Exception e) {
                    ScreenHelper.showErrorSnackBar(rootView, e.getMessage());
                }
        } else {
            // todo direct login screen
            try {
                if (helper.getSharedPreferencesHelper().getLoginUserId().trim().isEmpty()) {
                    mViewModel.startAnotherActivity(IntroActivity.class, true);
                } else {
                    mViewModel.startAnotherActivity(MainActivity.class, true);
                }
            } catch (Exception e) {
                Log.e(TAG, "checkLastLoginDate: "+e.getMessage());
            }
            Log.i("SplashActivity::", "Freshly start");
        }
    }
}
