package com.example.forsaforcompnay.model.show.applicant;

public class ShowApplicant {
    private boolean success;
    private String message;
    private int status;
    private DataShowApplicant data;

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

    public DataShowApplicant getData() {
        return data;
    }

    public void setData(DataShowApplicant data) {
        this.data = data;
    }
}
