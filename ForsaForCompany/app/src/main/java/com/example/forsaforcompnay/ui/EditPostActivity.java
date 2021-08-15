package com.example.forsaforcompnay.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.api.Api;
import com.example.forsaforcompnay.model.home.ItemDataHomeCompany;
import com.example.forsaforcompnay.model.post.Post;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPostActivity extends AppCompatActivity {
    int idPost;
    HashMap<String, Object> editPostHashMap;
    ItemDataHomeCompany itemDataHomeCompany;
    EditText titlePost , expireDate , description;
    TextView updateNewPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        init();
        idPost = getIntent().getIntExtra("idPost", -1);
        itemDataHomeCompany = (ItemDataHomeCompany) getIntent().getSerializableExtra("itemDataHomeCompany");
        titlePost.setText(itemDataHomeCompany.getPostTitle());
        expireDate.setText(itemDataHomeCompany.getDueDate());
        description.setText(itemDataHomeCompany.getPostText());
        updateNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditPost();
            }
        });
    }

    private void init() {
        titlePost = findViewById(R.id.titlePost);
        expireDate = findViewById(R.id.expireDate);
        description = findViewById(R.id.description);
        updateNewPost = findViewById(R.id.updateNewPost);
    }

    private void EditPost() {
        String newTitle = titlePost.getText().toString();
        String newExDate = expireDate.getText().toString();
        String newDes = description.getText().toString();
        if (newExDate.isEmpty() || newDes.isEmpty() || newTitle.isEmpty() ||
                newExDate.equals("") || newDes.equals("") || newTitle.equals("")){
            Toast.makeText(getApplicationContext() , "" , Toast.LENGTH_LONG).show();
        }else {
            editPostHashMap = new HashMap<>();
            editPostHashMap.put("title", newTitle);
            editPostHashMap.put("expire_date", newExDate);
            editPostHashMap.put("skills_id[0]", 1);
            editPostHashMap.put("description", newDes);

            Call<Post> postCall = Api.getINSTANCE(getApplicationContext())
                    .getApiInterface().EDIT_POST_CALL(idPost , editPostHashMap);
            postCall.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getApplicationContext() , "Done" , Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext() , "" , Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Toast.makeText(getApplicationContext() , "Error" , Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}