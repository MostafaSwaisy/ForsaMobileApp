package com.example.forsaforuser.model.company;

import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("data")
    CompanyData data;

    public CompanyData getData() {
        return data;
    }
}
