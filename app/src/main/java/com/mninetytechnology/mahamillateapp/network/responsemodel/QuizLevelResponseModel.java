package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizLevelData;

public class QuizLevelResponseModel {
    @Expose
    @SerializedName("Data")
    public QuizLevelData data;
    public int code;

    public QuizLevelData getData() {
        return data;
    }

    public void setData(QuizLevelData data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
