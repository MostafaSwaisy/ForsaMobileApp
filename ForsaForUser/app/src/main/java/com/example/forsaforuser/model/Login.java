package com.example.forsaforuser.model;

import com.example.forsaforuser.model.profile.UserProfile;
import com.google.gson.annotations.SerializedName;

public class Login {

    String success, message;
    @SerializedName("data")
    UserProfile userProfile;
    int status;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
