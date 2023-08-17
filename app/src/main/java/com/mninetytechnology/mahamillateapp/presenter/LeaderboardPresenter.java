package com.mninetytechnology.mahamillateapp.presenter;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.LeaderboardContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Leaderboard;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 19/7/2023.
 */
public class LeaderboardPresenter implements LeaderboardContract.Presenter {
    private final BaseActivity mActivity;
    private final LeaderboardContract.ViewModel mViewModel;

    public LeaderboardPresenter(BaseActivity mActivity, LeaderboardContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
    }

    @Override
    public void getLeaderboards() {
        if(mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);

            String userId = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId();
            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().getLeaderboards(userId).enqueue(new Callback<List<Leaderboard>>() {
                @Override
                public void onResponse(Call<List<Leaderboard>> call, Response<List<Leaderboard>> response) {
                    mActivity.dismissProgressDialog();
                    if (response.code() == 200 && response.body() != null) {
                        mViewModel.setUpLeaderboardAdapter(response.body());
                    } else {
                        mViewModel.showLeaderboardFailed(mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<List<Leaderboard>> call, Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showLeaderboardFailed(mActivity.getResources().getString(R.string.invalid_response));
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }


}
