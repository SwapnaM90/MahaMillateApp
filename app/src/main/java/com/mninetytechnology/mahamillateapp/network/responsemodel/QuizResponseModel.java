package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizData;

import java.util.ArrayList;

public class QuizResponseModel {
    @Expose
    @SerializedName("Data")
    public QuizData Data;
    public int count;
    public int code;

    public QuizData getData() {
        return Data;
    }

    public void setData(QuizData data) {
        Data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
