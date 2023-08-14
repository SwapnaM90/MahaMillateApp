package com.mninetytechnology.mahamillateapp.presenter;

import android.graphics.Color;
import android.os.Build;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;

import androidx.databinding.ObservableField;

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
    public ObservableField<String> rules;

    public QuizLevelPresenter(QuizLevelsActivity mActivity, QuizLevelContract.ViewModel mViewModel) {
        this.mActivity = mActivity;
        this.mViewModel = mViewModel;
        rules = new ObservableField<>();
    }

    @Override
    public void getLevelData() {
//        "Each question will be allotted 60 seconds.\n" +
//                "There are 100 questions for a total of 100 points.\n" +
//                "1 point for each correct answer.\n" +
//                "A certificate will be awarded if at least 50 questions are answered.\n" +
//                "Final decision will be with the organizers."
        String seconds = "0";
        String tot_questions = "10";

        String user_class = mActivity.getGlobalHelper().getSharedPreferencesHelper().getLoginServerUserClass();
        if (!user_class.trim().isEmpty()) {
            if (user_class.contains("1-4")) {
                seconds = "90";
            } else if (user_class.contains("5-7")) {
                seconds = "80";
            } else if (user_class.contains("8-10")) {
                seconds = "70";
            } else {
                seconds = "60";
            }
        } else {
            seconds = "100";
        }

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(getBulletString("Each question will be allotted "+seconds+" seconds",false));
        builder.append(getBulletString("There are "+tot_questions+" questions for a total of "+tot_questions+" points",true));
        builder.append(getBulletString("1 point for each correct answer.",true));
        builder.append(getBulletString("A certificate will be awarded if at least 50 questions are answered.",true));
        builder.append(getBulletString("Final decision will be with the organizers.",true));

        rules.set(builder.toString());
    }

    /**
     * generates string having bullet points
     * @param normalText
     * @return
     */
    private SpannableString getBulletString(String normalText,boolean nextLine) {
        if (nextLine) {
            SpannableString string = new SpannableString("\n\u2022 "+normalText);
            return string;
        } else {
            SpannableString string = new SpannableString("\u2022 "+normalText);
            return string;
        }
    }

    public void goToNext() {
        mViewModel.goToNext();
    }
}
