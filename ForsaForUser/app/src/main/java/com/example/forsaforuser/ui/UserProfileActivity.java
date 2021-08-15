package com.example.forsaforuser.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforuser.R;
import com.example.forsaforuser.adapters.TagAdapter;
import com.example.forsaforuser.api.Api;
import com.example.forsaforuser.helper.Helper;
import com.example.forsaforuser.model.profile.Profile;
import com.example.forsaforuser.model.profile.ProfileLink;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {
    TextView user_name, user_email, Specialization, degree,
            uneversity, avarege, mobileNum, btn_back,
            user_profileLink, user_facebook, user_twitter, user_Linkedin, update_profile_btn;
    CircleImageView user_iamge;
    String facebook, twitter, linkedin;
    RecyclerView skills_rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        init();
        request();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        update_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfileActivity.this, UpdateAccountActivity.class));
            }
        });
        user_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebook));
                startActivity(myIntent);
            }
        });
        user_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitter));
                startActivity(myIntent);
            }
        });
        user_Linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedin));
                startActivity(myIntent);
            }
        });
        user_profileLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(user_profileLink.getText().toString()));
                startActivity(myIntent);
            }
        });
    }

    void init() {
        btn_back = findViewById(R.id.btn_back_profile);
        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        Specialization = findViewById(R.id.Specialization);
        degree = findViewById(R.id.degree);
        uneversity = findViewById(R.id.uneversity);
        avarege = findViewById(R.id.avarege);
        mobileNum = findViewById(R.id.mobileNum);
        user_iamge = findViewById(R.id.user_iamge);
        user_profileLink = findViewById(R.id.user_profileLink);
        user_facebook = findViewById(R.id.user_facebook);
        user_twitter = findViewById(R.id.user_twitter);
        user_Linkedin = findViewById(R.id.user_Linkedin);
        update_profile_btn = findViewById(R.id.update_profile_btn);
        skills_rc=findViewById(R.id.skills_rc);
    }

    void request() {
        Call<Profile> profileCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().profile();
        profileCall.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful()) {
                    try {
                        user_name.setText(response.body().getData().getUsername());
                        user_email.setText(response.body().getData().getEmail());
                        Specialization.setText(response.body().getData().getSpecialization());
                        degree.setText(response.body().getData().getDegree());
                        uneversity.setText(response.body().getData().getUneversity());
                        avarege.setText(response.body().getData().getAvarege());
                        Helper.getINSTANCE(getApplicationContext()).setString("avarage", response.body().getData().getAvarege());
                        Helper.getINSTANCE(getApplicationContext()).setString("Specialization", response.body().getData().getSpecialization());
                        Helper.getINSTANCE(getApplicationContext()).setString("degree", response.body().getData().getDegree());
                        Helper.getINSTANCE(getApplicationContext()).setString("uneversity", response.body().getData().getUneversity());
                        mobileNum.setText(response.body().getData().getMobileNum());
                        Picasso.get().load(response.body().getData().getImage()).into(user_iamge);
                        System.out.println(response.body().getData().getUsername());
                        skills_rc.setAdapter(new TagAdapter(response.body().getData().getSkills(),getApplicationContext()));
                        if (response.body().getData().getProfileLink() != null) {
                            social_links(response.body().getData().getProfileLink());
                        } else {
                            user_profileLink.setText("there is no links yet");
                            user_facebook.setVisibility(View.GONE);
                            user_twitter.setVisibility(View.GONE);
                            user_Linkedin.setVisibility(View.GONE);
                        }
                    } catch (NullPointerException n) {
                        Toast.makeText(UserProfileActivity.this, "Something Wrong here", Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }

    private void social_links(ProfileLink social_links) {
        if (social_links.getFacebook() != null) {
            facebook = social_links.getFacebook();
        }else {
            user_facebook.setVisibility(View.GONE);

        }
        if (social_links.getWebsite() != null) {
            user_profileLink.setText(social_links.getWebsite());
        }else {
            user_profileLink.setText("there is no links yet");
        }
        if (social_links.getTwitter() != null) {
            twitter = social_links.getTwitter();
        }else {
            user_twitter.setVisibility(View.GONE);

        }
        if (social_links.getLinkedin() != null) {
            linkedin = social_links.getLinkedin();
        }else {
            user_Linkedin.setVisibility(View.GONE);
        }

    }
}