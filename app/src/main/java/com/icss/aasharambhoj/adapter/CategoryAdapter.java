package com.icss.aasharambhoj.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.Chili;
import com.icss.aasharambhoj.DaalBatiActivity;
import com.icss.aasharambhoj.MenuActivity;
import com.icss.aasharambhoj.R;
import com.icss.aasharambhoj.RojBhogActivity;
import com.icss.aasharambhoj.SadhavikActivity;
import com.icss.aasharambhoj.model_class.AddInterface;
import com.icss.aasharambhoj.model_class.CategoryModel;
import com.icss.aasharambhoj.model_class.MenuAddModelData;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    List<CategoryModel>menuDataModels;
    Context context;
    AddInterface addInterface;
    public CategoryAdapter(List<CategoryModel> menuDataModels, Context context) {
        this.menuDataModels = menuDataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.popup_items,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, int position) {

     holder.textView.setText(menuDataModels.get(position).name);

    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String id = String.valueOf(menuDataModels.get(position).id);
            Log.d("TAG", "====NewId: "+id);
            Intent intent= new Intent(context, MenuActivity.class);
            intent.putExtra("id",id);
            context.startActivity(intent);

        }
    });


    }

    @Override
    public int getItemCount() {
        return menuDataModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView textView;
      //  EditText editText;
        CardView cardView;
        ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.categ);
            cardView= itemView.findViewById(R.id.card);
           // editText= itemView.findViewById(R.id.tv_text3);




        }
    }
}
