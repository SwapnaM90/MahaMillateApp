package com.mninetytechnology.mahamillateapp.network.responsemodel;

import com.google.gson.annotations.SerializedName;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OtpMsg;

import java.util.ArrayList;

public class OtpResponseModel {
    @SerializedName("ErrorCode")
    private String errorCode;
    @SerializedName("ErrorMessage")
    private String errorMessage;
    @SerializedName("JobId")
    private String jobId;
    @SerializedName("MessageData")
    private ArrayList<OtpMsg> messageData;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public ArrayList<OtpMsg> getMessageData() {
        return messageData;
    }

    public void setMessageData(ArrayList<OtpMsg> messageData) {
        this.messageData = messageData;
    }
}
