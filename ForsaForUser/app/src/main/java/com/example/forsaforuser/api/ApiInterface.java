package com.example.forsaforuser.api;

import com.example.forsaforuser.model.Login;
import com.example.forsaforuser.model.archive.Archive;
import com.example.forsaforuser.model.company.Company;
import com.example.forsaforuser.model.constant.Constant;
import com.example.forsaforuser.model.home.Home;
import com.example.forsaforuser.model.postDetailes.Applicant;
import com.example.forsaforuser.model.postDetailes.PostData;
import com.example.forsaforuser.model.postDetailes.SubmitPost;
import com.example.forsaforuser.model.profile.Profile;
import com.example.forsaforuser.model.profile.UserProfile;
import com.example.forsaforuser.model.register.Register;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("user/login")
    Call<Login> LOGIN_CALL(@Body HashMap<String, String> LoginHashMap);

    @GET("user/posts")
    Call<Home> homescreen();

    @GET("user/posts")
    Call<Home> homePagination(@Query("page") int page);

    @GET("user/archive")
    Call<Archive> archiveScreen();

    @GET("user/profile")
    Call<Profile> profile();

    @POST("userRegister")
    Call<Register> register(@Body HashMap<String, String> RegisterHashMap);

    @GET("getConstant")
    Call<Constant> getConstant();

    @POST("user/updateProfile")
    Call<UserProfile> updateProfile(@Body HashMap<String, Object> updateProfileHashMap);

    @GET("user/postDetails")
    Call<PostData> getPostDtailes(@Query("post_id") String id);

    @POST("user/applicant")
    Call<Applicant> submitPost(@Body SubmitPost submitPost);

    @GET("user/showCompany")
    Call<Company> showCompany(@Query("company_id") int company_id);
}
