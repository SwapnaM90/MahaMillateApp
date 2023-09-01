package com.mninetytechnology.mahamillateapp.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.OrganisationSearchActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.blog.BlogActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.BlogContract;
import com.mninetytechnology.mahamillateapp.models.contracts.OrganisationContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Blog;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.network.responsemodel.BlogLikeResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.BlogResponseModel;
import com.google.gson.JsonObject;
import com.mninetytechnology.mahamillateapp.network.responsemodel.LoginResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 19/7/2023.
 */
public class OrganisationPresenter implements OrganisationContract.Presenter {
    private final OrganisationSearchActivity mActivity;
    private final OrganisationContract.ViewModel mViewModel;

    public OrganisationPresenter(OrganisationSearchActivity mActivity, OrganisationContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
    }

    @Override
    public void changeOrganisation(OrganisationLoginObject organisation) {
        mActivity.startProgressDialog(mActivity);
        RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
        String userId = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId();

        RetrofitClient.getApiService().updateOrganisation(userId,organisation.get_id()).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                Log.e("TAG", "onResponse: "+response);
                mActivity.dismissProgressDialog();
                if (response.body() != null && response.body().getData() != null) {
                    UserLoginObject user = response.body().getData();
                    Gson gson = new Gson();
                    String str_user = gson.toJson(user);
                    mActivity.getGlobalHelper().getSharedPreferencesHelper().setPrefLoginUser(str_user);
                    mViewModel.organisationChanged(organisation);
                } else {
                    mViewModel.showOrganisationLoginObjectFailed(mActivity.getString(R.string.invalid_response));
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                mActivity.dismissProgressDialog();
                mViewModel.showOrganisationLoginObjectFailed(t.getMessage());
            }
        });
    }
}
