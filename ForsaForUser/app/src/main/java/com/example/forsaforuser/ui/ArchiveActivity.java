package com.example.forsaforuser.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforuser.R;
import com.example.forsaforuser.adapters.ArchivePostAdapter;
import com.example.forsaforuser.api.Api;
import com.example.forsaforuser.model.archive.Archive;
import com.example.forsaforuser.model.archive.PostArchive;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArchiveActivity extends AppCompatActivity {
    RecyclerView rc_archive_post;
    ArrayList<PostArchive> postArchiveArrayList;
    ArchivePostAdapter archivePostAdapter;
    TextView noApplicant;
    RelativeLayout errorMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        init();
        request();
        Toolbar toolbar = findViewById(R.id.headerArchive);
        TextView title = toolbar.findViewById(R.id.screen_title);
        title.setText(R.string.archive);
        TextView btn_back = toolbar.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void init() {
        rc_archive_post = findViewById(R.id.rc_archive_post);
        noApplicant = findViewById(R.id.noApplicant);
        errorMsg = findViewById(R.id.errorMsg);
    }

    void request() {
        Call<Archive> postCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().archiveScreen();
        postCall.enqueue(new Callback<Archive>() {
            @Override
            public void onResponse(Call<Archive> call, Response<Archive> response) {
                    try {
                            errorMsg.setVisibility(View.GONE);
                            noApplicant.setVisibility(View.GONE);
                            rc_archive_post.setVisibility(View.VISIBLE);
                            postArchiveArrayList = response.body().getData().getPosts();
                        if (postArchiveArrayList.size() != 0){
                            archivePostAdapter = new ArchivePostAdapter(postArchiveArrayList, ArchiveActivity.this);
                            rc_archive_post.setAdapter(archivePostAdapter);
                        }
                    } catch (NullPointerException n) {
                        noApplicant.setVisibility(View.VISIBLE);
                        rc_archive_post.setVisibility(View.GONE);
                        errorMsg.setVisibility(View.GONE);

                    } catch (Exception e){
                        errorMsg.setVisibility(View.VISIBLE);
                        noApplicant.setVisibility(View.GONE);
                        rc_archive_post.setVisibility(View.GONE);
                    }

            }

            @Override
            public void onFailure(Call<Archive> call, Throwable t) {
                Log.d("error", "Error : " + t.getMessage());
            }
        });
    }
}