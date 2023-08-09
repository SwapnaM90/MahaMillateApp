package com.mninetytechnology.mahamillateapp.presenter;

import android.util.Log;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.blog.BlogActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.BlogContract;
import com.mninetytechnology.mahamillateapp.network.responsemodel.BlogResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 19/7/2023.
 */
public class BlogPresenter implements BlogContract.Presenter {
    private final BlogActivity mActivity;
    private final BlogContract.ViewModel mViewModel;

    public BlogPresenter(BlogActivity mActivity, BlogContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
    }

    @Override
    public void getBlogs() {
        if(mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);

            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().getBlogs().enqueue(new Callback<BlogResponseModel>() {
                @Override
                public void onResponse(Call<BlogResponseModel> call, Response<BlogResponseModel> response) {
                    mActivity.dismissProgressDialog();
                    if (response.code() == 200 && response.body() != null) {
                        mViewModel.setUpBlogAdapter(response.body().getData());
                    } else {
                        mViewModel.showBlogFailed(mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<BlogResponseModel> call, Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showBlogFailed(mActivity.getResources().getString(R.string.invalid_response));
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void updateLike(boolean isAdd) {
       String type = "REMOVE";
        if (isAdd) {
            type = "ADD";
        }
        if(mActivity.isInternetConnected()) {
//            mActivity.startProgressDialog(mActivity);

            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().updateLike(type).enqueue(new Callback<BlogResponseModel>() {
                @Override
                public void onResponse(Call<BlogResponseModel> call, Response<BlogResponseModel> response) {
//                    mActivity.dismissProgressDialog();
//                    if (response.code() == 200 && response.body() != null) {
//                        mViewModel.setUpBlogAdapter(response.body().getData());
//                    } else {
//                        mViewModel.showBlogFailed(mActivity.getResources().getString(R.string.invalid_response));
//                    }
                    Log.e("TAG", "onResponse: "+response.toString());
                }

                @Override
                public void onFailure(Call<BlogResponseModel> call, Throwable t) {
                    Log.e("TAG", "onFailure: "+t.getMessage());
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void updateDislike(boolean isAdd) {
        String type = "REMOVE";
        if (isAdd) {
            type = "ADD";
        }
        if(mActivity.isInternetConnected()) {
            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().updateDislike(type).enqueue(new Callback<BlogResponseModel>() {
                @Override
                public void onResponse(Call<BlogResponseModel> call, Response<BlogResponseModel> response) {
                    Log.e("TAG", "onResponse: "+response.toString());
                }

                @Override
                public void onFailure(Call<BlogResponseModel> call, Throwable t) {
                    Log.e("TAG", "onFailure: "+t.getMessage());
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void updateShare() {
        if(mActivity.isInternetConnected()) {
            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().updateShare().enqueue(new Callback<BlogResponseModel>() {
                @Override
                public void onResponse(Call<BlogResponseModel> call, Response<BlogResponseModel> response) {
                    Log.e("TAG", "onResponse: "+response.toString());
                }

                @Override
                public void onFailure(Call<BlogResponseModel> call, Throwable t) {
                    Log.e("TAG", "onFailure: "+t.getMessage());
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void updateView() {
        if(mActivity.isInternetConnected()) {
            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().updateView().enqueue(new Callback<BlogResponseModel>() {
                @Override
                public void onResponse(Call<BlogResponseModel> call, Response<BlogResponseModel> response) {
                    Log.e("TAG", "onResponse: "+response.toString());
                }

                @Override
                public void onFailure(Call<BlogResponseModel> call, Throwable t) {
                    Log.e("TAG", "onFailure: "+t.getMessage());
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }


}
