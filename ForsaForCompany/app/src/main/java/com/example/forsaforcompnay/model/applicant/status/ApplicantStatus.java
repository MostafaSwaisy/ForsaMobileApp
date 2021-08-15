package com.example.forsaforcompnay.model.applicant.status;

import java.io.Serializable;

public class ApplicantStatus implements Serializable {
    private boolean success;
    private String message;
    private int status;
    private DataApplicantStatus data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public DataApplicantStatus getData() {
        return data;
    }

    public void setData(DataApplicantStatus data) {
        this.data = data;
    }
}
