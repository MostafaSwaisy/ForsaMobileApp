package com.example.forsaforcompnay.model.show.applicants.post;

import com.example.forsaforcompnay.model.constant.Skills;

import java.io.Serializable;
import java.util.ArrayList;

public class DataApplicantsPost implements Serializable {
    ArrayList<ApplicantsPost> applicants;
    private int id;
    private String createAT;
    private String dueDate;
    private String postTitle;
    private String postText;
    private String status;
    private ArrayList<Skills> skills;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
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

    public ArrayList<Skills> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skills> skills) {
        this.skills = skills;
    }

    public ArrayList<ApplicantsPost> getApplicants() {
        return applicants;
    }

    public void setApplicants(ArrayList<ApplicantsPost> applicants) {
        this.applicants = applicants;
    }
}
