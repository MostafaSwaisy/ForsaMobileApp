package com.example.forsaforuser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forsaforuser.R;
import com.example.forsaforuser.model.constant.Data;

import java.util.ArrayList;
import java.util.Date;

public class TagChooseAdapter extends RecyclerView.Adapter<TagChooseAdapter.MyViewHolder> {
    ArrayList<Data> dataSkillsArrayList;
    Context context;
    onClick click;

    public interface onClick{
        void onClickItem(String title ,int isSelect , int position);
    }

    public TagChooseAdapter(ArrayList<Data> dataSkillsArrayList, Context context, onClick click) {
        this.dataSkillsArrayList = dataSkillsArrayList;
        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_skills, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       Data skill = dataSkillsArrayList.get(position);
       holder.nameTag.setText(skill.getName());
        holder.isClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skill.setIsSelected(0);
                if (skill.getIsSelected() == 1){
                    holder.iconIsClick.setText("");
                }else if(skill.getIsSelected() ==0){
                    holder.iconIsClick.setText("^_^");
                    click.onClickItem(skill.getName(),skill.getIsSelected(),position);
                    skill.setIsSelected(1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSkillsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTag, iconIsClick;
        LinearLayout isClick;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTag = itemView.findViewById(R.id.nameTag);
            isClick = itemView.findViewById(R.id.isClick);
            iconIsClick = itemView.findViewById(R.id.iconIsClick);
        }
    }
}
