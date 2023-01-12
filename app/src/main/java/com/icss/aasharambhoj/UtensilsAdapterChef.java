package com.icss.aasharambhoj;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.model_class.AddUtensilInterface;
import com.icss.aasharambhoj.model_class.AddUtensilsDataModel;

import java.util.List;

public class UtensilsAdapterChef extends RecyclerView.Adapter<UtensilsAdapterChef.viewHolder> {
    List<AddUtensilsDataModel> utensilsdatamodels;
    Context context;
    AddUtensilInterface addUtensilInterface;
    public UtensilsAdapterChef(List<AddUtensilsDataModel> todayDataModels, Context context,AddUtensilInterface addUtensilInterface) {
        this.utensilsdatamodels = todayDataModels;
        this.context = context;
        this.addUtensilInterface = addUtensilInterface;
    }

    @NonNull
    @Override
    public UtensilsAdapterChef.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.utensils_datamodeol,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UtensilsAdapterChef.viewHolder holder, int position) {

        holder.textView.setText(utensilsdatamodels.get(position).name);



        holder.visiable_txt_btn.setOnClickListener(view -> {
            holder.visiable_txt_btn.setVisibility(View.GONE);
            holder.constraintLayout.setVisibility(View.VISIBLE);
        });


        holder.edit_btn.setOnClickListener(view -> {


            String  Add_quantity= holder.editText.getText().toString();
            holder.gone_txt_btn.setText(Add_quantity);
            holder.constraintLayout.setVisibility(View.GONE);
            holder.gone_txt_btn.setVisibility(View.VISIBLE);

            String utensil_id = String.valueOf(utensilsdatamodels.get(position).id);
            String quantity = holder.editText.getText().toString();

            Log.d("TAG", "====id: "+utensil_id);
            Log.d("TAG", "====id: "+quantity);
            addUtensilInterface.click(utensil_id,quantity);

        });




    }

    @Override
    public int getItemCount() {
        return utensilsdatamodels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        EditText editText;
        CardView cardView;
        TextView edit_btn;
        TextView gone_txt_btn;
        ImageView visiable_txt_btn;
        ConstraintLayout constraintLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            textView= itemView.findViewById(R.id.tv_text11);
            editText= itemView.findViewById(R.id.tv_text3);
            cardView= itemView.findViewById(R.id.card1);

            constraintLayout=itemView.findViewById(R.id.gone_constraint);

            edit_btn= itemView.findViewById(R.id.add_btn);

            visiable_txt_btn=itemView.findViewById(R.id.add_img_btn);

            gone_txt_btn=itemView.findViewById(R.id.add_btn_txt);

        }
    }
}
