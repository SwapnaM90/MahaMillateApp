package com.mninetytechnology.mahamillateapp.presenter;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.videos.VideosActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.YoutubeVideoContract;
import com.mninetytechnology.mahamillateapp.network.responsemodel.VideoResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 19/7/2023.
 */
public class YoutubeVideoPresenter implements YoutubeVideoContract.Presenter {
    private final VideosActivity mActivity;
    private final YoutubeVideoContract.ViewModel mViewModel;

    public YoutubeVideoPresenter(VideosActivity mActivity, YoutubeVideoContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
    }

    @Override
    public void getYoutubeVideos() {
        if(mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);

            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().getVideos().enqueue(new Callback<VideoResponseModel>() {
                @Override
                public void onResponse(Call<VideoResponseModel> call, Response<VideoResponseModel> response) {
                    mActivity.dismissProgressDialog();
                    if (response.code() == 200 && response.body() != null) {
                        mViewModel.setUpYoutubeVideoAdapter(response.body().getData());
                    } else {
                        mViewModel.showYoutubeVideoFailed(mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<VideoResponseModel> call, Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showYoutubeVideoFailed(mActivity.getResources().getString(R.string.invalid_response));
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }
}
