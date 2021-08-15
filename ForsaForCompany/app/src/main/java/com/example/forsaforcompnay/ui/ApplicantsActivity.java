package com.example.forsaforcompnay.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.adapters.ApplicantAdapter;
import com.example.forsaforcompnay.api.Api;
import com.example.forsaforcompnay.model.show.applicants.post.ShowPostApplicant;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicantsActivity extends AppCompatActivity implements Serializable {
    RecyclerView applicantsRV;
    String idPost;
    TextView noApplicant , btnBack , titleActivity;
    RelativeLayout errorMsg , applicantsHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants);
        idPost = "" + getIntent().getIntExtra("idPost", -1);
        into();
        if (getResources().getBoolean(R.bool.is_ar)){
            btnBack.setRotation(180f);
        }
        titleActivity.setText(getResources().getString(R.string.show_applicants));
        ShowApplicants();
    }

    private void into() {

        applicantsRV = findViewById(R.id.applicants_rc);
        applicantsRV.setHasFixedSize(true);
        noApplicant = findViewById(R.id.noApplicant);
        errorMsg = findViewById(R.id.errorMsg);
        applicantsHeader = findViewById(R.id.applicants_header);
        btnBack = applicantsHeader.findViewById(R.id.btnBack);
        titleActivity = applicantsHeader.findViewById(R.id.titleActivity);

    }

    private void ShowApplicants() {
        Call<ShowPostApplicant> showPostApplicantCall = Api.getINSTANCE(getApplicationContext()).getApiInterface()
                .SHOW_POST_APPLICANT_CALL(idPost);
        showPostApplicantCall.enqueue(new Callback<ShowPostApplicant>() {
            @Override
            public void onResponse(Call<ShowPostApplicant> call, Response<ShowPostApplicant> response) {
                try {
                    if(response.body().getData().getApplicants().size() != 0){
                        applicantsRV.setVisibility(View.VISIBLE);
                        noApplicant.setVisibility(View.GONE);
                        applicantsRV.setAdapter(new ApplicantAdapter(response.body().getData().getApplicants()
                                , getApplicationContext()));
                    }else {
                        applicantsRV.setVisibility(View.GONE);
                        noApplicant.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    applicantsRV.setVisibility(View.GONE);
                    noApplicant.setVisibility(View.GONE);
                    errorMsg.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<ShowPostApplicant> call, Throwable t) {
                Log.d("show", "Error : " + t.getMessage());
            }
        });
    }
}