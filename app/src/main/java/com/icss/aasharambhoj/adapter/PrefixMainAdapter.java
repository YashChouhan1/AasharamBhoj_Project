package com.icss.aasharambhoj.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.icss.aasharambhoj.MenuActivity;
import com.icss.aasharambhoj.PrefixActivity;
import com.icss.aasharambhoj.R;
import com.icss.aasharambhoj.model_class.AddInterface;
import com.icss.aasharambhoj.model_class.CategoryModel;
import com.icss.aasharambhoj.model_class.NewMenuModel;
import com.icss.aasharambhoj.model_class.NewPrefixMenuModel;
import com.icss.aasharambhoj.model_class.PrefixDataModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class PrefixMainAdapter extends RecyclerView.Adapter<PrefixMainAdapter.viewHolder> {
    List<PrefixDataModel>menuDataModels;
    Context context;
    public  static String PREF_VENDOR ="123";
    List<NewPrefixMenuModel>DataModel;
    AddInterface addInterface;
    public PrefixMainAdapter(List<PrefixDataModel> menuDataModels, Context context) {
        this.menuDataModels = menuDataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public PrefixMainAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.prefix_items,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrefixMainAdapter.viewHolder holder, int position) {

        DataModel=new ArrayList<>();
     holder.textView.setText(menuDataModels.get(position).menuName);


       holder.recyclerView.setAdapter(new PrifixDishesAdapter(menuDataModels.get(position).dishes, context, new AddInterface() {
           @Override
           public void click(String dish_id, String people, String serving) {
               Log.d("TAG", "===disID: "+dish_id);
               Log.d("TAG", "===people: "+people);
               Log.d("TAG", "===serving: "+serving);
               DataModel.add(new NewPrefixMenuModel(dish_id,people,serving));
               SharedPreferences sharedPreferences =  context.getSharedPreferences(PREF_VENDOR,MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPreferences.edit();
               Gson gson = new Gson();
               String vendor = gson.toJson(DataModel);
               editor.putString("vendor",vendor);
               editor.apply();

           }
       }));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

    /*holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String id = String.valueOf(menuDataModels.get(position).id);
            Log.d("TAG", "====NewId: "+id);
            Intent intent= new Intent(context, MenuActivity.class);
            intent.putExtra("id",id);
            context.startActivity(intent);

        }
    });*/


    }

    @Override
    public int getItemCount() {
        return menuDataModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView textView;
      //  EditText editText;
        CardView cardView;
        RecyclerView recyclerView;
        ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.menu_name);
            cardView= itemView.findViewById(R.id.card);
            recyclerView= itemView.findViewById(R.id.recy);

           // editText= itemView.findViewById(R.id.tv_text3);




        }
    }
}
