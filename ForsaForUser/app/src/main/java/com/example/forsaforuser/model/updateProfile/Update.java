package com.example.forsaforuser.model.updateProfile;

import java.util.ArrayList;

public class Update {
    String name, email, password, avarage;
    int specialize_id, mobile_number, degree_id, university_id;
    ArrayList<Integer> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvarage() {
        return avarage;
    }

    public void setAvarage(String avarage) {
        this.avarage = avarage;
    }

    public int getSpecialize_id() {
        return specialize_id;
    }

    public void setSpecialize_id(int specialize_id) {
        this.specialize_id = specialize_id;
    }

    public int getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(int mobile_number) {
        this.mobile_number = mobile_number;
    }

    public int getDegree_id() {
        return degree_id;
    }

    public void setDegree_id(int degree_id) {
        this.degree_id = degree_id;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public ArrayList<Integer> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Integer> skills) {
        this.skills = skills;
    }
}
