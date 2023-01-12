package com.icss.aasharambhoj.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.MenuAdapter;
import com.icss.aasharambhoj.R;
import com.icss.aasharambhoj.model_class.AddInterface;
import com.icss.aasharambhoj.model_class.DishByCategoryModel;
import com.icss.aasharambhoj.model_class.NewMenuModel;

import java.util.ArrayList;
import java.util.List;


public class BhkSubCatAdapter extends RecyclerView.Adapter<BhkSubCatAdapter.MyViewHolder> {
    AddInterface addInterface;
    Context mContext;
    List<NewMenuModel> DataModel;
    List<DishByCategoryModel> list;
    ArrayList<DishByCategoryModel> mainlist = new ArrayList<>();
    String id, serving;
    int count = 1;

    public BhkSubCatAdapter(Context mContext, List<DishByCategoryModel> list, AddInterface addInterface) {
        this.list = list;
        mainlist = (ArrayList<DishByCategoryModel>) list;
        this.mContext = mContext;
        this.addInterface = addInterface;
    }

    @NonNull
    @Override
    public BhkSubCatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bhk_sub_cat, parent, false);
        return new MyViewHolder(view);
    }

    public void filterList(ArrayList<DishByCategoryModel> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        list = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BhkSubCatAdapter.MyViewHolder holder, int position) {

        DataModel = new ArrayList<>();
        String data = list.get(position).name;
        Log.d("TAG", "===data: " + data);

        holder.name.setText(data);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = holder.name.getText().toString();
                id = String.valueOf(list.get(position).id);
                serving = String.valueOf(list.get(position).servingType);

                Log.d("TAG", "===new one000: " + data);
                Log.d("TAG", "===new id: " + id);
                if (holder.qty.getText().toString().equals("")) {
                    Toast.makeText(mContext, "Please Input Quantity", Toast.LENGTH_SHORT).show();
                } else {


                    holder.value.setVisibility(View.VISIBLE);
                    holder.qty.setVisibility(View.GONE);
                    holder.add.setVisibility(View.GONE);
                    holder.value.setText(holder.qty.getText().toString());

                    addInterface.click(id, holder.qty.getText().toString(), serving);
                    shoUtensils(id);

                }/*else{
                    Toast.makeText(mContext, "Please Firstly Select Dish", Toast.LENGTH_SHORT).show();

                    Log.d("TAG", "Please Firstly Select Dish");
                }*/
            }


        });
        holder.value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* SharedPreferences sharedPreferences =  mContext.getSharedPreferences(MenuAdapter.PREF_VENDOR,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();*/
                holder.qty.setVisibility(View.VISIBLE);
                holder.add.setVisibility(View.VISIBLE);
                holder.value.setVisibility(View.GONE);
                //holder.checkBox.setChecked(false);
            }
        });






/*
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (holder.checkBox.isChecked()){
                    holder.qty.requestFocus();
                  String  data = holder.name.getText().toString();
                 id = String.valueOf(list.get(position).id);
                 serving = String.valueOf(list.get(position).servingType);

                    Log.d("TAG", "===new one000: "+data);
                    Log.d("TAG", "===new id: "+id);
                }
               // Log.d("TAG", "===new one: "+data);
               // Log.d("TAG", "===new one: "+menuId);

                SharedPreferences sharedPreferences = mContext.getSharedPreferences("My_Vendor", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("SERV", serving);
                editor.putString("MENUIDD", id);
                editor.apply();

            }
        });
*/


    }


//    public Filter getFilterData() {
//        return exampleFilter;
//    }

















//
//    private Filter exampleFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<DishByCategoryModel> filteredList = new ArrayList<>();
//            Log.d("exampleFilter", "exampleFilter: "+constraint.length() +"***********"+mainlist.size());
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(list);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//                Log.d("TAG", "performFiltering: " + list.size() + " " + filterPattern);
//
//                for (DishByCategoryModel item : list) {
//                    Log.d("TAG", "performFiltering: " + item.name + " " + filterPattern);
//
//                    if (item.name.toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            list.clear();
//            list.addAll((List) results.values);
//            notifyDataSetChanged();
//
//
//
//
//        }
//    };







    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, add, value;
        CheckBox checkBox;
        EditText qty;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.categ);
            this.checkBox = itemView.findViewById(R.id.checkbox);
            this.qty = itemView.findViewById(R.id.qty);
            this.add = itemView.findViewById(R.id.add);
            this.value = itemView.findViewById(R.id.value);

        }
    }


    private void shoUtensils(String id) {
        Dialog dialogView = new Dialog(mContext);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setContentView(R.layout.dialog_utensils);
        Window window = dialogView.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        TextView minus = dialogView.findViewById(R.id.minus);
        TextView quantity = dialogView.findViewById(R.id.quantity);
        TextView plus = dialogView.findViewById(R.id.plus);
        TextView submit = dialogView.findViewById(R.id.submit);


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count != 1) {
                    count--;
                    quantity.setText("" + count);
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                quantity.setText("" + count);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogView != null && dialogView.isShowing()) {
                    dialogView.dismiss();

                }

            }
        });

        dialogView.setCanceledOnTouchOutside(true);
        dialogView.show();
    }


    public void filterData(String text) {
        // creating a new array list to filter our data.
        ArrayList<DishByCategoryModel> filteredlist = new ArrayList<DishByCategoryModel>();
        if(text.isEmpty())
        {
            filterList(mainlist);
            return;
        }

        // running a for loop to compare elements.
        for (DishByCategoryModel item : list) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }else {
                filterList(mainlist);
            }
        }
        if (filteredlist.isEmpty()) {
            filterList(mainlist);
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
//            Toast.makeText(mContext, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            filterList(filteredlist);
        }
    }


}
