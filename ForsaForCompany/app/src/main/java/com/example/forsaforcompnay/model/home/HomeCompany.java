package com.example.forsaforcompnay.model.home;

public class HomeCompany {
    private boolean success;
    private DataHomeCompany data;
    private String message;
    private int status;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataHomeCompany getData() {
        return data;
    }

    public void setData(DataHomeCompany data) {
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
