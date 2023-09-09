package com.mninetytechnology.mahamillateapp.presenter;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.quiz.QuizCertificateActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.quiz.QuizScoreActivity;
import com.mninetytechnology.mahamillateapp.acitivities.ui.user.quiz.QuizShareActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.QuizScoreContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizScore;
import com.mninetytechnology.mahamillateapp.network.responsemodel.QuizScoreResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 19/7/2023.
 */
public class QuizScorePresenter implements QuizScoreContract.Presenter {
    private final QuizScoreActivity mActivity;
    private final QuizScoreContract.ViewModel mViewModel;

    public QuizScorePresenter(QuizScoreActivity mActivity, QuizScoreContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
    }

    @Override
    public void uploadScore(QuizScore score) {
        boolean isPassed = false;
        if (mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);

            String userId = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId();

            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();

            String userScore = String.valueOf(score.getScore());

            if (score.getScore() >= score.getPassing_marks()) {
                isPassed = true;
            } else {
                isPassed = false;
            }
            RetrofitClient.getApiService().updateScore(userId, userScore,isPassed).enqueue(new Callback<QuizScoreResponseModel>() {
                @Override
                public void onResponse(Call<QuizScoreResponseModel> call, Response<QuizScoreResponseModel> response) {
                    mActivity.dismissProgressDialog();
                    if (response.code() == 200 && response.body() != null) {
                        mViewModel.setUpScore();
                    } else {
                        mViewModel.showScoreFailed(mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<QuizScoreResponseModel> call, Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showScoreFailed(mActivity.getResources().getString(R.string.invalid_response));
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }
}
