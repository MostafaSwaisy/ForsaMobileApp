package com.example.forsaforuser.model.archive;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {
    @SerializedName("items")
    ArrayList<PostArchive> posts;

    public ArrayList<PostArchive> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostArchive> posts) {
        this.posts = posts;
    }
}
