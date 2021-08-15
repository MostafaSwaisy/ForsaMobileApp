package com.example.forsaforcompnay.model.profile;

import com.example.forsaforcompnay.model.ProfileLink;

import java.net.URL;

public class ProfileCompany {
    private int id;
    private String name, email, aboutcompany, address, status, mobileNum, access_token;
    private URL Logoimage, bannerImage;

     private ProfileLink socailLinks;

    public ProfileLink getSocailLinks() {
        return socailLinks;
    }

    public void setSocailLinks(ProfileLink socailLinks) {
        this.socailLinks = socailLinks;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutcompany() {
        return aboutcompany;
    }

    public void setAboutcompany(String aboutcompany) {
        this.aboutcompany = aboutcompany;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public URL getLogoimage() {
        return Logoimage;
    }

    public void setLogoimage(URL logoimage) {
        Logoimage = logoimage;
    }

    public URL getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(URL bannerImage) {
        this.bannerImage = bannerImage;
    }

//    public URL getSocailLinks() {
//        return socailLinks;
//    }
//
//    public void setSocailLinks(URL socailLinks) {
//        this.socailLinks = socailLinks;
//    }
}
