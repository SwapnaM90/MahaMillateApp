package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.SingleClass;

import java.util.ArrayList;

public class ClassResponseModel {
    @Expose
    @SerializedName("Data")
    public ArrayList<SingleClass> data;
    public int count;
    public int code;

    public ArrayList<SingleClass> getData() {
        return data;
    }

    public void setData(ArrayList<SingleClass> data) {
        this.data = data;
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
