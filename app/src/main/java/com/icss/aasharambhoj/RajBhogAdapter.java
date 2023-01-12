package com.icss.aasharambhoj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RajBhogAdapter extends RecyclerView.Adapter<RajBhogAdapter.viewHolder> {

    List<RajBhogDataModel>rajBhogDataModels;
    Context context;

    public RajBhogAdapter(List<RajBhogDataModel> rajBhogDataModels, Context context) {
        this.rajBhogDataModels = rajBhogDataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RajBhogAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.g_dal_bati_data_model,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RajBhogAdapter.viewHolder holder, int position) {
    holder.textView.setText(rajBhogDataModels.get(position).getIngredint());
    holder.btn.setText(rajBhogDataModels.get(position).getKg());
    }

    @Override
    public int getItemCount() {
        return rajBhogDataModels.size();
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
