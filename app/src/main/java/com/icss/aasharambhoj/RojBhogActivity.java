package com.icss.aasharambhoj;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RojBhogActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<RajBhogDataModel>rajBhogDataModels;
    EditText date;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_raj_bhog_ingredint);
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


                    DatePickerDialog datePickerDialog = new DatePickerDialog(RojBhogActivity.this,
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



        recyclerView = findViewById(R.id.recycler);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,1);
        rajBhogDataModels=new ArrayList<>();
        rajBhogDataModels.add(new RajBhogDataModel(" paneer","10kg"));
        rajBhogDataModels.add(new RajBhogDataModel("almonds","10kg"));
        rajBhogDataModels.add(new RajBhogDataModel(" powdered green cardamom","10kg"));
        rajBhogDataModels.add(new RajBhogDataModel("pistachios","10kg"));
        rajBhogDataModels.add(new RajBhogDataModel("sugar","10kg"));
        rajBhogDataModels.add(new RajBhogDataModel("milk","10kg"));
        rajBhogDataModels.add(new RajBhogDataModel("water","10kg"));

        RajBhogAdapter rajBhogAdapter= new RajBhogAdapter(rajBhogDataModels, RojBhogActivity.this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(rajBhogAdapter);


    }
}
