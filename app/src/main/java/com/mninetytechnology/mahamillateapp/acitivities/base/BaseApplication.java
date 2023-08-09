/*
 * Copyright (c) - 2020 & Created By AbhishekR - EncureIT :)
 */

package com.mninetytechnology.mahamillateapp.acitivities.base;

import android.content.Context;
import android.os.StrictMode;

import androidx.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;
import com.mninetytechnology.mahamillateapp.Helpers.GlobalHelper;


public class BaseApplication extends MultiDexApplication {
    private static Context appsContext;
    private static BaseApplication instances;
    private static GlobalHelper globalHelper;

    public static BaseApplication getInstances() {
        return instances;
    }

    public static Context getAppsContext() {
        return appsContext;
    }

    private void setStrictPolicy() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public void onCreate() {
        appsContext = this;
        instances = this;

        super.onCreate();
//        DatabaseUtil.init(getApplicationContext());
        FirebaseApp.initializeApp(this);
        setStrictPolicy();
    }

    /*@Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }*/

    public GlobalHelper getGlobalHelper() {
        if (globalHelper == null) {
            globalHelper = new GlobalHelper(this);
        }
        return globalHelper;
    }
}
