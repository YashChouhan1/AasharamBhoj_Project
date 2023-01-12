package com.icss.aasharambhoj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DishAdapter  extends RecyclerView.Adapter<DishAdapter.viewHolder> {
    List<DishDataModel>dishDataModels;
    Context context;

    public DishAdapter(List<DishDataModel> dishDataModels, Context context) {
        this.dishDataModels = dishDataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cher_dish_data_model,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.dish.setText(dishDataModels.get(position).getDish());
        holder.kg.setText(dishDataModels.get(position).getKg());
        holder.people.setText(dishDataModels.get(position).getPeople());

    }

    @Override
    public int getItemCount() {
        return dishDataModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView dish;
        TextView kg;
        TextView people;
            public viewHolder(@NonNull View itemView) {
            super(itemView);

             dish= itemView.findViewById(R.id.dish);
             kg=itemView.findViewById(R.id.kg);
                     people=itemView.findViewById(R.id.add_people);
        }
    }
}
