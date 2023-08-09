package com.mninetytechnology.mahamillateapp.presenter;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.LoginActivity;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.RegistrationActivity;
import com.mninetytechnology.mahamillateapp.lib.ScreenHelper;
import com.mninetytechnology.mahamillateapp.models.contracts.LoginContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.network.responsemodel.LoginResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClientLogin;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 3/1/2022.
 */
public class LoginPresenter implements LoginContract.Presenter {
    private final LoginActivity mActivity;
    private final LoginContract.ViewModel mViewModel;
    public ObservableField<String> userId;
    public ObservableField<String> password;
    public View rootView;

    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private static final Pattern PHONE_PATTERN = Pattern
            .compile("[a-zA-Z0-9]{1,250}");


    public LoginPresenter(LoginActivity mActivity, LoginContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
        initFields();
    }

    private void initFields() {
        userId = new ObservableField<>();
        password = new ObservableField<>();
    }

    private boolean validate() {
        if (userId == null || TextUtils.isEmpty(userId.get())) {
            mViewModel.showLoginFailed(mActivity.getResources().getString(R.string.empty_user_id));
            return false;
        } else if (password == null || TextUtils.isEmpty(password.get())) {
            mViewModel.showLoginFailed(mActivity.getResources().getString(R.string.empty_password));
            return false;
        }
        return true;
    }

    /**
     * @date 1-3-2022
     * Checks internet connection if not connected to internet show not connected dialog.
     * if internet connected then it fetches login response from server using retrofit
     */
    @Override
    public void doLogin() {
        if (validate()) {
            ScreenHelper.hideKeyboard(mActivity);
//            UserLoginObject obj = new UserLoginObject();
//            mViewModel.login(obj,"token");
            if(mActivity.isInternetConnected()) {
                mActivity.startProgressDialog(mActivity);

                if (isEmailOrNot()) {

                    RetrofitClientLogin.getApiService().getLoginResponse(userId.get(),"",password.get()).enqueue(new Callback<LoginResponseModel>() {
                        @Override
                        public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                            if (response.code() == 200 || response.code() == 201) {
                                if (response.body() != null) {
                                    UserLoginObject obj = response.body().getData();
                                    mActivity.dismissProgressDialog();
                                    mViewModel.login(obj,response.body().getToken());
                                } else {
                                    mActivity.dismissProgressDialog();
                                    mViewModel.showLoginFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                                }
                            } else {
                                mActivity.dismissProgressDialog();
                                mViewModel.showLoginFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                            mActivity.dismissProgressDialog();
                            mViewModel.showLoginFailed(t.getMessage());
                        }
                    });
                } else {
                    RetrofitClientLogin.getApiService().getLoginResponse("",userId.get(),password.get()).enqueue(new Callback<LoginResponseModel>() {
                        @Override
                        public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                            if (response.code() == 200 || response.code() == 201) {
                                if (response.body() != null) {
                                    UserLoginObject obj = response.body().getData();
                                    mActivity.dismissProgressDialog();
                                    mViewModel.login(obj,response.body().getToken());
                                } else {
                                    mActivity.dismissProgressDialog();
                                    mViewModel.showLoginFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                                }
                            } else {
                                mActivity.dismissProgressDialog();
                                mViewModel.showLoginFailed("" + mActivity.getResources().getString(R.string.invalid_response));
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                            mActivity.dismissProgressDialog();
                            mViewModel.showLoginFailed(t.getMessage());
                        }
                    });
                }

            } else {
                mActivity.showNotInternetConnected(new BaseActivity.OnInternetConnectedListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        }
    }

    private boolean isEmailOrNot() {
        if ((Checkphoneno(userId.get()))) {
            return false;
        } else return CheckEmail(userId.get());
    }

    private boolean CheckEmail(String sEmailId) {

        return EMAIL_PATTERN.matcher(sEmailId).matches();
    }

    private boolean Checkphoneno(String sPhoneNo) {

        return PHONE_PATTERN.matcher(sPhoneNo).matches();
    }

    public void goToRegister() {
        mActivity.startActivityOnTop(RegistrationActivity.class,true);
    }
}
