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

public class UtensilsAdapterBuyer extends RecyclerView.Adapter<UtensilsAdapterBuyer.viewHolder> {
    List<UtensilsDataModelBuyer> utensilsDataModelBuyers;
    Context context;

    public UtensilsAdapterBuyer(List<UtensilsDataModelBuyer> utensilsDataModelBuyers, Context context) {
        this.utensilsDataModelBuyers = utensilsDataModelBuyers;
        this.context = context;
    }

    @NonNull
    @Override
    public UtensilsAdapterBuyer.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.utensils_datamodel_buyer,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UtensilsAdapterBuyer.viewHolder holder, int position) {

        holder.textView.setText(utensilsDataModelBuyers.get(position).getIngredints());


    }

    @Override
    public int getItemCount() {
        return utensilsDataModelBuyers.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView2;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
             img = itemView.findViewById(R.id.iv_image);
             textView = itemView.findViewById(R.id.tv_text11);
             textView2 = itemView.findViewById(R.id.tv_text3);

        }
    }
}
