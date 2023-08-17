package com.mninetytechnology.mahamillateapp.models.contracts;


import android.view.View;

import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Questions;

import java.util.List;

/**
 * Created by Swapna Thakur on 19/7/2023.
 * Contract for blog
 */
public interface QuizQuestionContract {

    interface ViewModel {
        void changeQuestion();

        void checkAnswer(String selectedOption, View view);

        void setQuestions(List<Questions> questions);

        void showFailed(String error);
    }

    interface Presenter {
        void loadQuiz();

        void buttonClick(View view, int button);

        void goToNext();
    }
}
