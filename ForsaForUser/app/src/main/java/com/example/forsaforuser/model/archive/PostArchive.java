package com.example.forsaforuser.model.archive;

import com.example.forsaforuser.model.constant.Data;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PostArchive {
    int id;
    String companyName, date, applicant_notes, status;
    @SerializedName("postTitle")
    String postText;
    ArrayList<Data> skills;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getApplicant_notes() {
        return applicant_notes;
    }

    public void setApplicant_notes(String applicant_notes) {
        this.applicant_notes = applicant_notes;
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
