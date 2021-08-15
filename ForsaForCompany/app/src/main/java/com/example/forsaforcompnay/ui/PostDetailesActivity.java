package com.example.forsaforcompnay.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.adapters.TagSkills;
import com.example.forsaforcompnay.api.Api;
import com.example.forsaforcompnay.helper.Helper;
import com.example.forsaforcompnay.model.home.HomeCompany;
import com.example.forsaforcompnay.model.home.ItemDataHomeCompany;
import com.example.forsaforcompnay.model.post.DeletePost;
import com.example.forsaforcompnay.model.post.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class PostDetailesActivity extends AppCompatActivity {
    private final static String LOG = "PostDetailesActivity";
    ArrayList<ItemDataHomeCompany> itemDataHomeCompanyArrayList;
    ItemDataHomeCompany itemDataHomeCompany;
    int idPost;
    RecyclerView tagRV;
    TextView datePost, textPost, titlePost;
    TextView stopApplicants, showApplicants , deletePost , btnEdit;
    ImageView imagePost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detailes);
        into();
        idPost = getIntent().getIntExtra("idPost", -1);
        if (idPost != -1) {
            getHomeDataCompany();
        } else {
            Toast.makeText(PostDetailesActivity.this, "No Id Send", Toast.LENGTH_SHORT).show();
        }

        stopApplicants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp("stop");
            }
        });
        showApplicants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostDetailesActivity.this, ApplicantsActivity.class)
                        .putExtra("idPost", idPost));
            }
        });

        deletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp("delete");
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  EditPost();
                if (itemDataHomeCompany != null){
                    startActivity(new Intent(PostDetailesActivity.this, EditPostActivity.class)
                            .putExtra("idPost", idPost).putExtra("itemDataHomeCompany" , itemDataHomeCompany));
                }
            }
        });
    }

    private void into() {
        showApplicants = findViewById(R.id.showApplicants);
        stopApplicants = findViewById(R.id.stopApplicants);
        datePost = findViewById(R.id.datePost);
        textPost = findViewById(R.id.textPost);
        tagRV = findViewById(R.id.tagRV);
        tagRV.setHasFixedSize(true);
        titlePost = findViewById(R.id.titlePost);
        deletePost = findViewById(R.id.deletePost);
        btnEdit = findViewById(R.id.btnEdit);
        imagePost = findViewById(R.id.imagePost);
        RelativeLayout toolbar = findViewById(R.id.DetailesToolbar);
        TextView title = toolbar.findViewById(R.id.titleActivity);
        TextView back = toolbar.findViewById(R.id.btnBack);
        title.setText(getString(R.string.more_details));
    }

    private void getHomeDataCompany() {
        itemDataHomeCompanyArrayList = new ArrayList<>();
        Call<HomeCompany> homeCompanyCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().HOME_COMPANY_CALL();
        homeCompanyCall.enqueue(new Callback<HomeCompany>() {
            @Override
            public void onResponse(Call<HomeCompany> call, Response<HomeCompany> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess() && response.body().getStatus() == 200) {
                        ArrayList<ItemDataHomeCompany> itemDataHomeCompanies = response.body().getData().getItems();
                        itemDataHomeCompanyArrayList.addAll(itemDataHomeCompanies);
                        for (int i = 0; i < itemDataHomeCompanyArrayList.size(); i++) {
                            if (idPost == itemDataHomeCompanyArrayList.get(i).getId()) {
                                itemDataHomeCompany = itemDataHomeCompanies.get(i);
                                Helper.checkData(itemDataHomeCompanyArrayList.get(i).getCreateAT() ,datePost);
                                Helper.checkData(itemDataHomeCompanyArrayList.get(i).getPostText() ,textPost);
                                Helper.checkData(itemDataHomeCompanyArrayList.get(i).getPostTitle() ,titlePost);
                                Picasso.get().load(itemDataHomeCompanyArrayList.get(i).getImageCompany() + "").into(imagePost);
                                tagRV.setAdapter(new TagSkills(itemDataHomeCompanyArrayList.get(i).getSkills(), getApplicationContext()));
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<HomeCompany> call, Throwable t) {
                // Log.d(LOG, t.getMessage());
            }
        });
    }

    private void EditPost() {
        HashMap<String , Object> editPostHashMap = new HashMap<>();
        editPostHashMap.put("title" ,"title");
        editPostHashMap.put("expire_date" ,"2021-08-15");
        editPostHashMap.put("skills_id[0]" ,"6");
        editPostHashMap.put("skills_id[1]" ,"5");
        editPostHashMap.put("description" ,"description");

        Call<Post> postCall = Api.getINSTANCE(PostDetailesActivity.this).getApiInterface().EDIT_POST_CALL(idPost,editPostHashMap);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    Toast.makeText(PostDetailesActivity.this , "Done" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PostDetailesActivity.this , "Error Body" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(PostDetailesActivity.this , "Error" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void DeletePost() {
        Call<DeletePost> deletePostCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().DELETE_POST_CALL(idPost);
        deletePostCall.enqueue(new Callback<DeletePost>() {
            private static final String LOG = "Ismail";
            @Override
            public void onResponse(Call<DeletePost> call, Response<DeletePost> response) {
                try {
                    if (response.isSuccessful()) {
                        Toast.makeText(PostDetailesActivity.this , "This Post Now Is "+
                                response.body().getMessage() , Toast.LENGTH_LONG).show();
                        startActivity(new Intent(PostDetailesActivity.this , HomeActivity.class));
                    }
                }catch (Exception e){
                    Toast.makeText(PostDetailesActivity.this , "Error" , Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<DeletePost> call, Throwable t) {
                Log.d(LOG, t.getMessage());

            }
        });
    }

    private void StopApplicants() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("post_id" , idPost+"");
        hashMap.put("status" , "0");
        Call<Post> postCall = Api.getINSTANCE(getApplicationContext()).getApiInterface()
                .EDIT_STATUS_POST_CALL(hashMap);
         postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                try {
                    if (response.isSuccessful()){
                        Toast.makeText(PostDetailesActivity.this , "This Post Now Is "+
                                response.body().getData().getStatus() , Toast.LENGTH_LONG).show();
                        startActivity(new Intent(PostDetailesActivity.this , HomeActivity.class));
                    }
                }catch (Exception e){
                    Toast.makeText(PostDetailesActivity.this , "Error" , Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void showPopUp(String text){
        Dialog dialog = new Dialog(PostDetailesActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_pop_up, null);
        TextView textDialog = view.findViewById(R.id.text_request_modal_dialog_details);
        if (text.equals("delete")){
            textDialog.setText(R.string.delete_post_text);
        }else if(text.equals("stop")){
            textDialog.setText(R.string.stop_applicants_text);
        }

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
                if (text.equals("delete")){
                    DeletePost();
                }else if(text.equals("stop")){
                    StopApplicants();
                }

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