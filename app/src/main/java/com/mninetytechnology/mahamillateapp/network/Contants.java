package com.mninetytechnology.mahamillateapp.network;

import android.content.Context;
import android.os.Environment;

public class Contants {
    public static final String BASE_URL = "https://api.milletsindia.org/";
    public static final String GET_LOGIN = "auth/login";
    public static final String GET_VERIFY_OTP = "http://43.231.126.249/api/mt/SendSMS";
    public static final String REGISTER_USER = "user";
    public static final String BLOG = "blog";
    public static final String VIDEOS = "media";
    public static final String UPDATE_LIKE = "blog/like";
    public static final String UPDATE_DISLIKE = "blog/dislike";
    public static final String UPDATE_VIEW = "blog/view";
    public static final String UPDATE_SHARE = "blog/share";
    public static final String QUIZ = "quiz/class";
    public static final String GET_QUIZ_DATA = "score";
    public static final String LEADERBORAD = "score/leaderboard";
    public static final String UPDATE_SCORE = "score";
    public static final String GET_CLASS = "class";

    public static String PREF_IS_LOGIN = "PREF_IS_LOGIN";
    public static String PREF_AUTH_TOKEN = "PREF_AUTH_TOKEN";

    public static final String LAST_FIRST_LEVEL_SCORE = "com.mninetytechnology.mahamillateapp.SYNC_LAST_FIRST_LEVEL_SCORE";
    public static String APP_LOGIN_USER_ID = "com.mninetytechnology.mahamillateapp.SYNC_LOGIN_USER_ID";
    public static String APP_SERVER_LOGIN_USER_ID = "com.mninetytechnology.mahamillateapp.SYNC_LOGIN_SERVER_USER_ID";
    public static String APP_SERVER_LOGIN_USER_CLASS = "com.mninetytechnology.mahamillateapp.SYNC_LOGIN_SERVER_USER_CLASS";
    public static String APP_LOGIN_USER_KEY = "com.mninetytechnology.mahamillateapp.SYNC_LOGIN_USER_KEY";
    public static String APP_USER_ID = "com.mninetytechnology.mahamillateapp.SYNC_USER_ID";
    public static String APP_LOGIN_USER_ROLE = "com.mninetytechnology.mahamillateapp.SYNC_LOGIN_USER_ROLE";
    public static String SYNC_LOGIN_DATE_TIME_DATA = "com.mninetytechnology.mahamillateapp.SYNC_LOGIN_DATE_TIME_DATA";
    public static String SYNC_DATE_TIME_ALL_DATA = "com.mninetytechnology.mahamillateapp.all_data_sync_date_time";
    public static String SYNC_DATE_TIME_CANDIDATE_DATA = "com.mninetytechnology.mahamillateapp.candidate_data_sync_date_time";
}
