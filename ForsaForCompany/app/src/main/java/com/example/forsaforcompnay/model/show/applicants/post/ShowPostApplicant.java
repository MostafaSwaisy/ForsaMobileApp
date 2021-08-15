package com.example.forsaforcompnay.model.show.applicants.post;

import java.io.Serializable;

public class ShowPostApplicant {

    private boolean success;
    private DataApplicantsPost data;
    private String message;
    private int status;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataApplicantsPost getData() {
        return data;
    }

    public void setData(DataApplicantsPost data) {
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
