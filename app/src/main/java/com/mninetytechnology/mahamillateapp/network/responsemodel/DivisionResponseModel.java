package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Division;

import java.util.ArrayList;

public class DivisionResponseModel {
    @Expose
    @SerializedName("Data")
    public ArrayList<Division> data;
    public int count;
    public int code;

    public ArrayList<Division> getData() {
        return data;
    }

    public void setData(ArrayList<Division> data) {
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
