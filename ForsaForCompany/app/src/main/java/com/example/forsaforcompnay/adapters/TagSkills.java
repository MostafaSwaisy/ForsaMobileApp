package com.example.forsaforcompnay.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.model.constant.Skills;

import java.util.ArrayList;

public class TagSkills extends RecyclerView.Adapter<TagSkills.MyTagViewHolder> {
    private final ArrayList<Skills> tagArrayList;
    Context context;

    public TagSkills(ArrayList<Skills> tagArrayList, Context context) {
        this.tagArrayList = tagArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyTagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
            return new MyTagViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyTagViewHolder holder, int position) {
        Skills tagString = tagArrayList.get(position);
        holder.tag.setText(tagString.getName());
    }

    @Override
    public int getItemCount() {
        return tagArrayList.size();
    }

    public class MyTagViewHolder extends RecyclerView.ViewHolder {
        LoaderTextView tag;

        public MyTagViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tag);
        }
    }
}
