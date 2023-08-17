package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizScore;

/**
 * Created by Swapna Thakur on 19/7/2023.
 * Contract for blog
 */
public interface QuizScoreContract {

    interface ViewModel {
        void setUpScore();

        void showScoreFailed(String error);
    }

    interface Presenter {
        void uploadScore(QuizScore score);
    }
}
