package com.example.forsaforuser.model.register;

import java.util.List;

public class UserProfile {
    int id;
    String username, email, degree, Specialization, uneversity, avarege, mobileNum, image, access_token;
    List skills;
    ProfileLink profileLink;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List getSkills() {
        return skills;
    }

    public void setSkills(List skills) {
        this.skills = skills;
    }

    public ProfileLink getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(ProfileLink profileLink) {
        this.profileLink = profileLink;
    }
}
