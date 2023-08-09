package com.mninetytechnology.mahamillateapp.presenter;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.quiz.LeaderboardActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.quiz.QuizLevelsActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.quiz.QuizMainActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.QuizMainContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizLevelData;
import com.mninetytechnology.mahamillateapp.network.responsemodel.QuizResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 19/7/2023.
 */
public class QuizMainPresenter implements QuizMainContract.Presenter {
    private final QuizMainActivity mActivity;
    private final QuizMainContract.ViewModel mViewModel;

    public QuizMainPresenter(QuizMainActivity mActivity, QuizMainContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
    }

    @Override
    public void getQuizData() {
        if(mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);

            String userId = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId();
            String score = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLastFirstLevelScore();

            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().getAllQuizData(userId,score).enqueue(new Callback<QuizLevelData>() {
                @Override
                public void onResponse(Call<QuizLevelData> call, Response<QuizLevelData> response) {
                    mActivity.dismissProgressDialog();
                    if (response.code() == 200 && response.body() != null) {
                        mViewModel.setQuiz(response.body());
                    } else {
                        mViewModel.showFailed(mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<QuizLevelData> call, Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showFailed(mActivity.getResources().getString(R.string.invalid_response));
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void goToQuiz() {
        mActivity.startActivityOnTop(QuizLevelsActivity.class,true);
    }

    @Override
    public void goToLeaderBoard() {
        mActivity.startActivityOnTop(LeaderboardActivity.class,true);
    }
}
