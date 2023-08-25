package com.mninetytechnology.mahamillateapp.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.google.gson.Gson;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.network.responsemodel.LoginResponseModel;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.OrganisationMainActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.profile.AboutUsActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.profile.HelpAndSupportActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.profile.PrivacyPolicyActivity;
import com.mninetytechnology.mahamillateapp.lib.AppKeys;
import com.mninetytechnology.mahamillateapp.models.contracts.ProfileContract;
import com.mninetytechnology.mahamillateapp.network.responsemodel.DistrictTalukaVillageResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OrganisationLoginResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClientLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 3/1/2022.
 */
public class ProfilePresenter implements ProfileContract.Presenter {
    private final BaseActivity mActivity;
    private final ProfileContract.ViewModel mViewModel;

    //Address fields
//    public ObservableField<String> division;
    public ObservableField<String> district;
    public ObservableField<String> taluka;
    public ObservableField<String> village;
    public ObservableField<String> address;

    public ProfilePresenter(MainActivity mActivity, ProfileContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
        initFields();
    }

    public ProfilePresenter(OrganisationMainActivity mActivity, ProfileContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
        initFields();
    }

    private void initFields() {
//        division = new ObservableField<>();
        district = new ObservableField<>();
        taluka = new ObservableField<>();
        village = new ObservableField<>();
        address = new ObservableField<>();
    }

//    @Override
//    public void getDivision() {
//        if(mActivity.isInternetConnected()) {
//            mActivity.startProgressDialog(mActivity);
//            RetrofitClientLogin.getApiService().getDivisions().enqueue(new Callback<DivisionResponseModel>() {
//                @Override
//                public void onResponse(@NonNull Call<DivisionResponseModel> call, @NonNull Response<DivisionResponseModel> response) {
//                    if (response.code() == 200 || response.code() == 201) {
//                        assert response.body() != null;
//                        mActivity.dismissProgressDialog();
//                        mViewModel.setUpDivision(response.body().getData());
//                    } else {
//                        mActivity.dismissProgressDialog();
//                        mViewModel.showProfileFailed(""+mActivity.getResources().getString(R.string.invalid_response));
//                    }
//                }
//                @Override
//                public void onFailure(@NonNull Call<DivisionResponseModel> call, @NonNull Throwable t) {
//                    mActivity.dismissProgressDialog();
//                    mViewModel.showProfileFailed(t.getMessage());
//                }
//            });
//
//        } else {
//            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
//        }
//    }

    @Override
    public void getDistrict() {
        if (mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);
            RetrofitClientLogin.getApiService().getUpdatedDistricts().enqueue(new Callback<DistrictTalukaVillageResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<DistrictTalukaVillageResponseModel> call, @NonNull Response<DistrictTalukaVillageResponseModel> response) {
                    if (response.code() == 200 || response.code() == 201) {
                        assert response.body() != null;
                        mActivity.dismissProgressDialog();
                        mViewModel.setUpDistrict(response.body().getData());
                    } else {
                        mActivity.dismissProgressDialog();
                        mViewModel.showProfileFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DistrictTalukaVillageResponseModel> call, @NonNull Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showProfileFailed(t.getMessage());
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void getTaluka(String district) {
        if (mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);
            RetrofitClientLogin.getApiService().getUpdatedTaluka(district).enqueue(new Callback<DistrictTalukaVillageResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<DistrictTalukaVillageResponseModel> call, @NonNull Response<DistrictTalukaVillageResponseModel> response) {
                    if (response.code() == 200 || response.code() == 201) {
                        assert response.body() != null;
                        mActivity.dismissProgressDialog();
                        mViewModel.setUpTaluka(response.body().getData());
                    } else {
                        mActivity.dismissProgressDialog();
                        mViewModel.showProfileFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DistrictTalukaVillageResponseModel> call, @NonNull Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showProfileFailed(t.getMessage());
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void getVillage(String talukaCode) {
        if (mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);
            RetrofitClientLogin.getApiService().getUpdatedVillage(talukaCode).enqueue(new Callback<DistrictTalukaVillageResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<DistrictTalukaVillageResponseModel> call, @NonNull Response<DistrictTalukaVillageResponseModel> response) {
                    if (response.code() == 200 || response.code() == 201) {
                        assert response.body() != null;
                        mActivity.dismissProgressDialog();
                        mViewModel.setUpVillage(response.body().getData());
                    } else {
                        mActivity.dismissProgressDialog();
                        mViewModel.showProfileFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DistrictTalukaVillageResponseModel> call, @NonNull Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showProfileFailed(t.getMessage());
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void updateAddress() {
        if (mActivity.isInternetConnected()) {
            if (mActivity.getGlobalHelper().getSharedPreferencesHelper().getUser().equalsIgnoreCase(AppKeys.ORGANISATION)) {
              updateOrganisationAddress();
            } else {
                updateUserAddress();
            }

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    /**
     * updates user's address
     */
    private void updateUserAddress() {
        String token = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
        String id = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId();
        RetrofitClient.key = token;
        RetrofitClient.getApiService().updateUserAddress(id,district.get(),taluka.get(),village.get()).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                if (response.code() == 200 || response.code() == 201) {
                    if (response.body() != null && response.body().getCode() == 200) {
                        Log.e("TAG", "onResponse: "+response.body());
                        Gson gson = new Gson();
                        String user = gson.toJson(response.body().getData(), UserLoginObject.class);
                        mActivity.getGlobalHelper().getSharedPreferencesHelper().setPrefLoginUser(user);
                        mActivity.dismissProgressDialog();
                    } else {
                        mActivity.dismissProgressDialog();
                        mViewModel.showProfileFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                    }
                 }  else {
                    mActivity.dismissProgressDialog();
                    mViewModel.showProfileFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                mActivity.dismissProgressDialog();
                mViewModel.showProfileFailed(t.getMessage());
            }
        });
    }

    /**
     * updates organisation's address
     */
    private void updateOrganisationAddress() {
        mActivity.startProgressDialog(mActivity);
        String token = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
        String id = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId();
        RetrofitClient.key = token;
        RetrofitClient.getApiService().updateOrganisationAddress(id,district.get(),taluka.get(),village.get()).enqueue(new Callback<OrganisationLoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<OrganisationLoginResponseModel> call, @NonNull Response<OrganisationLoginResponseModel> response) {
                if (response.code() == 200 || response.code() == 201) {
                    if (response.body() != null && response.body().getCode() == 200) {
                        Log.e("TAG", "onResponse: "+response.body());
                        Gson gson = new Gson();
                        String user = gson.toJson(response.body().getData(), OrganisationLoginObject.class);
                        mActivity.getGlobalHelper().getSharedPreferencesHelper().setPrefLoginUser(user);
                        mActivity.dismissProgressDialog();
                    } else {
                        mActivity.dismissProgressDialog();
                        mViewModel.showProfileFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                    }
                } else {
                    mActivity.dismissProgressDialog();
                    mViewModel.showProfileFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                }
            }

            @Override
            public void onFailure(@NonNull Call<OrganisationLoginResponseModel> call, @NonNull Throwable t) {
                mActivity.dismissProgressDialog();
                mViewModel.showProfileFailed(t.getMessage());
            }
        });
    }

    public void populateAddress() {
        mViewModel.setUpAddress();
    }

    public void goToPrivacyPolicy() {
        mActivity.startActivityOnTop(PrivacyPolicyActivity.class, false);
    }

    public void goToHelpAndSupport() {
        mActivity.startActivityOnTop(HelpAndSupportActivity.class, false);
    }

    public void goToAboutUs() {
        mActivity.startActivityOnTop(AboutUsActivity.class, false);
    }
}
