package com.icss.aasharambhoj.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.MenuActivity;
import com.icss.aasharambhoj.PdfViewActivity;
import com.icss.aasharambhoj.R;
import com.icss.aasharambhoj.model_class.AddInterface;
import com.icss.aasharambhoj.model_class.CategoryModel;
import com.icss.aasharambhoj.model_class.GetMenuDataModel;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MenuNameAdapter extends RecyclerView.Adapter<MenuNameAdapter.viewHolder> {
    List<GetMenuDataModel>menuDataModels;
    Context context;
    String data;
    String menuId;
    String pdfUrl="";
    AddInterface addInterface;
    public MenuNameAdapter(List<GetMenuDataModel> menuDataModels, Context context) {
        this.menuDataModels = menuDataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuNameAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.menu_name_items,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuNameAdapter.viewHolder holder, int position) {

     holder.textView.setText(menuDataModels.get(position).menuName);


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


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (holder.checkBox.isChecked()){
                    data = holder.textView.getText().toString();
                    menuId =menuDataModels.get(position).id;


                }
                Log.d("TAG", "===new one: "+data);
                Log.d("TAG", "===new one: "+menuId);

                SharedPreferences sharedPreferences = context.getSharedPreferences("My_Vendor", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ID", data);
                editor.putString("MENUID", menuId);
//                editor.putString("PDF_URL",pdfUrl);
                editor.apply();


            }
        });
      /*  Intent intent = new Intent(context, PdfViewActivity.class);
        intent.putExtra("data",data);
        context.startActivity(intent);*/
    }

    @Override
    public int getItemCount() {
        return menuDataModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView textView;
      //  EditText editText;
        CardView cardView;
        CheckBox checkBox;
        ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.categ);
            cardView= itemView.findViewById(R.id.card);
            checkBox= itemView.findViewById(R.id.checkbox);
           // editText= itemView.findViewById(R.id.tv_text3);




        }
    }
}
