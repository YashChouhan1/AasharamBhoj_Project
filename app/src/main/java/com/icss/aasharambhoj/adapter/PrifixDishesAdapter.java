package com.icss.aasharambhoj.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.Chili;
import com.icss.aasharambhoj.DaalBatiActivity;
import com.icss.aasharambhoj.R;
import com.icss.aasharambhoj.RojBhogActivity;
import com.icss.aasharambhoj.SadhavikActivity;
import com.icss.aasharambhoj.model_class.AddInterface;
import com.icss.aasharambhoj.model_class.DishByCategoryResponse;
import com.icss.aasharambhoj.model_class.GetDishesNew;
import com.icss.aasharambhoj.model_class.PrefixDishModel;
import com.icss.aasharambhoj.network.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrifixDishesAdapter extends RecyclerView.Adapter<PrifixDishesAdapter.viewHolder> {
    List<PrefixDishModel>menuDataModels;
    Context context;
    AddInterface addInterface;
    ArrayList<String>  spinnerList;
    ArrayList<String>  spinnerId;
    String catID;
    public PrifixDishesAdapter(List<PrefixDishModel> menuDataModels, Context context, AddInterface addInterface) {
        this.menuDataModels = menuDataModels;
        this.context = context;
        this.addInterface = addInterface;
    }

    @NonNull
    @Override
    public PrifixDishesAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.prefix_data_model,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrifixDishesAdapter.viewHolder holder, int position) {

     holder.textView.setText(menuDataModels.get(position).name);
     holder.serving.setText(menuDataModels.get(position).servingType);


/*
     holder.editText.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String dish_id = String.valueOf(menuDataModels.get(position).id);
             String people = holder.gone_txt_btn.getText().toString();

             Log.d("TAG", "====id: "+dish_id);
             Log.d("TAG", "====id: "+people);
             addInterface.click(dish_id,people);
         }
     });*/

       holder.visiable_txt_btn.setOnClickListener(view -> {
         holder.visiable_txt_btn.setVisibility(View.GONE);
         holder.constraintLayout.setVisibility(View.VISIBLE);
       });


     holder.edit_btn.setOnClickListener(view -> {


         String  Add_quantity= holder.editText.getText().toString();
         holder.gone_txt_btn.setText(Add_quantity);
         holder.constraintLayout.setVisibility(View.GONE);
         holder.gone_txt_btn.setVisibility(View.VISIBLE);

         holder.gone_txt_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 holder.edit_btn.setVisibility(View.VISIBLE);
                 holder.editText.setVisibility(View.VISIBLE);
                 holder.constraintLayout.setVisibility(View.VISIBLE);
                 holder.gone_txt_btn.setVisibility(View.GONE);
             }
         });
         String dish_id = String.valueOf(menuDataModels.get(position).id);
         String quantity = holder.gone_txt_btn.getText().toString();
         String serving_type = holder.serving.getText().toString();

         Log.d("TAG", "====id: "+dish_id);
         Log.d("TAG", "====id: "+quantity);
         Log.d("TAG", "====id: "+serving_type);
         addInterface.click(dish_id,quantity,serving_type);

     });

        String dish_id = String.valueOf(menuDataModels.get(position).id);

        Log.d("TAG", "====id: "+dish_id);


/*
        APIClient.getAPIService().getDish(dish_id).enqueue(new Callback<DishByCategoryResponse>() {
            @Override
            public void onResponse(Call<DishByCategoryResponse> call, Response<DishByCategoryResponse> response) {
                if(response.isSuccessful()){
                    if (response.body().response) {
                        spinnerList = new ArrayList<>();
                        spinnerId = new ArrayList<>();
                        holder.serving.setText(response.body().data.get(0).servingType);
                        // spinnerList.add("Select Dish");
                        for (int t = 0; t < response.body().data.size(); t++) {
                            spinnerList.add(response.body().data.get(t).name);
                            spinnerId.add(String.valueOf(response.body().data.get(t).id));
                        }
                        ArrayAdapter<String> adap = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, spinnerList);
                        holder.category.setAdapter(adap);

                        holder.category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                int id = i;
                                catID= spinnerId.get(id).toString();
                                Log.d("TAG", "===onItemSelected: "+catID);
                                Log.d("TAG", "===onItemSelected0: "+id);
                                Log.d("TAG", "===onItemSelected1: "+spinnerId.size());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });


                    }else{
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DishByCategoryResponse> call, Throwable t) {

            }
        });
*/



        /*spinnerList = new ArrayList<>();
        spinnerList.add("Select Dish");
        spinnerList.add("Hindu");
        spinnerList.add("Islam");
        spinnerList.add("Sikh");

        ArrayAdapter<String> adap = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, spinnerList);
        holder.category.setAdapter(adap);
*/

        if (position == 0) {

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DaalBatiActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        if(position==1)
        {
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (context, RojBhogActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        if(position==2)
        {
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (context, Chili.class);
                    context.startActivity(intent);
                }
            });
        }
        if(position==3)
        {
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (context, SadhavikActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idd= String.valueOf(menuDataModels.get(position).id);

                Log.d("TAG", "===onClick: "+idd);
               /* APIClient.getAPIService().getDish(idd).enqueue(new Callback<DishByCategoryResponse>() {
                    @Override
                    public void onResponse(Call<DishByCategoryResponse> call, Response<DishByCategoryResponse> response) {
                        if(response.isSuccessful()){
                            if (response.body().response) {

                               // spinnerList.add("Select Dish");
                                for (int t = 0; t < response.body().data.size(); t++) {
                                    spinnerList.add(response.body().data.get(t).name);
                                }
                                    ArrayAdapter<String> adap = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, spinnerList);
                                holder.category.setAdapter(adap);

                            }else{
                                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                            }
                            }else{
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }

                            }

                    @Override
                    public void onFailure(Call<DishByCategoryResponse> call, Throwable t) {

                    }
                });*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuDataModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        EditText editText;
        CardView cardView;
        TextView edit_btn;
        TextView gone_txt_btn,serving;
        ImageView visiable_txt_btn;
        ConstraintLayout constraintLayout;
        Spinner category;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.tv_text11);
            serving= itemView.findViewById(R.id.tv_text12);
            editText= itemView.findViewById(R.id.tv_text3);
            cardView= itemView.findViewById(R.id.card1);

          constraintLayout=itemView.findViewById(R.id.gone_constraint);

            edit_btn= itemView.findViewById(R.id.add_btn);

            visiable_txt_btn=itemView.findViewById(R.id.add_img_btn);

            gone_txt_btn=itemView.findViewById(R.id.add_btn_txt);
            category=itemView.findViewById(R.id.sp_category);




        }
    }
}
