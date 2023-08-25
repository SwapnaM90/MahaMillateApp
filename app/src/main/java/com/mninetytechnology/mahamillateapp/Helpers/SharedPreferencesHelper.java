/*
 * Copyright (c) - 2020 & Created By AbhishekR - EncureIT :)
 */

package com.mninetytechnology.mahamillateapp.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mninetytechnology.mahamillateapp.lib.AppKeys;
import com.mninetytechnology.mahamillateapp.network.Contants;


public class SharedPreferencesHelper extends GlobalHelper {

    SharedPreferences preferences;

    public SharedPreferencesHelper(Context context) {
        super(context);
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public void clear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public void setPrefLoginUser(String user) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.PREF_LOGIN, user);
        editor.apply();
    }

    public String getPrefLoginUser() {
        return preferences.getString(Contants.PREF_LOGIN, "");
    }

    public void setPrefAuthToken(String auth_token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.PREF_AUTH_TOKEN, auth_token);
        editor.apply();
    }

    public void setLoginDateTimeData(String dateTime) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.SYNC_LOGIN_DATE_TIME_DATA, dateTime);
        editor.apply();
    }

    public void setLoginUserId(String loginUserId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.APP_LOGIN_USER_ID, loginUserId);
        editor.apply();
    }

    public void setLoginServerUserId(String loginUserId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.APP_SERVER_LOGIN_USER_ID, loginUserId);
        editor.apply();
    }

    public void setLoginServerUserClass(String loginUserClass) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.APP_SERVER_LOGIN_USER_CLASS, loginUserClass);
        editor.apply();
    }

    public void setLoginKey(String loginKey) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.APP_LOGIN_USER_KEY, loginKey);
        editor.apply();
    }

    public void setAddress(String address) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.APP_ADDRESS, address);
        editor.apply();
    }

    public void setLoginUserRole(String loginUserRole) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.APP_LOGIN_USER_ROLE, loginUserRole);
        editor.apply();
    }

    public void setLastSyncTimeAllData(String dateTime) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.SYNC_DATE_TIME_ALL_DATA, dateTime);
        editor.apply();
    }

    public void setLastSyncTimeCandidateData(String dateTime) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.SYNC_DATE_TIME_CANDIDATE_DATA, dateTime);
        editor.apply();
    }

    public void setLastFirstLevelScore(String score) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Contants.LAST_FIRST_LEVEL_SCORE, score);
        editor.apply();
    }

    public String getLoginDateTimeData() {
        return preferences.getString(Contants.SYNC_LOGIN_DATE_TIME_DATA, "");
    }

    public String getLoginUserId() {
        return preferences.getString(Contants.APP_LOGIN_USER_ID, "");
    }

    public String getLoginServerUserId() {
        return preferences.getString(Contants.APP_SERVER_LOGIN_USER_ID, "");
    }

    public String getLoginServerUserClass() {
        return preferences.getString(Contants.APP_SERVER_LOGIN_USER_CLASS, "");
    }

    public String getLoginKey() {
        return preferences.getString(Contants.APP_LOGIN_USER_KEY, "");
    }

    public String getAddress() {
        return preferences.getString(Contants.APP_ADDRESS, "");
    }

    public String getLoginUserRole() {
        return preferences.getString(Contants.APP_LOGIN_USER_ROLE, "");
    }

    public String getLastSyncAllDataTime() {
        return preferences.getString(Contants.SYNC_DATE_TIME_ALL_DATA, "");
    }

    public String getLastSyncCandidateDataTime() {
        return preferences.getString(Contants.SYNC_DATE_TIME_CANDIDATE_DATA, "");
    }

    public String getLastFirstLevelScore() {
        return preferences.getString(Contants.LAST_FIRST_LEVEL_SCORE, "0");
    }

    public String getPrefAuthToken() {
        return preferences.getString(Contants.PREF_AUTH_TOKEN, "");
    }

    public void setQuizLanguage(String language) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AppKeys.QUIZ_LANGUAGE, language);
        editor.apply();
    }

    public String getQuizLanguage() {
        return preferences.getString(AppKeys.QUIZ_LANGUAGE, AppKeys.ENGLISH);
    }

    public void setUser(String user_type) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AppKeys.USER_TYPE, user_type);
        editor.apply();
    }

    public String getUser() {
        return preferences.getString(AppKeys.USER_TYPE, AppKeys.USER);
    }


}
