package com.example.forsaforuser.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforuser.R;
import com.example.forsaforuser.model.archive.PostArchive;

import java.util.ArrayList;

public class ArchivePostAdapter extends RecyclerView.Adapter<ArchivePostAdapter.MyViewHolder> {
    ArrayList<PostArchive> data;
    Context context;

    public ArchivePostAdapter(ArrayList<PostArchive> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_archive, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.archive_company_name.setText(data.get(position).getCompanyName());
        holder.archive_post_date.setText(data.get(position).getDate());
        holder.archive_postText.setText(data.get(position).getApplicant_notes());
        holder.archive_post_title.setText(data.get(position).getPostText());
        holder.archive_post_tag.setAdapter(new TagAdapter(data.get(position).getSkills(), context));
        if (data.get(position).getStatus().equals("قيد المراجعة")
                || data.get(position).getStatus().equalsIgnoreCase("Review")) {
            holder.archive_post_state.setBackgroundResource(R.drawable.background_wating);
        } else if (data.get(position).getStatus().equals("مقبول")
                || data.get(position).getStatus().equalsIgnoreCase("Accepted")) {
            holder.archive_post_state.setText(data.get(position).getStatus());
            holder.archive_post_state.setBackgroundResource(R.drawable.background_accepted);
        } else if (data.get(position).getStatus().equals("مرفوض")
                || data.get(position).getStatus().equalsIgnoreCase("Canceled")){
            holder.archive_post_state.setText(data.get(position).getStatus());
            holder.archive_post_state.setBackgroundResource(R.drawable.background_canceled);

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView archive_company_name, archive_post_date, archive_postText, archive_post_state, archive_post_title;
        RecyclerView archive_post_tag;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            archive_company_name = itemView.findViewById(R.id.archive_company_name);
            archive_post_date = itemView.findViewById(R.id.archive_post_date);
            archive_postText = itemView.findViewById(R.id.archive_postText);
            archive_post_state = itemView.findViewById(R.id.archive_post_state);
            archive_post_tag = itemView.findViewById(R.id.archive_post_tag);
            archive_post_title = itemView.findViewById(R.id.archive_post_title);
            archive_post_tag.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }
    }
}
