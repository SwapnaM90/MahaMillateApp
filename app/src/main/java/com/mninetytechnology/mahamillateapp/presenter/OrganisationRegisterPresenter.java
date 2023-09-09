package com.mninetytechnology.mahamillateapp.presenter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.OrganisationRegistrationActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.OrganisationRegisterContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.network.responsemodel.ClassResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.DistrictTalukaVillageResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OrganisationLoginResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClientLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 3/1/2022.
 */
public class OrganisationRegisterPresenter implements OrganisationRegisterContract.Presenter {
    private final OrganisationRegistrationActivity mActivity;
    private final OrganisationRegisterContract.ViewModel mViewModel;
    public ObservableField<String> phone_number;
    public ObservableField<String> email;
    public ObservableField<String> name;
    public ObservableField<String> password;
    public ObservableField<String> re_enter_password;
    public ObservableField<String> referal_code;
    public ObservableField<String> address;

    //Address fields
//    public ObservableField<String> division;
    public ObservableField<String> district;
    public ObservableField<String> taluka;
    public ObservableField<String> village;

    public OrganisationRegisterPresenter(OrganisationRegistrationActivity mActivity, OrganisationRegisterContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
        initFields();
    }

    private void initFields() {
        email = new ObservableField<>();
        name = new ObservableField<>();
        password = new ObservableField<>();
        re_enter_password = new ObservableField<>();
        address = new ObservableField<>();
        phone_number = new ObservableField<>();
        referal_code = new ObservableField<>();
        district = new ObservableField<>();
        taluka = new ObservableField<>();
        village = new ObservableField<>();
    }

    private boolean validate() {
        if (email == null || TextUtils.isEmpty(email.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_email));
            return false;
        } else if (name == null || TextUtils.isEmpty(name.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        } else if (password == null || TextUtils.isEmpty(password.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_password));
            return false;
        } else if (re_enter_password == null || TextUtils.isEmpty(re_enter_password.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_password));
            return false;
        } else if (!password.get().trim().equalsIgnoreCase(re_enter_password.get().trim())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.password_not_match));
            return false;
        } else if (address == null || TextUtils.isEmpty(address.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        } else if (referal_code == null || TextUtils.isEmpty(referal_code.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        } else if (district == null || TextUtils.isEmpty(district.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        } else if (taluka == null || TextUtils.isEmpty(taluka.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        } else if (village == null || TextUtils.isEmpty(village.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        }

        return true;
    }

    @Override
    public void doRegistration() {
        if (validate()) {
            if (mActivity.isInternetConnected()) {
                mActivity.startProgressDialog(mActivity);

                RetrofitClientLogin.getApiService().registerOrganisation(name.get(),referal_code.get(),name.get(),email.get(), password.get(), phone_number.get(),"Maharashtra", district.get(), district.get(), taluka.get(), village.get()).enqueue(new Callback<OrganisationLoginResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<OrganisationLoginResponseModel> call, @NonNull Response<OrganisationLoginResponseModel> response) {
                        if (response.code() == 200 || response.code() == 201) {
                            assert response.body() != null;
                            OrganisationLoginObject obj = response.body().getData();
                            mActivity.dismissProgressDialog();
                            mViewModel.register(obj, response.body().getToken());
                        } else {
                            mActivity.dismissProgressDialog();
                            mViewModel.showRegisterFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<OrganisationLoginResponseModel> call, @NonNull Throwable t) {
                        mActivity.dismissProgressDialog();
                        mViewModel.showRegisterFailed(t.getMessage());
                    }
                });

            } else {
                mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
            }
        }
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
//                        mViewModel.showRegisterFailed(""+mActivity.getResources().getString(R.string.invalid_response));
//                    }
//                }
//                @Override
//                public void onFailure(@NonNull Call<DivisionResponseModel> call, @NonNull Throwable t) {
//                    mActivity.dismissProgressDialog();
//                    mViewModel.showRegisterFailed(t.getMessage());
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
                        mViewModel.showRegisterFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DistrictTalukaVillageResponseModel> call, @NonNull Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showRegisterFailed(t.getMessage());
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void getTaluka(String districtCode) {
        if (mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);
            RetrofitClientLogin.getApiService().getUpdatedTaluka(districtCode).enqueue(new Callback<DistrictTalukaVillageResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<DistrictTalukaVillageResponseModel> call, @NonNull Response<DistrictTalukaVillageResponseModel> response) {
                    if (response.code() == 200 || response.code() == 201) {
                        assert response.body() != null;
                        mActivity.dismissProgressDialog();
                        mViewModel.setUpTaluka(response.body().getData());
                    } else {
                        mActivity.dismissProgressDialog();
                        mViewModel.showRegisterFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DistrictTalukaVillageResponseModel> call, @NonNull Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showRegisterFailed(t.getMessage());
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
                        mViewModel.showRegisterFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DistrictTalukaVillageResponseModel> call, @NonNull Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showRegisterFailed(t.getMessage());
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    public void populateAddress() {
        mViewModel.setUpAddress();
    }
}
