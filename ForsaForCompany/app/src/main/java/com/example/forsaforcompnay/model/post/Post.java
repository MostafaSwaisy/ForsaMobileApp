package com.example.forsaforcompnay.model.post;

public class Post {
    private boolean success;
    private String message;
    private int status;
    private DataPost data;

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

    public DataPost getData() {
        return data;
    }

    public void setData(DataPost data) {
        this.data = data;
    }
}
