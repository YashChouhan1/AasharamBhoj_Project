package com.icss.aasharambhoj;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.model_class.IngredientModelData;

import java.util.List;

public class DalbatiAdapter extends RecyclerView.Adapter<DalbatiAdapter.viewHolder> {

    List<IngredientModelData>dalbatiDataModels;
    Context context;

    public DalbatiAdapter(List<IngredientModelData> dalbatiDataModels, Context context) {
        this.dalbatiDataModels = dalbatiDataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public DalbatiAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.g_dal_bati_data_model,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DalbatiAdapter.viewHolder holder, int position) {
   // holder.textView.setText(dalbatiDataModels.get(position).menuDetail.get(position).ingredients.get(position).ingiName);
  //  holder.btn.setText(dalbatiDataModels.get(position).menuDetail.get(position).ingredients.get(position).finalQuantity);

     //   Log.d("TAG", "===Data: "+dalbatiDataModels.get(position).menuDetail.get(position).ingredients.get(position).ingiName);
      //  Log.d("TAG", "===Data: "+dalbatiDataModels.get(position).menuDetail.get(position).ingredients.get(position).finalQuantity);
    }

    @Override
    public int getItemCount() {
        return dalbatiDataModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView btn;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            textView =itemView.findViewById(R.id.txt1);
            btn=itemView.findViewById(R.id.btn);


        }
    }
}
