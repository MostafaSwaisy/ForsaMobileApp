package com.example.forsaforuser.model.archive;

import com.example.forsaforuser.model.home.Paginate;
import com.google.gson.annotations.SerializedName;

public class Archive {
    String message;
    int status;
    boolean success;
    @SerializedName("data")
    Data data;
    @SerializedName("paginate")
    Paginate paginate;

    public Paginate getPaginate() {
        return paginate;
    }

    public void setPaginate(Paginate paginate) {
        this.paginate = paginate;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
