package com.example.forsaforcompnay.model.constant;

public class GetConstants {
    private boolean success;
    private String message;
    private int status;
    private DataConstant data;

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

    public DataConstant getData() {
        return data;
    }

    public void setData(DataConstant data) {
        this.data = data;
    }
}
