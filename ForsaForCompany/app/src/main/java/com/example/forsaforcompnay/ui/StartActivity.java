package com.example.forsaforcompnay.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.api.Api;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    LinearLayout btnLogin, btnRegstrion, btnAboutApp, btnAboutUs, btnContactUs, btnVisit;
    ImageSlider imageSlider;
    ArrayList<SlideModel> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        init();
        imageList = new ArrayList<>();

        imageList.add(new SlideModel("https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZ0srMeYDswvR105qA8JfqIOYPiYHu2tY-qA&usqp=CAU", ScaleTypes.FIT));
        imageSlider.setImageList(imageList, ScaleTypes.FIT);
        imageSlider.startSliding(5000);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
            }
        });

        btnRegstrion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( Intent.ACTION_VIEW, Uri.parse(Api.getOurUrl() +"company/register")));
            }
        });

        btnAboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, AboutAppActivity.class));
            }
        });

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, AboutUsActivity.class));
            }
        });

        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, ContactUsActivity.class));
            }
        });

        btnVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "", Toast.LENGTH_LONG).show();
            }
        });

    }

    void init() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegstrion = findViewById(R.id.btnRegstrion);
        btnAboutApp = findViewById(R.id.btnAboutApp);
        btnAboutUs = findViewById(R.id.btnAboutUs);
        btnContactUs = findViewById(R.id.btnContactUs);
        btnVisit = findViewById(R.id.btnVisit);
        imageSlider = findViewById(R.id.imageSlider);
    }
}