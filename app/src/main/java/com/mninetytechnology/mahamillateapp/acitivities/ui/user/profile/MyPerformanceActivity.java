package com.mninetytechnology.mahamillateapp.acitivities.ui.user.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.google.gson.Gson;
import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.MainActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityMyPerformanceBinding;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.network.responsemodel.LoginResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPerformanceActivity extends BaseActivity {
    private ActivityMyPerformanceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_performance);
        getUserData();
    }

    private void getUserData() {
        if (isInternetConnected()) {
            startProgressDialog(mActivity);

            String userId = getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId();
            RetrofitClient.key = getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().getAllQuizData(userId).enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    dismissProgressDialog();
                    if (response.code() == 200) {
                        if (response.body() != null && response.body().getData() != null) {
                            Gson gson = new Gson();
                            String str_user = gson.toJson(response.body().getData());
                            getGlobalHelper().getSharedPreferencesHelper().setPrefLoginUser(str_user);
                            UserLoginObject user = response.body().getData();
                            binding.setUser(user);
                            double tot_second_level = user.getSecondLevel() + user.getReferral_points() + user.getOrganization_points();
                            double tot_third_level = user.getThirdLevel() + user.getReferral_earn_points();
                            binding.tvSecondLevelTotal.setText(String.valueOf(tot_second_level));
                            binding.tvThirdLevelTotal.setText(String.valueOf(tot_third_level));
                        } else {
                            showErrorSnackBar(binding.getRoot(),response.body().getMessage());
                        }
                    } else {
                        showErrorSnackBar(binding.getRoot(),getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                    dismissProgressDialog();
                    showErrorSnackBar(binding.getRoot(),getResources().getString(R.string.invalid_response));
                }
            });

        } else {
            showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void onBackPressed() {
        startActivityOnTop(MainActivity.class,true);
    }
}