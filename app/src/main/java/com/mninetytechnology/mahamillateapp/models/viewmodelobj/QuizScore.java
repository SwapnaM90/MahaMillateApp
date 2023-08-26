package com.mninetytechnology.mahamillateapp.models.viewmodelobj;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class QuizScore implements Parcelable {
    int tot_questions = 0;
    int solved_question = 0;
    int score = 0;
    int correct_questions = 0;
    int incorrect_questions = 0;
    int not_answered_questions = 0;
    int passing_marks = 0;
    int total_marks = 0;

    public QuizScore() {
    }

    protected QuizScore(Parcel in) {
        tot_questions = in.readInt();
        solved_question = in.readInt();
        score = in.readInt();
        correct_questions = in.readInt();
        incorrect_questions = in.readInt();
        not_answered_questions = in.readInt();
        passing_marks = in.readInt();
        total_marks = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

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

    public int getCorrect_questions() {
        return correct_questions;
    }

    public void setCorrect_questions(int correct_questions) {
        this.correct_questions = correct_questions;
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

    public int getPassing_marks() {
        return passing_marks;
    }

    public void setPassing_marks(int passing_marks) {
        this.passing_marks = passing_marks;
    }

    public int getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(int total_marks) {
        this.total_marks = total_marks;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(tot_questions);
        parcel.writeInt(solved_question);
        parcel.writeInt(score);
        parcel.writeInt(correct_questions);
        parcel.writeInt(incorrect_questions);
        parcel.writeInt(not_answered_questions);
        parcel.writeInt(passing_marks);
        parcel.writeInt(total_marks);
    }
}
