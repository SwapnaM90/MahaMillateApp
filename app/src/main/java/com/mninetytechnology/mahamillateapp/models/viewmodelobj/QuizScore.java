package com.mninetytechnology.mahamillateapp.models.viewmodelobj;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class QuizScore implements Parcelable {
    int tot_questions = 0;
    int solved_question = 0;
    int score = 0;
    int incorrect_questions = 0;
    int not_answered_questions = 0;

    public QuizScore() {
    }

    protected QuizScore(Parcel in) {
        tot_questions = in.readInt();
        solved_question = in.readInt();
        score = in.readInt();
        incorrect_questions = in.readInt();
        not_answered_questions = in.readInt();
    }

    public static final Creator<QuizScore> CREATOR = new Creator<QuizScore>() {
        @Override
        public QuizScore createFromParcel(Parcel in) {
            return new QuizScore(in);
        }

        @Override
        public QuizScore[] newArray(int size) {
            return new QuizScore[size];
        }
    };

    public int getTot_questions() {
        return tot_questions;
    }

    public void setTot_questions(int tot_questions) {
        this.tot_questions = tot_questions;
    }

    public int getSolved_question() {
        return solved_question;
    }

    public void setSolved_question(int solved_question) {
        this.solved_question = solved_question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIncorrect_questions() {
        return incorrect_questions;
    }

    public void setIncorrect_questions(int incorrect_questions) {
        this.incorrect_questions = incorrect_questions;
    }

    public int getNot_answered_questions() {
        return not_answered_questions;
    }

    public void setNot_answered_questions(int not_answered_questions) {
        this.not_answered_questions = not_answered_questions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(tot_questions);
        parcel.writeInt(solved_question);
        parcel.writeInt(score);
        parcel.writeInt(incorrect_questions);
        parcel.writeInt(not_answered_questions);
    }
}
