package com.mninetytechnology.mahamillateapp;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.mninetytechnology.mahamillateapp.Helpers.GlobalHelper;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.adapters.SplashGridAdapter;
import com.mninetytechnology.mahamillateapp.databinding.ActivitySplashBinding;
import com.mninetytechnology.mahamillateapp.lib.ScreenHelper;
import com.mninetytechnology.mahamillateapp.models.contracts.SplashContract;
import com.mninetytechnology.mahamillateapp.presenter.SplashPresenter;

public class SplashActivity extends BaseActivity implements SplashContract.ViewModel {
    private ActivitySplashBinding mBinding;
    private SplashPresenter mPresenter;
    private Runnable mRunnable;
    private Handler mHandler;
    private final static long SPLASH_INTERVAL_IN_MILLIS = 500;
    private GlobalHelper helper;
    private static final String TAG = "SplashActivity";

    Integer[] drawables = {
            R.drawable.farmer,
            R.drawable.millapte_app_icon,
            R.drawable.rcf,
            R.drawable.tifan_foundation
    };

    private SplashGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mPresenter = new SplashPresenter(this,this);
        mPresenter.rootView = mBinding.getRoot();
        mBinding.setPresenter(mPresenter);
        initObject();
        init();
    }

    private void initObject() {
        helper = new GlobalHelper(this);
    }

    private void init() {
        adapter = new SplashGridAdapter(SplashActivity.this,drawables);
        mBinding.grdLayout.setAdapter(adapter);

        mHandler = new Handler();
        mRunnable = () -> {
            mPresenter.checkLastLoginDate(helper);
        };
    }

    @Override
    public void startAnotherActivity(Class<?> cls, boolean finishCurrent) {
        startActivityOnTop(cls, finishCurrent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            mHandler.postDelayed(mRunnable, SPLASH_INTERVAL_IN_MILLIS);
        } catch (Exception e) {
            Toast.makeText(mActivity, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            mHandler.removeCallbacks(mRunnable);
        } catch (Exception e) {
            Toast.makeText(mActivity, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        ScreenHelper.exitAppWithoutDialog(SplashActivity.this);
    }
}