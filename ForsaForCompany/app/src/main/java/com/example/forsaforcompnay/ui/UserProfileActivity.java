package com.example.forsaforcompnay.ui;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.api.Api;
import com.example.forsaforcompnay.helper.Helper;
import com.example.forsaforcompnay.model.ProfileUser;
import com.example.forsaforcompnay.model.applicant.status.ApplicantStatus;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity implements Serializable {
    ProfileUser user ;
    TextView userName , userEmail , specialization , degree , uneversity , otherDegree ,avarege , mobileNum , url , canceledApplicants , acceptedApplicants , userStatus;
    ImageView userImage;
    String idApplicants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        user = (ProfileUser) getIntent().getSerializableExtra("user");
        idApplicants = getIntent().getIntExtra("idApplicants" , -1) + "";
        init();
        putData();
        acceptedApplicants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changApplicantStatus("accepted");
            }
        });

        canceledApplicants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp();
            }
        });
    }

    private void putData() {
        if (Helper.checkData(user.getUsername() ,userName)){
            userName.setText(user.getUsername());
        }
        if (Helper.checkData(user.getEmail() , userEmail)){
            userEmail.setText(user.getEmail());
        }
        if (Helper.checkData(user.getSpecialization() , specialization)){
            specialization.setText(user.getSpecialization());
        }
        if (Helper.checkData(user.getDegree() , degree)){
            degree.setText(user.getDegree());
        }
        if (Helper.checkData(user.getUneversity(), uneversity)){
            uneversity.setText(user.getUneversity());
        }
        if (Helper.checkData(user.getDegree() , otherDegree)){
            otherDegree.setText(user.getDegree());
        }
        if (Helper.checkData(user.getAvarege(),avarege)){
            avarege.setText(user.getAvarege());
        }
       String image = user.getImage() + "";
        if (!image.isEmpty() || !image.equals("")){
            Picasso.get().load(user.getImage() + "").into(userImage);
        }
        if (Helper.checkData(user.getMobileNum(),mobileNum)){
            mobileNum.setText(user.getMobileNum());
        }
        try {
            if (Helper.checkData(user.getProfileLink().getWebsite() +"" ,url)){
                url.setText(user.getProfileLink().getWebsite() +"");
            }
            url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent( Intent.ACTION_VIEW, Uri.parse(user.getProfileLink().getWebsite() +"")));
                }
            });
        }catch (Exception e){
            url.setClickable(false);
            url.setText("Empty");
        }

    }

    private void init() {
        userName  = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        specialization = findViewById(R.id.specialization);
        degree = findViewById(R.id.degree);
        uneversity = findViewById(R.id.uneversity);
        otherDegree = findViewById(R.id.otherDegree);
        avarege = findViewById(R.id.avarege);
        mobileNum = findViewById(R.id.mobileNum);
        url = findViewById(R.id.url);
        userImage = findViewById(R.id.user_image);
        acceptedApplicants = findViewById(R.id.acceptedApplicants);
        canceledApplicants = findViewById(R.id.canceledApplicants);
        userStatus = findViewById(R.id.userStatus);
    }

    private void changApplicantStatus(String status){
        Helper helper = new Helper(getApplicationContext());
        Log.d("ttthhh" , "Token One : "+helper.getString("token"));
        HashMap<String ,String> hashMap = new HashMap<>();
        hashMap.put("applicant_id" , idApplicants);
        hashMap.put("status" , status);
        Call<ApplicantStatus> applicantStatusCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().APPLICANT_STATUS(hashMap);
        applicantStatusCall.enqueue(new Callback<ApplicantStatus>() {
            @Override
            public void onResponse(Call<ApplicantStatus> call, Response<ApplicantStatus> response) {
                try {
                    if(response.isSuccessful()){
                        Toast.makeText(UserProfileActivity.this , "Edit status successfully" , Toast.LENGTH_LONG).show();
                        startActivity(new Intent(UserProfileActivity.this , HomeActivity.class));
                    }else {
                        Toast.makeText(UserProfileActivity.this , "Error" , Toast.LENGTH_LONG).show();
                        Log.d("ttthhh" , response.errorBody() + "");
                    }
                }catch (Exception e){
                    Toast.makeText(UserProfileActivity.this , "Error" , Toast.LENGTH_LONG).show();
                    Log.d("ttthhh" ,  "Edddd  : "+ e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ApplicantStatus> call, Throwable t) {

            }
        });
    }

    private void showPopUp(){
        Dialog dialog = new Dialog(UserProfileActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_pop_up, null);
        TextView textDialog = view.findViewById(R.id.text_request_modal_dialog_details);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changApplicantStatus("canceled");
            }
        });
        view.findViewById(R.id.exitDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }
}