package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;

public class RegisterResponseModel {
    private UserLoginObject Data;
    private int code;
    private String message;

    private String token;

    public UserLoginObject getData() {
        return Data;
    }

    public void setData(UserLoginObject data) {
        Data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
