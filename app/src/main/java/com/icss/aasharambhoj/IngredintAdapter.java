package com.icss.aasharambhoj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IngredintAdapter  extends RecyclerView.Adapter<IngredintAdapter.viewHolder> {
    List<IngredintDataModel>ingredintDataModels;
    Context context;

    public IngredintAdapter(List<IngredintDataModel> ingredintDataModels, Context context) {
        this.ingredintDataModels = ingredintDataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredintAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ingredintdatamodel,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredintAdapter.viewHolder holder, int position) {
        holder.imageView.setImageResource(ingredintDataModels.get(position).getImage());
        holder.textView1.setText(ingredintDataModels.get(position).getFood_name());
        holder.textView2.setText(ingredintDataModels.get(position).getAdd());

        holder.textView3.setText(ingredintDataModels.get(position).getKg());

        holder.textView4.setText(ingredintDataModels.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return ingredintDataModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1,textView2,textView3,textView4;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_image);
            textView1=itemView.findViewById(R.id.tv_text11);
            textView2=itemView.findViewById(R.id.tv_text1);
            textView3=itemView.findViewById(R.id.txt_money);
            textView4=itemView.findViewById(R.id.tv_text3);
        }
    }
}
