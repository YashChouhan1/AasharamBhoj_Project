package com.icss.aasharambhoj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.R;
import com.icss.aasharambhoj.model_class.remember.RememberRes;

import java.util.List;


public class RememberPointsAdapter extends RecyclerView.Adapter<RememberPointsAdapter.viewHolder> {
    List<RememberRes> rememberlist;
    Context context;

    public RememberPointsAdapter(List<RememberRes> menuDataModels, Context context) {
        this.rememberlist = menuDataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RememberPointsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.remember_view_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RememberPointsAdapter.viewHolder holder, int position) {
        holder.textView.setText(rememberlist.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return rememberlist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.name);

        }
    }
}
