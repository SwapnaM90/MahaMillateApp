package com.mninetytechnology.mahamillateapp.network.retrofit;


import static com.mninetytechnology.mahamillateapp.network.Contants.BASE_URL;

import com.mninetytechnology.mahamillateapp.network.reposervices.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClientLogin {

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }

    final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();
}
