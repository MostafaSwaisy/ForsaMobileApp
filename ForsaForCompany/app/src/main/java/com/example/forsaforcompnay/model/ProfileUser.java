package com.example.forsaforcompnay.model;

import com.example.forsaforcompnay.model.constant.Skills;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class ProfileUser implements Serializable {
    private int id;
    private String username;
    private String email;
    private String degree;
    private String Specialization;
    private String uneversity;
    private String avarege;
    private String mobileNum;
    private URL image;
    private ArrayList<Skills> skills;
    private ProfileLink profileLink;

    public ProfileLink getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(ProfileLink profileLink) {
        this.profileLink = profileLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public String getUneversity() {
        return uneversity;
    }

    public void setUneversity(String uneversity) {
        this.uneversity = uneversity;
    }

    public String getAvarege() {
        return avarege;
    }

    public void setAvarege(String avarege) {
        this.avarege = avarege;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public ArrayList<Skills> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skills> skills) {
        this.skills = skills;
    }
}
