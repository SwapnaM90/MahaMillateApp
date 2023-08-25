package com.mninetytechnology.mahamillateapp.presenter;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.UserListContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Leaderboard;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OrganisationUserListResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 19/7/2023.
 */
public class UserListPresenter implements UserListContract.Presenter {
    private final BaseActivity mActivity;
    private final UserListContract.ViewModel mViewModel;

    public UserListPresenter(BaseActivity mActivity, UserListContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
    }

    @Override
    public void getUserList() {
        if (mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);

            String userId = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId();
            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().getOrganisationUserList(userId).enqueue(new Callback<OrganisationUserListResponseModel>() {
                @Override
                public void onResponse(Call<OrganisationUserListResponseModel> call, Response<OrganisationUserListResponseModel> response) {
                    mActivity.dismissProgressDialog();
                    if (response.code() == 200 && response.body() != null) {
                        mViewModel.setUpUserList(response.body().getData());
                    } else {
                        mViewModel.showLeaderboardFailed(mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<OrganisationUserListResponseModel> call, Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showLeaderboardFailed(mActivity.getResources().getString(R.string.invalid_response));
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }


}
