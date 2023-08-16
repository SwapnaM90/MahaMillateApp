package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Blog;

import java.util.List;

public class BlogLikeResponseModel {

    Blog Data;
    int count;
    int code;

    public Blog getData() {
        return Data;
    }

    public void setData(Blog data) {
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

    @Override
    public String toString() {
        return "BlogResponseModel{" +
                "Data=" + Data +
                ", count=" + count +
                ", code=" + code +
                '}';
    }
}
