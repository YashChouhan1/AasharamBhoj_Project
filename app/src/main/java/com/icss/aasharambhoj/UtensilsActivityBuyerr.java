package com.icss.aasharambhoj;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UtensilsActivityBuyerr extends AppCompatActivity {
    RecyclerView recyclerView;
    List<UtensilsDataModelBuyer>utensilsDataModelBuyers;
    TextView date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utensils_activity_buyer);
        recyclerView= findViewById(R.id.recycler_1);
        date=findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == date) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(UtensilsActivityBuyerr.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    date.setText(dayOfMonth + " - " + (monthOfYear + 1) + " - " + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        utensilsDataModelBuyers=new ArrayList<>();
        utensilsDataModelBuyers.add(new UtensilsDataModelBuyer("spoon"));
        utensilsDataModelBuyers.add(new UtensilsDataModelBuyer("tongs"));
        utensilsDataModelBuyers.add(new UtensilsDataModelBuyer("Bowl"));


        UtensilsAdapterBuyer untensilsAdapter= new UtensilsAdapterBuyer(utensilsDataModelBuyers, UtensilsActivityBuyerr.this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(untensilsAdapter);

    }
}
