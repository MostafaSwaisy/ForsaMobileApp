package com.example.forsaforcompnay.api;

import com.example.forsaforcompnay.model.Login;
import com.example.forsaforcompnay.model.applicant.status.ApplicantStatus;
import com.example.forsaforcompnay.model.archive.ArchiveCompany;
import com.example.forsaforcompnay.model.constant.GetConstants;
import com.example.forsaforcompnay.model.home.HomeCompany;
import com.example.forsaforcompnay.model.post.DeletePost;
import com.example.forsaforcompnay.model.post.Post;
import com.example.forsaforcompnay.model.profile.DataProfile;
import com.example.forsaforcompnay.model.show.applicant.ShowApplicant;
import com.example.forsaforcompnay.model.show.applicants.post.ShowPostApplicant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("company/login")
    Call<Login> LOGIN_CALL(@Body HashMap<String, String> LoginHashMap);

    @GET("company/profile")
    Call<DataProfile> PROFILE_COMPANY_CALL();

    @GET("company/posts")
    Call<HomeCompany> HOME_COMPANY_CALL();

    @GET("company/archive")
    Call<ArchiveCompany> ARCHIVE_COMPANY_CALL();

    @POST("company/applicantStatus")
    Call<ApplicantStatus> APPLICANT_STATUS(@Body HashMap<String, String> ApplicantStatusHashMap);

    @GET("company/applicant/show")
    Call<ShowApplicant> SHOW_APPLICANT_CALL(@Query("applicant_id") int id);

    @GET("getConstant")
    Call<GetConstants> GET_CONSTANTS_CALL();

    @POST("company/createPost")
    Call<Post> POST_CALL(@Body HashMap<String, Object> CreatePostHashMap);

    @POST("company/editPost")
    Call<Post> EDIT_POST_CALL(@Query("post_id") int id ,@Body HashMap<String , Object> EditPostHashMap);

    @POST("company/deletePost")
    Call<DeletePost> DELETE_POST_CALL(@Query("post_id") int id);

    @GET("company/post/applicants")
    Call<ShowPostApplicant> SHOW_POST_APPLICANT_CALL(@Query("post_id") String post_id);

    @POST("company/post/status")
    Call<Post> EDIT_STATUS_POST_CALL(@Body HashMap<String, String> HashMap);

}
