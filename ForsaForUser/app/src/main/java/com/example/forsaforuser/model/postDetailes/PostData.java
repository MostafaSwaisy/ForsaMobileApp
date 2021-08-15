package com.example.forsaforuser.model.postDetailes;

import com.example.forsaforuser.model.home.PostItem;
import com.google.gson.annotations.SerializedName;

public class PostData {
    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private PostItem post;
    @SerializedName("message")
    private String message;
    private int status;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public PostItem getPost() {
        return post;
    }

    public void setPost(PostItem post) {
        this.post = post;
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
