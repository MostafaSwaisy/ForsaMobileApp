package com.example.forsaforuser.model.home;

import com.example.forsaforuser.model.constant.Data;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PostItem {
    private int id, companyId;
    private String companyName;
    private String createAT;
    private String dueDate;
    private String imageCompany;
    private String postText;
    private String postTitle;
    private ArrayList<Data> skills;
    private String status;
    @SerializedName("is_applicant")
    private boolean is_applicant;

    public boolean isIs_applicant() {
        return is_applicant;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreateAT() {
        return createAT;
    }

    public void setCreateAT(String createAT) {
        this.createAT = createAT;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getImageCompany() {
        return imageCompany;
    }

    public void setImageCompany(String imageCompany) {
        this.imageCompany = imageCompany;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Data> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Data> skills) {
        this.skills = skills;
    }
}
