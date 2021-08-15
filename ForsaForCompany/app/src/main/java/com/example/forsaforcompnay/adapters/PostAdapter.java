package com.example.forsaforcompnay.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.model.home.ItemDataHomeCompany;
import com.example.forsaforcompnay.ui.ApplicantsActivity;
import com.example.forsaforcompnay.ui.PostDetailesActivity;
import com.example.forsaforcompnay.ui.ProfileActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyPostViewHolder> {
    Context context;
    ArrayList<ItemDataHomeCompany> itemDataHomeCompanyArrayList;

    public PostAdapter(Context context, ArrayList<ItemDataHomeCompany> itemDataHomeCompanyArrayList) {
        this.context = context;
        this.itemDataHomeCompanyArrayList = itemDataHomeCompanyArrayList;
    }

    @NonNull
    @Override
    public MyPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostAdapter.MyPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPostViewHolder holder, int position) {
        ItemDataHomeCompany itemDataHomeCompany = itemDataHomeCompanyArrayList.get(position);
        holder.postTitle.setText(itemDataHomeCompany.getPostTitle());
        holder.postText.setText(itemDataHomeCompany.getPostText());
        holder.date.setText(itemDataHomeCompany.getCreateAT());
        holder.companyName.setText(itemDataHomeCompany.getCompanyName());
        Picasso.get().load(itemDataHomeCompany.getImageCompany() + "").into(holder.imageCompany);
        holder.RV_tag.setAdapter(new TagSkills(itemDataHomeCompany.getSkills(), context));
        holder.moreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getApplicationContext(), PostDetailesActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("idPost", itemDataHomeCompany.getId()));
            }
        });
        holder.showApplicants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getApplicationContext(), ApplicantsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("idPost", itemDataHomeCompany.getId()));
            }
        });
        holder.imageCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getApplicationContext(), ProfileActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemDataHomeCompanyArrayList.size();
    }

    public class MyPostViewHolder extends RecyclerView.ViewHolder {
        TextView postTitle, date, companyName, postText;
        ImageView imageCompany;
        RecyclerView RV_tag;
        TextView moreDetails, showApplicants;

        public MyPostViewHolder(@NonNull View itemView) {
            super(itemView);
            postTitle = itemView.findViewById(R.id.postTitle);
            postText = itemView.findViewById(R.id.postText);
            date = itemView.findViewById(R.id.date);
            companyName = itemView.findViewById(R.id.companyName);
            imageCompany = itemView.findViewById(R.id.imageCompany);
            moreDetails = itemView.findViewById(R.id.moreDetails);
            showApplicants = itemView.findViewById(R.id.showApplicants);
            RV_tag = itemView.findViewById(R.id.RV_tag);
            RV_tag.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        }
    }
}
