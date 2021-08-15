package com.example.forsaforcompnay.model.constant;

import java.io.Serializable;

public class Skills implements Serializable {
    private int id;
    private String name;
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
