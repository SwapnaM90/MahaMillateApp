package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Blog;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.YoutubeVideo;

import java.util.List;

public class VideoResponseModel {
    @Expose
    @SerializedName("Data")
    List<YoutubeVideo> Data;
    int count;
    int code;

    public List<YoutubeVideo> getData() {
        return Data;
    }

    public void setData(List<YoutubeVideo> data) {
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
