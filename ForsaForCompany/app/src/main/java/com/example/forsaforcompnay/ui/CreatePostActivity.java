package com.example.forsaforcompnay.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.adapters.TagChooseAdapter;
import com.example.forsaforcompnay.adapters.TagSkills;
import com.example.forsaforcompnay.api.Api;
import com.example.forsaforcompnay.model.constant.GetConstants;
import com.example.forsaforcompnay.model.constant.Skills;
import com.example.forsaforcompnay.model.post.Post;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity implements TagChooseAdapter.onClick {
    private static final String LOG = "CreatePostActivity";

    EditText  description, titlePost;
    TextView createNewPost;
    Dialog dialog;
    TextView expireDate,skillEd;
    RecyclerView skillsRV, skillrc;
    ArrayList<Skills> skills;
    TagSkills tagAdapter;

    ArrayList<Skills>  tagClicked = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        init();
        getConstant();

        expireDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDatePikser();
            }
        });

        createNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNewPost();
            }
        });
        skillEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSkills();
            }
        });
        tagAdapter = new TagSkills(tagClicked, getApplicationContext());
        skillrc.setAdapter(tagAdapter);

    }

    private void init() {
        RelativeLayout toolbar = findViewById(R.id.header_post);
        TextView title = toolbar.findViewById(R.id.titleActivity);
        TextView back = toolbar.findViewById(R.id.btnBack);
        title.setText(getString(R.string.create_post));
        expireDate = findViewById(R.id.expireDate);
        expireDate.setInputType(InputType.TYPE_NULL);
        description = findViewById(R.id.description);
        titlePost = findViewById(R.id.titlePost);
        createNewPost = findViewById(R.id.createNewPost);
        skillEd = findViewById(R.id.skills);
        skillrc = findViewById(R.id.skills_rc);
    }

    private void getConstant() {
        Call<GetConstants> constantCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().GET_CONSTANTS_CALL();
        constantCall.enqueue(new Callback<GetConstants>() {
            @Override
            public void onResponse(Call<GetConstants> call, Response<GetConstants> response) {
                skills = response.body().getData().getSkills();
            }

            @Override
            public void onFailure(Call<GetConstants> call, Throwable t) {

            }
        });

    }

    private void CreateNewPost() {
        String des = description.getText().toString();
        String exDate = expireDate.getText().toString();
        String title = titlePost.getText().toString();
        if (exDate.isEmpty() || des.isEmpty() || title.isEmpty() ||
                exDate.equals("") || des.equals("") || title.equals("") ||
                tagClicked.size() == 0 || tagClicked == null) {
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, Object> dataPost = new HashMap<>();
            dataPost.put("title", title);
            dataPost.put("expire_date", exDate);
            dataPost.put("description", des);
            dataPost.put("skills_id[0]", 1);
            for (int i = 0; i < tagClicked.size(); i++) {
                dataPost.put("skills_id["+i+"]", tagClicked.get(i).getId());
                Log.d("postmanTest" , tagClicked.get(i).getId()+"");
            }
            Call<Post> postCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().POST_CALL(dataPost);
            postCall.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    try {
                        if (response.isSuccessful()) {
                            // Toast.makeText(CreatePostActivity.this, "Done", Toast.LENGTH_SHORT).show();
                            // Toast.makeText(CreatePostActivity.this, response.body().getData().getSkills().get(0).getName(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreatePostActivity.this,HomeActivity.class));
                        } else {
                            Toast.makeText(CreatePostActivity.this, "Error Body", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception n){
                        startActivity(new Intent(CreatePostActivity.this,HomeActivity.class));
                    }


                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Toast.makeText(CreatePostActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


    private void getDatePikser() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        DatePickerDialog datePickerDialog = new DatePickerDialog(CreatePostActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (year >= mYear && monthOfYear >= mMonth && dayOfMonth > mDay) {
                            String dateToday = mYear + "-" + mMonth + "-" + mDay;
                            String dateTime = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            Log.d("myDateTime", dateTime);
                            expireDate.setText(dateTime);
                        } else {
                            Toast.makeText(getApplicationContext(), "الرجاء اختيار تاريخ صالح", Toast.LENGTH_LONG).show();
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void showDialogSkills() {

        dialog = new Dialog(CreatePostActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog, null);
        skillsRV = view.findViewById(R.id.skillsRV);
        skillsRV.setAdapter(new TagChooseAdapter(skills, getApplicationContext(), this));
        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    public void onClickItem(String title, int isSelect, int position) {
        for (Skills skill : skills) {
            if (skill.getIsSelected() == 0) {
                tagClicked.add(skill);
            }
        }
        tagAdapter.notifyDataSetChanged();

    }
}