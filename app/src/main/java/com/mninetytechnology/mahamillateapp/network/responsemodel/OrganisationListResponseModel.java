package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.google.gson.annotations.SerializedName;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;

import java.util.ArrayList;

public class OrganisationListResponseModel {
    @SerializedName("Data")
    private ArrayList<OrganisationLoginObject> data;
    private int count;
    private int code;

    public ArrayList<OrganisationLoginObject> getData() {
        return data;
    }

    public void setData(ArrayList<OrganisationLoginObject> data) {
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
