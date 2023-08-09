package com.mninetytechnology.mahamillateapp.models.viewmodelobj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Options {
    @Expose
    @SerializedName("B")
    private String b;

    @Expose
    @SerializedName("C")
    private String c;

    @Expose
    @SerializedName("D")
    private String d;

    @Expose
    @SerializedName("A")
    private String a;

    private boolean isImage = false;

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }
}
