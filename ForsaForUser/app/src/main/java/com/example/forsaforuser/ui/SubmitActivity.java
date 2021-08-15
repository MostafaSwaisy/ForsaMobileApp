package com.example.forsaforuser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.forsaforuser.R;
import com.example.forsaforuser.api.Api;
import com.example.forsaforuser.model.postDetailes.Applicant;
import com.example.forsaforuser.model.postDetailes.SubmitPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {
    String post_title_string;
    Toolbar header_submit;
    TextView screen_title, post_title, submit_applicant_btn, btn_back;
    EditText proposal;
    int post_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        post_id = Integer.parseInt(getIntent().getStringExtra("post_id"));
        post_title_string = getIntent().getStringExtra("title");
        init();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submit_applicant_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (proposal.getText().toString() != null
                        || !proposal.getText().toString().trim().equals("")) {
                    request(new SubmitPost(post_id, proposal.getText().toString()));
                } else {
                    Toast.makeText(SubmitActivity.this, R.string.write_proposal, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request(SubmitPost s) {
        Call<Applicant> applicantCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().submitPost(s);
        applicantCall.enqueue(new Callback<Applicant>() {
            @Override
            public void onResponse(Call<Applicant> call, Response<Applicant> response) {
                try {
                    if (response.body().isSuccess() && response.isSuccessful()) {
                        Toast.makeText(SubmitActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SubmitActivity.this, MainActivity.class));
                    }
                } catch (NullPointerException e) {
                    Toast.makeText(SubmitActivity.this, R.string.write_proposal, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Applicant> call, Throwable t) {
                Log.e("Error Submit Activity", t.getMessage());
            }
        });
    }

    private void init() {
        header_submit = findViewById(R.id.header_submit);
        screen_title = header_submit.findViewById(R.id.screen_title);
        screen_title.setText(R.string.submit_Applicant);
        post_title = findViewById(R.id.post_title);
        submit_applicant_btn = findViewById(R.id.submit_applicant_btn);
        proposal = findViewById(R.id.proposal);
        post_title.setText("" + post_title_string);
        btn_back = header_submit.findViewById(R.id.btn_back);
    }
}