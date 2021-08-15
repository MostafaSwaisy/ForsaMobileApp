package com.example.forsaforcompnay.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.model.show.applicants.post.ApplicantsPost;
import com.example.forsaforcompnay.ui.UserProfileActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ApplicantAdapter extends RecyclerView.Adapter<ApplicantAdapter.MyViewHolder> {
    ArrayList<ApplicantsPost> applicantsPostArrayList;
    Context context;

    public ApplicantAdapter(ArrayList<ApplicantsPost> applicantsPostArrayList, Context context) {
        this.applicantsPostArrayList = applicantsPostArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_applicant, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ApplicantsPost applicantsPost = applicantsPostArrayList.get(position);
        holder.emailUser.setText(applicantsPost.getUser().getEmail());
        holder.specializationUser.setText(applicantsPost.getUser().getSpecialization());
        holder.timeApplicant.setText(applicantsPost.getDate());
        holder.nameUser.setText(applicantsPost.getUser().getUsername());
        Picasso.get().load(applicantsPost.getUser().getImage() + "").into(holder.imageUser);
        holder.clickUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getApplicationContext() , UserProfileActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("user" , applicantsPost.getUser()).putExtra("idApplicants" , applicantsPost.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return applicantsPostArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageUser;
        TextView emailUser, specializationUser, timeApplicant , nameUser;
        LinearLayout clickUser;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageUser = itemView.findViewById(R.id.imageUser);
            emailUser = itemView.findViewById(R.id.emailUser);
            specializationUser = itemView.findViewById(R.id.specializationUser);
            timeApplicant = itemView.findViewById(R.id.timeApplicant);
            nameUser = itemView.findViewById(R.id.nameUser);
            clickUser = itemView.findViewById(R.id.clickUser);
        }
    }
}
