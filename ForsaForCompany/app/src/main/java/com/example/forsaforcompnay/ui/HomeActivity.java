package com.example.forsaforcompnay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.adapters.PostAdapter;
import com.example.forsaforcompnay.api.Api;
import com.example.forsaforcompnay.helper.Helper;
import com.example.forsaforcompnay.model.home.HomeCompany;
import com.example.forsaforcompnay.model.home.ItemDataHomeCompany;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private static final String LOG = "MyHomeActivity";
    LinearLayout btnProfile, btnArchive;
    Toolbar headerHome;
    AppCompatImageButton btnNavigation;
    DrawerLayout drawerLayout;
    RecyclerView RV_post;
    NavigationView navigationView;
    LinearLayout homeLayout, profileLayout, websiteLayout, aboutAppLayout, aboutUsLayout, archiveLayout, logoutLayout;
    TextView createPost, nameCompany, tagCompany;
    String status, name, URL;
    ArrayList<ItemDataHomeCompany> itemDataHomeCompanyArrayList;
    ImageView imageCompany;
    private Helper helper;
    RelativeLayout errorMsg;
    TextView isActive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();


        name = helper.getString("Name");
        status = helper.getString("Status");
        URL = helper.getString("URL");
        nameCompany.setText(name);
        tagCompany.setText(status);
        if (status.equals("inactive") || status.equals("غير فعال")) {
            createPost.setClickable(false);
            createPost.setVisibility(View.GONE);
            isActive.setVisibility(View.VISIBLE);
        } else {
            createPost.setClickable(true);
            isActive.setVisibility(View.GONE);
            createPost.setVisibility(View.VISIBLE);
        }
        RV_post.setHasFixedSize(true);
        Picasso.get().load(URL).into(imageCompany);

        onClickNav();
        btnArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ArchiveActivity.class));
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });

        btnNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }

    private void init() {
        helper = new Helper(getApplicationContext());
        btnArchive = findViewById(R.id.btnArchive);
        btnProfile = findViewById(R.id.btnProfile);
        headerHome = findViewById(R.id.headerHome);
        btnNavigation = headerHome.findViewById(R.id.btnNavigation);
        drawerLayout = findViewById(R.id.drawerLayout);
        RV_post = findViewById(R.id.RV_post);
        navigationView = findViewById(R.id.navigationView);
        homeLayout = navigationView.findViewById(R.id.HomeLayout);
        profileLayout = navigationView.findViewById(R.id.ProfileLayout);
        websiteLayout = navigationView.findViewById(R.id.WebsiteLayout);
        aboutAppLayout = navigationView.findViewById(R.id.AboutAppLayout);
        aboutUsLayout = navigationView.findViewById(R.id.AboutUsLayout);
        archiveLayout = navigationView.findViewById(R.id.ArchiveLayout);
        logoutLayout = navigationView.findViewById(R.id.LogoutLayout);
        createPost = navigationView.findViewById(R.id.createPost);
        nameCompany = navigationView.findViewById(R.id.nameCompany);
        tagCompany = navigationView.findViewById(R.id.tagCompany);
        imageCompany = navigationView.findViewById(R.id.imageCompany);
        errorMsg = findViewById(R.id.errorMsg);
        isActive = findViewById(R.id.isActive);
    }

    @Override
    protected void onResume() {
        super.onResume();
        drawerLayout.closeDrawers();
        getHomeDataCompany();
    }

    private void getHomeDataCompany() {
        itemDataHomeCompanyArrayList = new ArrayList<>();
        Helper helper = new Helper(getApplicationContext());
        System.out.println(helper.getString("token"));
        Call<HomeCompany> homeCompanyCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().HOME_COMPANY_CALL();
        homeCompanyCall.enqueue(new Callback<HomeCompany>() {
            @Override
            public void onResponse(Call<HomeCompany> call, Response<HomeCompany> response) {
                Log.d("ttt", response.isSuccessful() + "");
//                try {
//                    if (response.isSuccessful()) {
//                        errorMsg.setVisibility(View.GONE);
//                        RV_post.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    ArrayList<ItemDataHomeCompany> itemDataHomeCompanies = response.body().getData().getItems();
                    Collections.reverse(itemDataHomeCompanies);
                    itemDataHomeCompanyArrayList.addAll(itemDataHomeCompanies);
                    PostAdapter postAdapter = new PostAdapter(getApplicationContext(), itemDataHomeCompanies);
                    RV_post.setAdapter(postAdapter);
                }
//                    }else {
//                        RV_post.setVisibility(View.GONE);
//                        errorMsg.setVisibility(View.VISIBLE);
//                    }
//                }catch (Exception e){
//                    RV_post.setVisibility(View.GONE);
//                    errorMsg.setVisibility(View.VISIBLE);
//                }

            }

            @Override
            public void onFailure(Call<HomeCompany> call, Throwable t) {
                Log.d(LOG, t.getMessage());
            }
        });
    }

    private void onClickNav() {
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });

        aboutAppLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AboutAppActivity.class));
            }
        });

        aboutUsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AboutUsActivity.class));
            }
        });

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                //startActivity(new Intent(HomeActivity.this, HomeActivity.class));
            }
        });

        archiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ArchiveActivity.class));
            }
        });

        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.getInstance(getApplicationContext()).clearall();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        websiteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CreatePostActivity.class));
            }
        });

    }


}