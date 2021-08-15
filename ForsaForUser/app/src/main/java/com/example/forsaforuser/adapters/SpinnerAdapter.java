package com.example.forsaforuser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.forsaforuser.R;
import com.example.forsaforuser.model.constant.Data;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<Data> data;

    public SpinnerAdapter(Context context, ArrayList<Data> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_custom_spinner, null);
        TextView sp_data = view.findViewById(R.id.sp_data);
        sp_data.setText(data.get(i).getName());
        return view;
    }
}
