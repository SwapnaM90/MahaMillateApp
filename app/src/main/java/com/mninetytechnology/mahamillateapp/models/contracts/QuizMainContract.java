package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Blog;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizData;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizLevelData;

import java.util.List;

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
