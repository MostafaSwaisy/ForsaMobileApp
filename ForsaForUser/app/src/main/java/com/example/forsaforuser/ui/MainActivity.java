package com.example.forsaforuser.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforuser.R;
import com.example.forsaforuser.adapters.PostAdapter;
import com.example.forsaforuser.api.Api;
import com.example.forsaforuser.helper.Helper;
import com.example.forsaforuser.model.home.Home;
import com.example.forsaforuser.model.home.PostItem;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public PostAdapter postAdapter;
    RecyclerView rc_post;
    LinearLayout btnProfile, btnArchive;
    ArrayList<PostItem> postItemList;
    Toolbar headerHome;
    ImageButton btnNavigation;
    DrawerLayout drawerLayout;
    NavigationView nav_view;
    CircleImageView imageUser;
    AppCompatTextView home, profile, tagUser, archive, about_us, about_app, visit_website, nameUser, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserProfileActivity.class));
            }
        });
        btnArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ArchiveActivity.class));
            }
        });
        btnNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //                moveToActivity(MainActivity.this, StartActivity.class,null,false,true);
        archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ArchiveActivity.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserProfileActivity.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.getINSTANCE(getApplicationContext()).clearall();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        visit_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.0.112:80"));
                startActivity(myIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        drawerLayout.closeDrawers();
        callPost();
    }

    void init() {
        rc_post = findViewById(R.id.rc_post);
        btnProfile = findViewById(R.id.btnProfile);
        btnArchive = findViewById(R.id.btnArchive);
        headerHome = findViewById(R.id.headerHome);
        btnNavigation = headerHome.findViewById(R.id.btnNavigation);
        drawerLayout = findViewById(R.id.drawerLayout);
        nav_view = findViewById(R.id.nav_view);
        home = nav_view.findViewById(R.id.btnHome);
        profile = nav_view.findViewById(R.id.profile);
        archive = nav_view.findViewById(R.id.archive);
        about_us = nav_view.findViewById(R.id.btnAboutUs);
        about_app = nav_view.findViewById(R.id.btnAboutApp);
        visit_website = nav_view.findViewById(R.id.btnVisit_website);
        logout = nav_view.findViewById(R.id.logout);
        imageUser = nav_view.findViewById(R.id.imageUser);
        nameUser = nav_view.findViewById(R.id.nameUser);
        tagUser = nav_view.findViewById(R.id.tagUser);
        tagUser.setText(Helper.getINSTANCE(getApplicationContext()).getString("email"));
        nameUser.setText(Helper.getINSTANCE(getApplicationContext()).getString("name"));
        Picasso.get().load(Helper.getINSTANCE(getApplicationContext()).getString("image")).into(imageUser);
    }

    void callPost() {

        Call<Home> postCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().homescreen();
        postCall.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                if (response.isSuccessful()) {
                    try {
                        postItemList = response.body().getData().getPosts();
                        Collections.reverse(postItemList);
                        postAdapter = new PostAdapter(postItemList, getApplicationContext());
                        rc_post.setAdapter(postAdapter);
                    } catch (NullPointerException n) {
                        Toast.makeText(MainActivity.this, "Something Wrong here", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "No post Yet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Log.e("Main Activity Error", t.getMessage());
            }
        });

    }

}