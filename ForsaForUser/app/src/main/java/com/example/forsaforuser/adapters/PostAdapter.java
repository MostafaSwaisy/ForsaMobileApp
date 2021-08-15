package com.example.forsaforuser.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.example.forsaforuser.R;
import com.example.forsaforuser.model.home.PostItem;
import com.example.forsaforuser.ui.CompanyProfileActivity;
import com.example.forsaforuser.ui.PostActivity;
import com.example.forsaforuser.ui.SubmitActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    ArrayList<PostItem> data;
    Context context;


    public PostAdapter(ArrayList<PostItem> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.postText.setText(data.get(position).getPostText());
        holder.company_name.setText(data.get(position).getCompanyName());
        holder.company_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CompanyProfileActivity.class);
                intent.putExtra("company_id", data.get(position).getCompanyId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.postTitle.setText(data.get(position).getPostTitle());
        holder.postText.setText(data.get(position).getPostText());
        if (!data.get(position).isIs_applicant()) {
            holder.applicant_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SubmitActivity.class);
                    intent.putExtra("post_id", "" + data.get(position).getId());
                    intent.putExtra("title", "" + data.get(position).getPostTitle());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        } else {
            holder.applicant_btn.setClickable(false);
            holder.applicant_btn.setBackgroundResource(R.drawable.background_accepted);
            holder.applicant_btn.setText(R.string.applicant_done);

        }
        holder.postText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("post_id", "" + data.get(position).getId());
                intent.putExtra("title", "" + data.get(position).getPostTitle());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.date.setText(data.get(position).getCreateAT());
        holder.rv_tag.setAdapter(new TagAdapter(data.get(position).getSkills(), context));
        Picasso.get().load(data.get(position).getImageCompany() + "").into(holder.post_company_image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, company_name, applicant_btn;
        RecyclerView rv_tag;
        CircleImageView post_company_image;
        LoaderTextView postText, postTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_tag = itemView.findViewById(R.id.skills_rc);
            rv_tag.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            postText = itemView.findViewById(R.id.postText);
            postTitle = itemView.findViewById(R.id.postTitle);
            date = itemView.findViewById(R.id.post_date);
            company_name = itemView.findViewById(R.id.post_company_name);
            post_company_image = itemView.findViewById(R.id.post_company_image);
            applicant_btn = itemView.findViewById(R.id.applicant_btn);
        }
    }
}
