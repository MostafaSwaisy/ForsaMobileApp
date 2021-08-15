package com.example.forsaforcompnay.ui;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forsaforcompnay.R;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        into();
    }

    private void into() {
        RelativeLayout toolbar = findViewById(R.id.ContactUsHeader);
        TextView title = toolbar.findViewById(R.id.titleActivity);
        TextView back = toolbar.findViewById(R.id.btnBack);
        title.setText(getString(R.string.contact_us));
    }

}