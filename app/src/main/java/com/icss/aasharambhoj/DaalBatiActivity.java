package com.icss.aasharambhoj;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.model_class.IngrModelMainData;
import com.icss.aasharambhoj.network.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaalBatiActivity  extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DalbatiDataModel>dalbatiDataModels;
    EditText date;
    TextView submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_daal_bati_ingredint);
        date=findViewById(R.id.ed_date);
        submit=findViewById(R.id.submit);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == date) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(DaalBatiActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    date.setText(year+"-"+(monthOfYear + 1)+"-"+dayOfMonth);


                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });



        recyclerView = findViewById(R.id.recycler);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIngredients();
            }
        });
       /* GridLayoutManager gridLayoutManager= new GridLayoutManager(this,1);
        dalbatiDataModels=new ArrayList<>();
        dalbatiDataModels.add(new DalbatiDataModel("wheat flour","10kg"));
        dalbatiDataModels.add(new DalbatiDataModel("chana dal","10kg"));
        dalbatiDataModels.add(new DalbatiDataModel("garam masala powde","10kg"));
        dalbatiDataModels.add(new DalbatiDataModel("turmeric","10kg"));
        dalbatiDataModels.add(new DalbatiDataModel("green moong dal","10kg"));
        dalbatiDataModels.add(new DalbatiDataModel(" ghee","10kg"));
        dalbatiDataModels.add(new DalbatiDataModel("salt","10kg"));
        dalbatiDataModels.add(new DalbatiDataModel(" toor daal","10kg"));
        dalbatiDataModels.add(new DalbatiDataModel("urad dal","10kg"));
        dalbatiDataModels.add(new DalbatiDataModel("water","10kg"));
        dalbatiDataModels.add(new DalbatiDataModel("red chilli powder","10kg"));
        DalbatiAdapter dalbatiAdapter= new DalbatiAdapter(dalbatiDataModels,DaalBatiActivity.this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(dalbatiAdapter);*/


    }
    public void getIngredients(){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String Date = date.getText().toString();

        APIClient.getAPIService().getIngredi(Date).enqueue(new Callback<IngrModelMainData>() {
            @Override
            public void onResponse(Call<IngrModelMainData> call, Response<IngrModelMainData> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    if (response.body().response){
                        Toast.makeText(DaalBatiActivity.this, "Data Available", Toast.LENGTH_SHORT).show();
                        recyclerView.setAdapter(new DalbatiAdapter(response.body().ingredientList,DaalBatiActivity.this));
                        recyclerView.setLayoutManager(new LinearLayoutManager(DaalBatiActivity.this, RecyclerView.VERTICAL, false));


                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(DaalBatiActivity.this, "Data Not Available", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(DaalBatiActivity.this, "Data Not Available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IngrModelMainData> call, Throwable t) {

            }
        });
    }
}
