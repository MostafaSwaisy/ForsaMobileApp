package com.example.forsaforcompnay.model;

import com.example.forsaforcompnay.model.profile.ProfileCompany;
import com.google.gson.annotations.SerializedName;

public class Login {
    private boolean success;
    @SerializedName("data")
    private ProfileCompany profileCompany;
    private String message;
    private int status;


    public boolean isSuccess() {
        return success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ProfileCompany getProfileCompany() {
        return profileCompany;
    }

    public void setProfileCompany(ProfileCompany profileCompany) {
        this.profileCompany = profileCompany;
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
