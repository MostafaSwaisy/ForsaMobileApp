package com.example.forsaforuser.model.constant;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    //To check if Skill click or not
    private int isSelected = 1;

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
