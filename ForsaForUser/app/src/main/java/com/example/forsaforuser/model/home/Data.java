package com.example.forsaforuser.model.home;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {
    @SerializedName("items")
    ArrayList<PostItem> posts;

    public ArrayList<PostItem> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostItem> posts) {
        this.posts = posts;
    }
}
