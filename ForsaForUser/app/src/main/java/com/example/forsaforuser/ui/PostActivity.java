package com.example.forsaforuser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforuser.R;
import com.example.forsaforuser.adapters.TagAdapter;
import com.example.forsaforuser.api.Api;
import com.example.forsaforuser.model.postDetailes.PostData;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    Toolbar header_post;
    TextView screen_title, post_company_name, btn_back, post_title, post_date, submit_post_btn, postText;
    RecyclerView rc_tag_post;
    CircleImageView company_image;
    private String post_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        init();
        post_id = getIntent().getStringExtra("post_id");
        Log.e("postId", "" + post_id);
        request(post_id);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submit_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this, SubmitActivity.class);
                intent.putExtra("post_id", post_id);
                intent.putExtra("title", post_title.getText().toString());
                startActivity(intent);
            }
        });
    }


    private void init() {
        header_post = findViewById(R.id.header_post);
        screen_title = findViewById(R.id.screen_title);
        screen_title.setText(R.string.post);
        btn_back = header_post.findViewById(R.id.btn_back);
        post_company_name = findViewById(R.id.post_company_name);
        post_date = findViewById(R.id.post_date);
        submit_post_btn = findViewById(R.id.submit_post_btn);
        postText = findViewById(R.id.postText);
        rc_tag_post = findViewById(R.id.rc_tag_post);
        post_title = findViewById(R.id.post_title);
        company_image = findViewById(R.id.post_company_image);
    }

    private void request(String post_id) {
        Call<PostData> postItemCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().getPostDtailes(post_id);
        postItemCall.enqueue(new Callback<PostData>() {
            @Override
            public void onResponse(Call<PostData> call, Response<PostData> response) {
                if (response.isSuccessful()) {
                    postText.setText(response.body().getPost().getPostText());
                    post_title.setText(response.body().getPost().getPostTitle());
                    post_company_name.setText(response.body().getPost().getCompanyName());
                    post_date.setText(response.body().getPost().getDueDate());
                    if (!response.body().getPost().getImageCompany().
                            equals("http://192.168.0.112/Backend/img/default.jpg")) {
                        Picasso.get().load(response.body().getPost().getImageCompany()).into(company_image);
                    } else {
                        company_image.setImageResource(R.drawable.default_account);
                    }
                    rc_tag_post.setLayoutManager(new LinearLayoutManager(PostActivity.this,
                            LinearLayoutManager.HORIZONTAL, false));
                    rc_tag_post.setAdapter(
                            new TagAdapter(response.body().getPost().getSkills()
                                    , getApplicationContext())
                    );
                } else {
                    Toast.makeText(PostActivity.this,
                            "something wrong in the request ",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostData> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

}