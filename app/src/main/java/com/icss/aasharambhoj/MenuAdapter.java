package com.icss.aasharambhoj;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.adapter.BhkSubCatAdapter;
import com.icss.aasharambhoj.model_class.AddInterface;
import com.icss.aasharambhoj.model_class.AddNewData;
import com.icss.aasharambhoj.model_class.DishByCategoryModel;
import com.icss.aasharambhoj.model_class.DishByCategoryResponse;
import com.icss.aasharambhoj.model_class.GetDishesNew;
import com.icss.aasharambhoj.network.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.viewHolder> implements AddInterface {
    List<GetDishesNew> menuDataModels;
    Context context;
    AddInterface addInterface;
    AddNewData addNewData;
    ArrayList<String> spinnerList;
    ArrayList<String> spinnerId, stringArrayList;
    List<DishByCategoryModel> dataList = new ArrayList<>();
    //    List<NewMenuModel> DataModel;
    public static String PREF_VENDOR = "123";
    String catID;
    int status = 0;
    BhkSubCatAdapter bhkSubCatAdapter;



    public MenuAdapter(List<GetDishesNew> menuDataModels, Context context, AddNewData addNewData) {
        this.menuDataModels = menuDataModels;
        this.context = context;
        this.addNewData = addNewData;
        // this.addInterface = addInterface;
        this.stringArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MenuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.menu_data_model, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.viewHolder holder, int position) {


        holder.textView.setText(menuDataModels.get(position).name);


      /*  holder.visiable_txt_btn.setOnClickListener(view -> {
            holder.visiable_txt_btn.setVisibility(View.GONE);
            holder.constraintLayout.setVisibility(View.VISIBLE);
        });*/

 /*       holder.gone_txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.edit_btn.setVisibility(View.VISIBLE);
                holder.editText.setVisibility(View.VISIBLE);
                holder.editText.requestFocus();
                holder.constraintLayout.setVisibility(View.VISIBLE);
                holder.gone_txt_btn.setVisibility(View.GONE);
            }
        });*/
        holder.edit_btn.setOnClickListener(view -> {

            SharedPreferences sharedPreferences = context.getSharedPreferences("My_Vendor", MODE_PRIVATE);
            String vendorId = sharedPreferences.getString("MENUIDD", null);

             /*(vendorId == null) {
                Toast.makeText(context, "Please Firstly Select Dish", Toast.LENGTH_SHORT).show();
            } else*/
            String Add_quantity = holder.editText.getText().toString();
            holder.gone_txt_btn.setText(Add_quantity);
            holder.constraintLayout.setVisibility(View.GONE);
            holder.gone_txt_btn.setVisibility(View.VISIBLE);

            String dish_id = String.valueOf(menuDataModels.get(position).id);
            String quantity = holder.gone_txt_btn.getText().toString();
            String serving_type = holder.serving.getText().toString();


            Log.d("------", "====000011: " + menuDataModels.get(position).getCat_id());
            Log.d("TAG", "====id: " + dish_id);
            Log.d("TAG", "====id: " + quantity);
            Log.d("TAG", "====id: " + serving_type);
            Log.d("TAG", "====vendorId: " + vendorId);
            Log.d("TAG", "====vendorId: " + sharedPreferences.getString("SERV", null));

            Log.d("TAG", "===datamodel: " + dataList.size());
            addInterface.click(menuDataModels.get(position).getCat_id(), quantity, serving_type);

        });



        holder.img_dwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.img_up.setVisibility(View.VISIBLE);
                holder.lyt.setVisibility(View.VISIBLE);
                holder.img_dwn.setVisibility(View.GONE);

                String dish_id = String.valueOf(menuDataModels.get(position).id);

                Log.d("TAG", "====id: " + dish_id);
                if (!dish_id.equals(null)) {
                    APIClient.getAPIService().getDishByType(dish_id).enqueue(new Callback<DishByCategoryResponse>() {
                        @Override
                        public void onResponse(Call<DishByCategoryResponse> call, Response<DishByCategoryResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.body().response) {
                                    dataList = response.body().data;
                                    Log.d("TAG", "onResponse: " + dataList.get(0).name);
                                    holder.recyclerView.setVisibility(View.VISIBLE);
                                    bhkSubCatAdapter  = new BhkSubCatAdapter(context, dataList, MenuAdapter.this::click);
                                    holder.recyclerView.setAdapter(bhkSubCatAdapter);
                                    holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
                                    holder.recyclerView.setNestedScrollingEnabled(false);
                                    holder.serving.setText(response.body().data.get(0).servingType);

                                    RecyclerView.OnItemTouchListener mScrollTouchListener = new RecyclerView.OnItemTouchListener() {


                                        @Override
                                        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                                        }

                                        @Override
                                        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                                            int action = e.getAction();
                                            switch (action) {
                                                case MotionEvent.ACTION_MOVE:
                                                    rv.getParent().requestDisallowInterceptTouchEvent(true);
                                                    break;
                                            }
                                            return false;
                                        }

                                        @Override
                                        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                                        }
                                    };

                                    holder.recyclerView.addOnItemTouchListener(mScrollTouchListener);





                                    // status = 1;
/*

                                spinnerList = new ArrayList<>();
                                spinnerId = new ArrayList<>();

                                for (int t = 0; t < response.body().data.size(); t++) {
                                    spinnerList.add(response.body().data.get(t).name);
                                    spinnerId.add(String.valueOf(response.body().data.get(t).id));
                                    Log.d("------", menuDataModels.get(position).name+"--"+menuDataModels.get(position).id + "---" + response.body().data.get(t).name + "--" + response.body().data.get(t).id);
                                }
                                demoModelList.add(new DemoModel(String.valueOf(menuDataModels.get(position).id), spinnerId));
                                ArrayAdapter<String> adap = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, spinnerList);
                                holder.category.setAdapter(adap);
                                holder.category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int posi, long l) {
                                        // int posit =i;

                                        for (int i = 0; i < demoModelList.size(); i++) {
                                            if (demoModelList.get(i).getId() == String.valueOf(menuDataModels.get(position).id)) {
                                                stringArrayList.clear();
                                                stringArrayList.addAll(demoModelList.get(i).getData());
                                                Log.d("----", stringArrayList.size() + "===posi: " + posi);
                                                catID = stringArrayList.get(posi).toString();
                                                menuDataModels.get(position).setCat_id(stringArrayList.get(posi));
                                                Log.d("-----CI", menuDataModels.get(position).id + "===catidd: " + stringArrayList.get(posi));
                                            }
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
*/

                                } else {
                                    // Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<DishByCategoryResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });

        holder.img_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.img_up.setVisibility(View.GONE);
                holder.lyt.setVisibility(View.GONE);
                holder.img_dwn.setVisibility(View.VISIBLE);
            }
        });

        holder.searchDish.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("TAG", "onQueryTextChange: " + newText);
                bhkSubCatAdapter.filterData(newText);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuDataModels.size();
    }

    @Override
    public void click(String dish_id, String people, String serving) {
        Log.d("TAG", "===new1: " + dish_id);
        Log.d("TAG", "===new2: " + people);
        Log.d("TAG", "===new3: " + serving);

        addNewData.click(dish_id, people, serving);
        //DataModel.add(new NewMenuModel(dish_id, peop
        // le, serving));
       /* SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_VENDOR, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String vendor = gson.toJson(DataModel);
        editor.putString("vendor", vendor);
        editor.apply();
        Log.d("TAG", "===datamodel1: " + DataModel.size());*/

    }


    public class viewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        EditText editText;
        CardView cardView;
        TextView edit_btn;
        TextView gone_txt_btn, serving;
        ImageView visiable_txt_btn, img_up, img_dwn;
        ConstraintLayout constraintLayout;
        Spinner category;
        RecyclerView recyclerView;
        LinearLayout lyt;
        SearchView searchDish;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_text11);
            serving = itemView.findViewById(R.id.tv_text12);
            editText = itemView.findViewById(R.id.tv_text3);
            cardView = itemView.findViewById(R.id.card1);
            img_dwn = itemView.findViewById(R.id.img);
            img_up = itemView.findViewById(R.id.img2);
            lyt = itemView.findViewById(R.id.lyt);

            constraintLayout = itemView.findViewById(R.id.gone_constraint);

            edit_btn = itemView.findViewById(R.id.add_btn);

            visiable_txt_btn = itemView.findViewById(R.id.add_img_btn);

            gone_txt_btn = itemView.findViewById(R.id.add_btn_txt);
            category = itemView.findViewById(R.id.sp_category);
            recyclerView = itemView.findViewById(R.id.recycler);

            this.searchDish = itemView.findViewById(R.id.searchDish);


        }
    }


    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<GetDishesNew> filteredlist = new ArrayList<GetDishesNew>();

        // running a for loop to compare elements.
        for (GetDishesNew item : menuDataModels) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(context, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            this.menuDataModels = filteredlist;
            notifyDataSetChanged();
            // at last we are passing that filtered
            // list to our adapter class.

        }
    }
}
