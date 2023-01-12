package com.icss.aasharambhoj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.icss.aasharambhoj.adapter.MenuNameAdapter;
import com.icss.aasharambhoj.adapter.PrefixMainAdapter;
import com.icss.aasharambhoj.model_class.NewPrefixMenuModel;
import com.icss.aasharambhoj.model_class.PrefixDishResponse;
import com.icss.aasharambhoj.model_class.SubmitMenuResponse;
import com.icss.aasharambhoj.network.APIClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrefixActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView back;
    String ID;
    Button submit;
    TextView date;
    PrefManager prefManager;
    List<NewPrefixMenuModel>DataModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefix);
        recyclerView = findViewById(R.id.recycler);
        back = findViewById(R.id.add_w);
        submit = findViewById(R.id.submit);
        date = findViewById(R.id.date);
        ID= getIntent().getStringExtra("id");

        DataModel=new ArrayList<>();
        Log.d("TAG", "===onCreate: "+ID);
        SharedPreferences sharedPreferences =  getSharedPreferences(PrefixMainAdapter.PREF_VENDOR,MODE_PRIVATE);
        Gson gson = new Gson();
        String vendor = sharedPreferences.getString("vendor",null);
        Type type = new TypeToken<ArrayList<NewPrefixMenuModel>>(){}.getType();
        DataModel = gson.fromJson(vendor,type);

        prefManager=new PrefManager(getApplicationContext());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog2();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == date) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(PrefixActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    date.setText(year+"-"+getMonth(monthOfYear+1)+ "-"+ dayOfMonth);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });

        prefixMenu();

    }
    private String getMonth(int month) {
        if (month == 1)
            return "01";
        if (month == 2)
            return "02";
        if (month == 3)
            return "03";
        if (month == 4)
            return "04";
        if (month == 5)
            return "05";
        if (month == 6)
            return "06";
        if (month == 7)
            return "07";
        if (month == 8)
            return "08";
        if (month == 9)
            return "09";
        if (month == 10)
            return "10";
        if (month == 11)
            return "11";
        if (month == 12)
            return "12";

        //default should never happen
        return "01";

    }
    public void prefixMenu(){
        APIClient.getAPIService().get_PrefixMenu().enqueue(new Callback<PrefixDishResponse>() {
            @Override
            public void onResponse(Call<PrefixDishResponse> call, Response<PrefixDishResponse> response) {
                if(response.isSuccessful()){
                    if (response.body().response) {

                        recyclerView.setAdapter(new PrefixMainAdapter(response.body().data,PrefixActivity.this));
                        recyclerView.setLayoutManager(new LinearLayoutManager(PrefixActivity.this, RecyclerView.VERTICAL, false));


                    }else{
                        Toast.makeText(PrefixActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(PrefixActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PrefixDishResponse> call, Throwable t) {

            }
        });
    }

    private void showBottomSheetDialog2(){
        final Dialog dialog = new Dialog(PrefixActivity.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.bottom_sheet_dialog_wallet);
        // Set dialog title
        dialog.setTitle("Custom Dialog");

        // set values for custom dialog components - text, image and button
        EditText text = (EditText) dialog.findViewById(R.id.txt1);
        TextView text1 = (TextView) dialog.findViewById(R.id.txt);
        Button submit = (Button) dialog.findViewById(R.id.subm);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dta = text.getText().toString();
                addMenu(dta);

                Log.d("TAG", "===NewonClick: "+DataModel.size());

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public  void addMenu(String menuName){

        HashMap<String, String> data = prefManager.getUserDetails();
        String userId =data.get("id");
        String Date = date.getText().toString();
        Log.d("TAG", "===userId: "+userId);
        Log.d("TAG", "===userId: "+Date);

        Gson gson = new GsonBuilder().create();
        JsonArray jsonArray = gson.toJsonTree(DataModel).getAsJsonArray();
        String userData = jsonArray.toString();

        Log.d("TAG", "===onClick: "+userData);
        ProgressDialog progressDialog = new ProgressDialog(PrefixActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        APIClient.getAPIService().AddMenu(menuName,userId,Date,ID,userData).enqueue(new Callback<SubmitMenuResponse>() {
            @Override
            public void onResponse(Call<SubmitMenuResponse> call, Response<SubmitMenuResponse> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    if (response.body().response){


                        Toast.makeText(PrefixActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(PrefixActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(PrefixActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubmitMenuResponse> call, Throwable t) {

            }
        });
    }
}