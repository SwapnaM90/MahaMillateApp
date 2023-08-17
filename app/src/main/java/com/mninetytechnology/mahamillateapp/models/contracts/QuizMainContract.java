package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizLevelData;

/**
 * Created by Swapna Thakur on 19/7/2023.
 * Contract for blog
 */
public interface QuizMainContract {

    interface ViewModel {
        void setQuiz(QuizLevelData quizLevelData);

        void showFailed(String error);
    }

    interface Presenter {
        void getQuizData();

        void goToQuiz();

        void goToLeaderBoard();
    }
}
