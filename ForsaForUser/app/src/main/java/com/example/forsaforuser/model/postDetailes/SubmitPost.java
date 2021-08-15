package com.example.forsaforuser.model.postDetailes;

import com.google.gson.annotations.SerializedName;

public class SubmitPost {
    @SerializedName("notes")
    private final String notes;
    @SerializedName("post_id")
    private int post_id;

    public SubmitPost(int post_id, String notes) {
        this.post_id = post_id;
        this.notes = notes;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

}
