package com.example.forsaforuser.model.constant;

import com.google.gson.annotations.SerializedName;

public class Constant {
    @SerializedName("success")
    boolean success;
    @SerializedName("data")
    ConstantData constantData;
    @SerializedName("message")
    String message;
    @SerializedName("status")
    int status;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ConstantData getConstantData() {
        return constantData;
    }

    public void setConstantData(ConstantData constantData) {
        this.constantData = constantData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
