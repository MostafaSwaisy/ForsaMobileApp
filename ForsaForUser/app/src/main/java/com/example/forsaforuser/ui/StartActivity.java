package com.example.forsaforuser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.forsaforuser.R;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    LinearLayout btnLogin, btnRegstrion;
    ImageSlider imageSlider;
    ArrayList<SlideModel> imageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        imageList = new ArrayList<>();
        init();
        imageList.add(new SlideModel("https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZ0srMeYDswvR105qA8JfqIOYPiYHu2tY-qA&usqp=CAU", ScaleTypes.FIT));
        imageSlider.setImageList(imageList, ScaleTypes.FIT);
        imageSlider.startSliding(5000);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
            }
        });
        btnRegstrion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
            }
        });
    }

    void init() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegstrion = findViewById(R.id.btnRegstrion);
        imageSlider = findViewById(R.id.imageSlider);
    }
}