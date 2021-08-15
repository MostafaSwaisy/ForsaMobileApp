package com.example.forsaforuser.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forsaforuser.R;
import com.example.forsaforuser.api.Api;
import com.example.forsaforuser.model.company.Company;
import com.example.forsaforuser.model.profile.ProfileLink;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyProfileActivity extends AppCompatActivity {
    int company_id;
    ImageView image_cover;
    CircleImageView company_logo;
    TextView about_company, company_email, company_name, company_mobile_number,
            company_address, company_facebook,
            company_twitter, company_Linkedin, company_website, btn_back_company_profile;
    String facebook, twitter, linkedin;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);
        company_id = getIntent().getIntExtra("company_id", -1);
        init();
        request(company_id);
        company_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebook));
                startActivity(myIntent);
            }
        });
        company_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitter));
                startActivity(myIntent);
            }
        });
        company_Linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedin));
                startActivity(myIntent);
            }
        });
        company_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(company_website.getText().toString()));
                startActivity(myIntent);
            }
        });
        btn_back_company_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init() {
        image_cover = findViewById(R.id.image_cover);
        company_logo = findViewById(R.id.company_logo);
        about_company = findViewById(R.id.about_company);
        company_email = findViewById(R.id.company_email);
        company_name = findViewById(R.id.company_name);
        company_mobile_number = findViewById(R.id.company_mobile_number);
        company_address = findViewById(R.id.company_address);
        company_twitter = findViewById(R.id.company_twitter);
        company_Linkedin = findViewById(R.id.company_Linkedin);
        company_facebook = findViewById(R.id.company_facebook);
        company_website = findViewById(R.id.company_website);
        btn_back_company_profile = findViewById(R.id.btn_back_company_profile);
    }

    private void request(int id) {
        if (id != -1) {
            Call<Company> companyCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().showCompany(id);
            companyCall.enqueue(new Callback<Company>() {
                @Override
                public void onResponse(Call<Company> call, Response<Company> response) {
                    if (response.isSuccessful()) {
                        try {
                            company_name.setText(response.body().getData().getName());
                            company_email.setText(response.body().getData().getEmail());
                            Picasso.get().load(response.body().getData().getBannerImage()).into(image_cover);
                            Picasso.get().load(response.body().getData().getLogoimage()).into(company_logo);
                            about_company.setText(response.body().getData().getAboutcompany());
                            company_mobile_number.setText(response.body().getData().getMobileNum());
                            company_address.setText(response.body().getData().getAddress());
                            if (response.body().getData().getProfileLink() != null) {
                                social_links(response.body().getData().getProfileLink());
                            } else {
                                company_website.setText("there is no links yet ");
                                company_facebook.setVisibility(View.GONE);
                                company_twitter.setVisibility(View.GONE);
                                company_Linkedin.setVisibility(View.GONE);
                            }
                        } catch (NullPointerException n) {
                            Toast.makeText(CompanyProfileActivity.this, "Something Wrong here", Toast.LENGTH_SHORT).show();

                        }

                    }
                }

                @Override
                public void onFailure(Call<Company> call, Throwable t) {
                    Log.e("Company Profile", t.getMessage());
                }
            });
        } else {
            Toast.makeText(this, "Something Wrong ?!", Toast.LENGTH_SHORT).show();
        }
    }

    private void social_links(ProfileLink social_links) {
        if (social_links.getFacebook() != null) {
            facebook = social_links.getFacebook();
        }
        if (social_links.getWebsite() != null) {
            company_website.setText(social_links.getWebsite());
        }
        if (social_links.getTwitter() != null) {
            twitter = social_links.getTwitter();
        }
        if (social_links.getLinkedin() != null) {
            linkedin = social_links.getLinkedin();
        }

    }

}