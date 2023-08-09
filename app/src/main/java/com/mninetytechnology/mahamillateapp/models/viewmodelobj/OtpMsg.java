package com.mninetytechnology.mahamillateapp.models.viewmodelobj;

import com.google.gson.annotations.SerializedName;

public class OtpMsg {
    @SerializedName("Number")
    private String number;
    @SerializedName("MessageId")
    private String messageId;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
