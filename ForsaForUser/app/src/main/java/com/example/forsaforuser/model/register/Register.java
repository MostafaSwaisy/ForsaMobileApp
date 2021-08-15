package com.example.forsaforuser.model.register;

import com.google.gson.annotations.SerializedName;

public class Register {

    @SerializedName("success")
    boolean success;

    @SerializedName("data")
    UserProfile data;

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

    public UserProfile getData() {
        return data;
    }

    public void setData(UserProfile data) {
        this.data = data;
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
