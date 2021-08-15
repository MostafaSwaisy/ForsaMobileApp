package com.example.forsaforuser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforuser.R;
import com.example.forsaforuser.model.constant.Data;

import java.util.ArrayList;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.MyTagViewHolder> {
    private final ArrayList<Data> tagArrayList;
    Context context;


    public TagAdapter(ArrayList<Data> tagArrayList, Context context) {
        this.tagArrayList = tagArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyTagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
        return new MyTagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTagViewHolder holder, int position) {
        if (tagArrayList.get(position).getName() != null) {
            holder.tag.setText(tagArrayList.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return tagArrayList.size();
    }


    class MyTagViewHolder extends RecyclerView.ViewHolder {
        TextView tag;

        public MyTagViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.skill_tag);
        }
    }

}

