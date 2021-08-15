package com.example.forsaforcompnay.ui;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forsaforcompnay.R;

public class AboutAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        into();
    }

    private void into() {
        RelativeLayout toolbar = findViewById(R.id.aboutAppHeader);
        TextView title = toolbar.findViewById(R.id.titleActivity);
        TextView back = toolbar.findViewById(R.id.btnBack);
        title.setText(getString(R.string.about_app));
    }


}