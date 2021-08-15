package com.example.forsaforuser.model.company;

import com.example.forsaforuser.model.profile.ProfileLink;
import com.google.gson.annotations.SerializedName;

public class CompanyData {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("email")
    String email;
    @SerializedName("Logoimage")
    String Logoimage;
    @SerializedName("bannerImage")
    String bannerImage;
    @SerializedName("aboutcompany")
    String aboutcompany;
    @SerializedName("mobileNum")
    String mobileNum;
    @SerializedName("address")
    String address;
    @SerializedName("status")
    String status;
    @SerializedName("socailLinks")
    ProfileLink profileLink;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLogoimage() {
        return Logoimage;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public String getAboutcompany() {
        return aboutcompany;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public ProfileLink getProfileLink() {
        return profileLink;
    }
}
