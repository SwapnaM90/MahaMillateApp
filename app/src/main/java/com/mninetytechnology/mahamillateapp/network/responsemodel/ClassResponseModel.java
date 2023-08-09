package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.mninetytechnology.mahamillateapp.models.viewmodelobj.SingleClass;

import java.util.List;

public class ClassResponseModel {
    private List<SingleClass> Data;

    private int count;
    private int code;

    public List<SingleClass> getData() {
        return Data;
    }

    public void setData(List<SingleClass> data) {
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
