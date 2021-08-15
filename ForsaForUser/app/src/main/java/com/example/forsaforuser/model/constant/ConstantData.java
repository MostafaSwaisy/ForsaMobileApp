package com.example.forsaforuser.model.constant;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ConstantData {
    @SerializedName("skills")
    ArrayList<Data> skills;
    @SerializedName("degrees")
    ArrayList<Data> degrees;
    @SerializedName("universities")
    ArrayList<Data> universities;
    @SerializedName("specialize")
    ArrayList<Data> specialize;

    public ArrayList<Data> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Data> skills) {
        this.skills = skills;
    }

    public ArrayList<Data> getDegrees() {
        return degrees;
    }

    public void setDegrees(ArrayList<Data> degrees) {
        this.degrees = degrees;
    }

    public ArrayList<Data> getUniversities() {
        return universities;
    }

    public void setUniversities(ArrayList<Data> universities) {
        this.universities = universities;
    }

    public ArrayList<Data> getSpecialize() {
        return specialize;
    }

    public void setSpecialize(ArrayList<Data> specialize) {
        this.specialize = specialize;
    }
}
