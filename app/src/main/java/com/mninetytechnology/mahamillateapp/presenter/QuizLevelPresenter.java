package com.mninetytechnology.mahamillateapp.presenter;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.ui.quiz.QuizLevelsActivity;
import com.mninetytechnology.mahamillateapp.models.contracts.QuizLevelContract;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizLevelData;
import com.mninetytechnology.mahamillateapp.network.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapna Thakur on 19/7/2023.
 */
public class QuizLevelPresenter implements QuizLevelContract.Presenter {
    private final QuizLevelsActivity mActivity;
    private final QuizLevelContract.ViewModel mViewModel;

    public QuizLevelPresenter(QuizLevelsActivity mActivity, QuizLevelContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
    }

    @Override
    public void getLevelData() {

    }

    public void goToNext() {
        mViewModel.goToNext();
    }
}
