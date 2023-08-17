package com.mninetytechnology.mahamillateapp.presenter;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.mninetytechnology.mahamillateapp.MainActivity;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.profile.AboutUsActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.profile.HelpAndSupportActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.profile.PrivacyPolicyActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.ProfileContract;
import com.mninetytechnology.mahamillateapp.network.responsemodel.DistrictTalukaVillageResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClientLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 3/1/2022.
 */
public class ProfilePresenter implements ProfileContract.Presenter {
    private final MainActivity mActivity;
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
