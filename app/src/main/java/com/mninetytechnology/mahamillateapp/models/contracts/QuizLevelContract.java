package com.mninetytechnology.mahamillateapp.models.contracts;


import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizLevelData;

/**
 * Created by Swapna Thakur on 19/7/2023.
 * Contract for blog
 */
public interface QuizLevelContract {

    interface ViewModel {
        void setUpLevel(QuizLevelData quizLevelDataData);

        void goToNext();
        void showFailed(String error);
    }
    interface Presenter {
        void getLevelData();
    }
}
