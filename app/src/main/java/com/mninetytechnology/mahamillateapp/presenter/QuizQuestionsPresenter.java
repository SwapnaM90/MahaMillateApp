package com.mninetytechnology.mahamillateapp.presenter;

import android.os.Handler;
import android.view.View;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.quiz.QuizQuestionsActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.QuizQuestionContract;
import com.mninetytechnology.mahamillateapp.network.responsemodel.QuizResponseModel;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 19/7/2023.
 */
public class QuizQuestionsPresenter implements QuizQuestionContract.Presenter {
    private final QuizQuestionsActivity mActivity;
    private final QuizQuestionContract.ViewModel mViewModel;

    public QuizQuestionsPresenter(QuizQuestionsActivity mActivity, QuizQuestionContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
    }

    @Override
    public void loadQuiz() {
        if (mActivity.isInternetConnected()) {
            mActivity.startProgressDialog(mActivity);

            String userId = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserId();
            RetrofitClient.key = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginKey();
            RetrofitClient.getApiService().getAllQuiz(userId).enqueue(new Callback<QuizResponseModel>() {
                @Override
                public void onResponse(Call<QuizResponseModel> call, Response<QuizResponseModel> response) {
                    mActivity.dismissProgressDialog();
                    if (response.code() == 200 && response.body() != null) {
                        mViewModel.setQuestions(response.body().getData().getQuestions());
                    } else {
                        mViewModel.showFailed(mActivity.getResources().getString(R.string.invalid_response));
                    }
                }

                @Override
                public void onFailure(Call<QuizResponseModel> call, Throwable t) {
                    mActivity.dismissProgressDialog();
                    mViewModel.showFailed(mActivity.getResources().getString(R.string.invalid_response));
                }
            });

        } else {
            mActivity.showNotInternetConnected((dialog, which) -> dialog.dismiss());
        }
    }

    @Override
    public void buttonClick(View view, int button) {
        switch (button) {
            case 1:
                mViewModel.checkAnswer("A", view);
                break;
            case 2:
                mViewModel.checkAnswer("B", view);
                break;
            case 3:
                mViewModel.checkAnswer("C", view);
                break;
            case 4:
                mViewModel.checkAnswer("D", view);
                break;
        }
    }

    @Override
    public void goToNext() {
        mActivity.countDown.cancel();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewModel.changeQuestion();
            }
        }, 500);
    }
}
