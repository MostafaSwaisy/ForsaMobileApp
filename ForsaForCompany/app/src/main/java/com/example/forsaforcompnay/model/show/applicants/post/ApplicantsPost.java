package com.example.forsaforcompnay.model.show.applicants.post;

import com.example.forsaforcompnay.model.ProfileUser;
import com.example.forsaforcompnay.model.constant.Skills;

import java.io.Serializable;
import java.util.ArrayList;

public class ApplicantsPost implements Serializable {
    private int id;
    private String companyName;
    private String date;
    private String postTitle;
    private String applicant_notes;
    private String status;
    private ArrayList<Skills> skills;
    private ProfileUser user;

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

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
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

    public ProfileUser getUser() {
        return user;
    }

    public void setUser(ProfileUser user) {
        this.user = user;
    }
}
