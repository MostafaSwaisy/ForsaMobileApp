package com.example.forsaforcompnay.model.archive;

import com.example.forsaforcompnay.model.ProfileUser;
import com.example.forsaforcompnay.model.constant.Skills;

import java.util.ArrayList;

public class ItemDataArchiveCompany {
    private int id;
    private String companyName;
    private String date;
    private String postTitle, applicant_notes;
    private String status;
    private ArrayList<Skills> skills;
    private ProfileUser user;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProfileUser getUser() {
        return user;
    }

    public void setUser(ProfileUser user) {
        this.user = user;
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

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postText) {
        this.postTitle = postText;
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

    public ArrayList<Skills> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skills> skills) {
        this.skills = skills;
    }
}

