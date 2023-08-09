package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;

/**
 * Created by Swapna Thakur on 3/1/2022.
 */
public class LoginResponseModel {
    UserLoginObject Data;
    String Message;
    int code;
    String token;

    public UserLoginObject getData() {
        return Data;
    }

    public void setData(UserLoginObject data) {
        Data = data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
