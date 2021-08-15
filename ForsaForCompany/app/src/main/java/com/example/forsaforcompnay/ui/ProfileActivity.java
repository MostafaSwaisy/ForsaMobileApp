package com.example.forsaforcompnay.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.api.Api;
import com.example.forsaforcompnay.helper.Helper;
import com.example.forsaforcompnay.model.profile.DataProfile;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private static final String LOG = "MyProfileActivity";
    ImageView image_cover, logoImage;
    LoaderTextView nameCompany, aboutUs, emailCompany, mobileCompany, addressCompany, linkCompany;
    RelativeLayout errorMsg , profileLayoutError;
    TextView btnFaceBook, btnTwitter , btnLinkedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        getProfileCompany();
    }

    private void init() {
        nameCompany = findViewById(R.id.nameCompany);
        aboutUs = findViewById(R.id.aboutUs);
        emailCompany = findViewById(R.id.emailCompany);
        mobileCompany = findViewById(R.id.mobileCompany);
        addressCompany = findViewById(R.id.addressCompany);
        linkCompany = findViewById(R.id.linkCompany);
        image_cover = findViewById(R.id.image_cover);
        logoImage = findViewById(R.id.logoImage);
        errorMsg = findViewById(R.id.errorMsg);
        profileLayoutError = findViewById(R.id.ProfileLayoutError);
        btnFaceBook = findViewById(R.id.btnFaceBook);
        btnTwitter = findViewById(R.id.btnTwitter);
        btnLinkedIn = findViewById(R.id.btnLinkedIn);

    }

    private void getProfileCompany() {
        Call<DataProfile> dataProfileCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().PROFILE_COMPANY_CALL();
        dataProfileCall.enqueue(new Callback<DataProfile>() {
            @Override
            public void onResponse(Call<DataProfile> call, Response<DataProfile> response) {
//                try {
                    if (response.isSuccessful()) {
                        profileLayoutError.setVisibility(View.VISIBLE);
                        errorMsg.setVisibility(View.GONE);
                        Helper.checkData(response.body().getData().getName() ,nameCompany);
                        Helper.checkData(response.body().getData().getAboutcompany() ,aboutUs);
                        Helper.checkData(response.body().getData().getEmail() ,emailCompany);
                        Helper.checkData(response.body().getData().getMobileNum() ,mobileCompany);
                        Helper.checkData(response.body().getData().getAddress() ,addressCompany);

                        try {
                            if (Helper.checkData(response.body().getData().getSocailLinks().getWebsite() +"" ,linkCompany)){
                                linkCompany.setText(response.body().getData().getSocailLinks().getWebsite() +"");
                            }
                            linkCompany.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent( Intent.ACTION_VIEW, Uri.parse(response.body().getData().getSocailLinks().getWebsite() +"")));
                                }
                            });
                        }catch (NullPointerException e){
                            linkCompany.setClickable(false);
                            linkCompany.setText("Empty");
                        }
                        Picasso.get().load(response.body().getData().getBannerImage() + "").into(image_cover);
                        Picasso.get().load(response.body().getData().getLogoimage() + "").into(logoImage);
                    }
//                    else {
//                        profileLayoutError.setVisibility(View.GONE);
//                        errorMsg.setVisibility(View.VISIBLE);
//                    }
//                }catch (Exception e){
//                    profileLayoutError.setVisibility(View.GONE);
//                    errorMsg.setVisibility(View.VISIBLE);
//                }
            }
            @Override
            public void onFailure(Call<DataProfile> call, Throwable t) {
                Log.d(LOG, "Error : " + t.getMessage());
            }
        });

    }

}