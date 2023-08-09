package com.mninetytechnology.mahamillateapp.presenter;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableField;
import androidx.databinding.adapters.AdapterViewBindingAdapter;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.RegistrationActivity;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.lib.ScreenHelper;
import com.mninetytechnology.mahamillateapp.models.contracts.RegisterContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.network.responsemodel.ClassResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.RegisterResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClientLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 3/1/2022.
 */
public class RegisterPresenter implements RegisterContract.Presenter {
    private final RegistrationActivity mActivity;
    private final RegisterContract.ViewModel mViewModel;
    public ObservableField<String> email;
    public ObservableField<String> phone_number;
    public ObservableField<String> name;
    public ObservableField<String> password;
    public ObservableField<String> re_enter_password;
    public ObservableField<String> state;
    public ObservableField<String> district;
    public ObservableField<String> taluka;
    public ObservableField<String> village;
    public ObservableField<String> zip_code;
    public ObservableField<String> user_class;

    public RegisterPresenter(RegistrationActivity mActivity, RegisterContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
        initFields();
    }

    private void initFields() {
        email = new ObservableField<>();
        name = new ObservableField<>();
        password = new ObservableField<>();
        re_enter_password = new ObservableField<>();
        state = new ObservableField<>();
        district = new ObservableField<>();
        taluka = new ObservableField<>();
        village = new ObservableField<>();
        user_class = new ObservableField<>();
        zip_code = new ObservableField<>();
        phone_number = new ObservableField<>();
    }

    private boolean validate() {
        if (email == null || TextUtils.isEmpty(email.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_email));
            return false;
        }else if (phone_number == null || TextUtils.isEmpty(phone_number.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_phone_number));
            return false;
        }else if (phone_number.get().length() < 10) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.enter_valid_phone));
            return false;
        }else if (name == null || TextUtils.isEmpty(name.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        }else if (password == null || TextUtils.isEmpty(password.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_password));
            return false;
        }else if (re_enter_password == null || TextUtils.isEmpty(re_enter_password.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_password));
            return false;
        }else if (!password.get().trim().equalsIgnoreCase(re_enter_password.get().trim())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.password_not_match));
            return false;
        }/*else if (state == null || TextUtils.isEmpty(state.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        }else if (district == null || TextUtils.isEmpty(district.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        }else if (taluka == null || TextUtils.isEmpty(taluka.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        }else if (village == null || TextUtils.isEmpty(village.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        }*/else if (zip_code == null || TextUtils.isEmpty(zip_code.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        }else if (user_class == null || TextUtils.isEmpty(user_class.get())) {
            mViewModel.showRegisterFailed(mActivity.getResources().getString(R.string.empty_field));
            return false;
        }

        return true;
    }

    @Override
    public void doRegistration() {
        if(validate()) {
            if(mActivity.isInternetConnected()) {
                mActivity.startProgressDialog(mActivity);

                RetrofitClientLogin.getApiService().registerUser(name.get(), email.get(),phone_number.get(),password.get(),state.get(),district.get(),village.get(),village.get(),zip_code.get(), user_class.get()).enqueue(new Callback<RegisterResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<RegisterResponseModel> call, @NonNull Response<RegisterResponseModel> response) {
                        if (response.code() == 200 || response.code() == 201) {
                            assert response.body() != null;
                            UserLoginObject obj = response.body().getData();
                            mActivity.dismissProgressDialog();
                            mViewModel.register(obj,response.body().getToken());
                        } else {
                            mActivity.dismissProgressDialog();
                            mViewModel.showRegisterFailed(""+mActivity.getResources().getString(R.string.invalid_response));
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<RegisterResponseModel> call, @NonNull Throwable t) {
                        mActivity.dismissProgressDialog();
                        mViewModel.showRegisterFailed(t.getMessage());
                    }
                });

            } else {
                mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
            }
        }
    }

    @Override
    public void getClassData() {
        if(mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);

            RetrofitClientLogin.getApiService().getClassData().enqueue(new Callback<ClassResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<ClassResponseModel> call, @NonNull Response<ClassResponseModel> response) {
                    if (response.code() == 200 || response.code() == 201) {
                        assert response.body() != null;
                        mActivity.dismissProgressDialog();
                        mViewModel.setUpClass(response.body().getData());
                    } else {
                        mActivity.dismissProgressDialog();
                        mViewModel.showRegisterFailed(""+mActivity.getResources().getString(R.string.invalid_response));
                    }
                }
                @Override
                public void onFailure(@NonNull Call<ClassResponseModel> call, @NonNull Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showRegisterFailed(t.getMessage());
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    /**
     * @date 2-8-2023
     * get state, district, taluka and village using zip code
//     * @param zip_code
     */
//    public void getPostalDetails(String zip_code) {
//        if(validate()) {
//            if(mActivity.isInternetConnected()) {
//                mActivity.startProgressDialog(mActivity);
//
//                RetrofitClientLogin.getApiService().registerUser(name.get(), email.get(),phone_number.get(),password.get(),zip_code.get(), user_class.get()).enqueue(new Callback<RegisterResponseModel>() {
//                    @Override
//                    public void onResponse(@NonNull Call<RegisterResponseModel> call, @NonNull Response<RegisterResponseModel> response) {
//                        if (response.code() == 200 || response.code() == 201) {
//                            obj = response.body().getData();
//                            mActivity.dismissProgressDialog();
//                            mViewModel.register(obj);
//                        } else {
//                            mActivity.dismissProgressDialog();
//                            mViewModel.showRegisterFailed(""+mActivity.getResources().getString(R.string.invalid_response));
//                        }
//                    }
//                    @Override
//                    public void onFailure(@NonNull Call<RegisterResponseModel> call, @NonNull Throwable t) {
//                        mActivity.dismissProgressDialog();
//                        mViewModel.showRegisterFailed(t.getMessage());
//                    }
//                });
//
//            } else {
//                mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
//            }
//        }
//    }

    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        user_class.set((String) parent.getSelectedItem());
    }
}
