package com.example.forsaforcompnay.model.archive;

public class ArchiveCompany {
    private boolean success;
    private DataArchiveCompany data;
    private String message;
    private int status;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataArchiveCompany getData() {
        return data;
    }

    public void setData(DataArchiveCompany data) {
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
