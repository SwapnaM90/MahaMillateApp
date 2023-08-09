package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizData;

import java.util.ArrayList;

public class QuizResponseModel {
    public ArrayList<QuizData> Data;
    public int count;
    public int code;

    public ArrayList<QuizData> getData() {
        return Data;
    }

    public void setData(ArrayList<QuizData> data) {
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
