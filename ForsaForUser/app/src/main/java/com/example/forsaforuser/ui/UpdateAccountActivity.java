package com.example.forsaforuser.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforuser.R;
import com.example.forsaforuser.adapters.TagAdapter;
import com.example.forsaforuser.adapters.TagChooseAdapter;
import com.example.forsaforuser.api.Api;
import com.example.forsaforuser.helper.Helper;
import com.example.forsaforuser.model.constant.Constant;
import com.example.forsaforuser.model.constant.Data;
import com.example.forsaforuser.model.profile.UserProfile;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAccountActivity extends AppCompatActivity implements TagChooseAdapter.onClick {
    Toolbar update_header;
    TextView screen_title, btn_back, update_profile_action , clickSkill;
    Spinner university_sp, degree_sp, specialization_sp, skills_sp;
    RecyclerView skills_rc , RV_tag_Choose;
    ArrayList<Data> skills, specialization, university, degree;
    HashMap<String, Object> hashMap;
        Dialog dialog;
    ArrayList<Data> tagClicked;
    TagAdapter tagAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        init();
        request();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        university_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hashMap.put("university_id", university.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                hashMap.put("university_id", Helper.getINSTANCE(getApplicationContext()).getString("uneversity"));
            }
        });

        specialization_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hashMap.put("specialize_id", specialization.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                hashMap.put("specialize_id", Helper.getINSTANCE(getApplicationContext()).getString("Specialization"));
            }
        });
        degree_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hashMap.put("degree_id", degree.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                hashMap.put("degree_id", Helper.getINSTANCE(getApplicationContext()).getString("degree"));
            }
        });

        update_profile_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hashMap.put("skills_id[0]" , 0);
                if (tagClicked.size() != 0){
//                    for (int i = 0; i <tagClicked.size() ; i++) {
//                        hashMap.put("skills_id["+i+"]" , tagClicked.get(i).getId());
//                    }
                    update(hashMap);
                }else {
                    Toast.makeText(getApplicationContext() , "Plase Choose Skills" ,Toast.LENGTH_SHORT).show();
                }

            }
        });


        clickSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        tagAdapter = new TagAdapter(tagClicked , getApplicationContext());
        skills_rc.setAdapter(tagAdapter);
    }

    private void showDialog() {
        tagClicked.clear();
        dialog = new Dialog(UpdateAccountActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_acvity_dialog, null);
        RV_tag_Choose =  view.findViewById(R.id.RV_tag_Choose);
        RV_tag_Choose.setAdapter(new TagChooseAdapter(skills,getApplicationContext(),this));
        dialog.setContentView(view);
        dialog.show();
    }

    private void update(HashMap<String, Object> hashMap) {
        Api.getINSTANCE(getApplicationContext()).getApiInterface().updateProfile(hashMap).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    try {
                        Toast.makeText(UpdateAccountActivity.this
                                , R.string.thankyou + response.body().getUsername(),
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateAccountActivity.this, UserProfileActivity.class));
                    } catch (NullPointerException n) {
                        System.out.println(n.getMessage());
                    }
                }else {
                    Toast.makeText(getApplicationContext() , "Error Body" ,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }

    private void request() {
        Call<Constant> constantCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().getConstant();
        constantCall.enqueue(new Callback<Constant>() {
            @Override
            public void onResponse(Call<Constant> call, Response<Constant> response) {
                university_sp.setAdapter(
                        new com.example.forsaforuser.adapters.SpinnerAdapter(getApplicationContext()
                                , response.body().getConstantData().getUniversities()));
//                skills_sp.setAdapter(
//                        new com.example.forsaforuser.adapters.SpinnerAdapter(getApplicationContext()
//                                , response.body().getConstantData().getSkills()));
                specialization_sp.setAdapter(
                        new com.example.forsaforuser.adapters.SpinnerAdapter(getApplicationContext()
                                , response.body().getConstantData().getSpecialize()));
                degree_sp.setAdapter(
                        new com.example.forsaforuser.adapters.SpinnerAdapter(getApplicationContext()
                                , response.body().getConstantData().getDegrees()));
                degree = response.body().getConstantData().getDegrees();
                skills = response.body().getConstantData().getSkills();
                specialization = response.body().getConstantData().getSpecialize();
                university = response.body().getConstantData().getUniversities();
            }

            @Override
            public void onFailure(Call<Constant> call, Throwable t) {

            }
        });
    }

    private void init() {
        tagClicked = new ArrayList<>();
        update_header = findViewById(R.id.update_header);
        screen_title = update_header.findViewById(R.id.screen_title);
        screen_title.setText(R.string.update_profile);
        btn_back = update_header.findViewById(R.id.btn_back);
        university_sp = findViewById(R.id.university_sp);
        degree_sp = findViewById(R.id.degree_sp);
        specialization_sp = findViewById(R.id.specialization_sp);
       // skills_sp = findViewById(R.id.skills_sp);
        skills_rc = findViewById(R.id.skills_rc);
        clickSkill = findViewById(R.id.clickSkill);
        update_profile_action = findViewById(R.id.update_profile_action);
        hashMap = new HashMap();
        hashMap.put("name", Helper.getINSTANCE(getApplicationContext()).getString("name"));
        hashMap.put("email", Helper.getINSTANCE(getApplicationContext()).getString("email"));
        hashMap.put("mobile_number", Helper.getINSTANCE(getApplicationContext()).getString("mobile_number"));
        hashMap.put("password", Helper.getINSTANCE(getApplicationContext()).getString("password"));
        hashMap.put("avarage", Helper.getINSTANCE(getApplicationContext()).getString("avarage"));
    }

    @Override
    public void onClickItem(String title, int isSelect, int position) {
        for (Data skill : skills){
            if (skill.getIsSelected() == 0){
                tagClicked.add(skill);
            }
        }
        tagAdapter.notifyDataSetChanged();

    }
}