package com.example.forsaforcompnay.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.model.archive.ItemDataArchiveCompany;
import com.example.forsaforcompnay.ui.HomeActivity;
import com.example.forsaforcompnay.ui.ProfileActivity;
import com.example.forsaforcompnay.ui.UserProfileActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<ItemDataArchiveCompany> itemDataArchiveCompanyArrayList;

    public ArchiveAdapter(Context context, ArrayList<ItemDataArchiveCompany> itemDataArchiveCompanyArrayList) {
        this.context = context;
        this.itemDataArchiveCompanyArrayList = itemDataArchiveCompanyArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_archive, parent, false);
        return new ArchiveAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemDataArchiveCompany itemDataArchiveCompany = itemDataArchiveCompanyArrayList.get(position);
        holder.postTitleArchive.setText(itemDataArchiveCompany.getPostTitle());
        holder.postTextArchive.setText(itemDataArchiveCompany.getApplicant_notes());
        holder.dateArchive.setText(itemDataArchiveCompany.getDate());
        holder.RV_tagArchive.setAdapter(new TagSkills(itemDataArchiveCompany.getSkills(), context));
        if (itemDataArchiveCompany.getStatus().equals("مرفوض") ||
                itemDataArchiveCompany.getStatus().equalsIgnoreCase("Canceled")) {
            holder.showApplicant.setText(itemDataArchiveCompany.getStatus());
            holder.showApplicant.setBackgroundResource(R.drawable.background_canceled);
        } else if (itemDataArchiveCompany.getStatus().equals("مقبول") ||
                itemDataArchiveCompany.getStatus().equalsIgnoreCase("Accepted")) {
            holder.showApplicant.setText(itemDataArchiveCompany.getStatus());
            holder.showApplicant.setBackgroundResource(R.drawable.background_accepted);
        } else if (itemDataArchiveCompany.getStatus().equals("قيد المراجعة") ||
                itemDataArchiveCompany.getStatus().equalsIgnoreCase("Review")) {
            holder.showApplicant.setText(itemDataArchiveCompany.getStatus());
            holder.showApplicant.setBackgroundResource(R.drawable.background_wating);
        }
        Picasso.get().load(itemDataArchiveCompany.getUser().getImage() + "").into(holder.imageUser);
        holder.imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context.getApplicationContext(), UserProfileActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("user", itemDataArchiveCompany.getUser())
                        .putExtra("idApplicants", itemDataArchiveCompany.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemDataArchiveCompanyArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LoaderTextView dateArchive, postTextArchive, postTitleArchive;
        RecyclerView RV_tagArchive;
        TextView showApplicant;
        ImageView imageUser;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dateArchive = itemView.findViewById(R.id.dateArchive);
            postTitleArchive = itemView.findViewById(R.id.postTitleArchive);
            postTextArchive = itemView.findViewById(R.id.postTextArchive);
            RV_tagArchive = itemView.findViewById(R.id.RV_tagArchive);
            RV_tagArchive.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            showApplicant = itemView.findViewById(R.id.showApplicant);
            imageUser = itemView.findViewById(R.id.imageUser);
        }
    }
}
