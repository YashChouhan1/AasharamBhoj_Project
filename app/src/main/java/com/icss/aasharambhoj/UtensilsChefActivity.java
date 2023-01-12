package com.icss.aasharambhoj;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.icss.aasharambhoj.model_class.AddChefUtensilsresponse;
import com.icss.aasharambhoj.model_class.AddMenuResponse;
import com.icss.aasharambhoj.model_class.AddUtensilInterface;
import com.icss.aasharambhoj.model_class.NewMenuModel;
import com.icss.aasharambhoj.model_class.NewUtensilModel;
import com.icss.aasharambhoj.model_class.SubmitMenuResponse;
import com.icss.aasharambhoj.network.APIClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtensilsChefActivity extends AppCompatActivity implements AddUtensilInterface {
    RecyclerView recyclerView;
    List<UtensilsDatamodelChef>utensilsdatamodelchef;
    EditText date;
    ImageView back;
    TextView txt_submit;
    List<NewUtensilModel>DataModel;
    PrefManager prefManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utensils_activity_chef);
        recyclerView= findViewById(R.id.recycler_1);
        date=findViewById(R.id.date);
        back = findViewById(R.id.iv_image1);
        txt_submit= findViewById(R.id.txt_submit);
        txt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              showBottomSheetDialog2();
            }
        });

        prefManager= new PrefManager(getApplicationContext());
        DataModel = new ArrayList<>();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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


                    DatePickerDialog datePickerDialog = new DatePickerDialog(UtensilsChefActivity.this,
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
     /*   GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        utensilsdatamodelchef=new ArrayList<>();
        utensilsdatamodelchef.add(new UtensilsDatamodelChef("spoon"));
        utensilsdatamodelchef.add(new UtensilsDatamodelChef("Tongs"));
        utensilsdatamodelchef.add(new UtensilsDatamodelChef("Bowl"));
       UtensilsAdapterChef todayAdapter= new UtensilsAdapterChef(utensilsdatamodelchef, UtensilsChefActivity.this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(todayAdapter);*/
           GetUtensil();
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
    public void GetUtensil(){
        ProgressDialog progressDialog = new ProgressDialog(UtensilsChefActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        APIClient.getAPIService().GetUtensil().enqueue(new Callback<AddChefUtensilsresponse>() {
            @Override
            public void onResponse(Call<AddChefUtensilsresponse> call, Response<AddChefUtensilsresponse> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    if (response.body().response){


                        recyclerView.setAdapter(new UtensilsAdapterChef(response.body().data,UtensilsChefActivity.this,UtensilsChefActivity.this));
                        recyclerView.setLayoutManager(new LinearLayoutManager(UtensilsChefActivity.this, RecyclerView.VERTICAL, false));

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(UtensilsChefActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(UtensilsChefActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddChefUtensilsresponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void click(String utensil_id, String quantity) {
        Log.d("TAG", "===new: "+utensil_id);
        Log.d("TAG", "===new: "+quantity);
        DataModel.add(new NewUtensilModel(utensil_id,quantity));

    }
    public  void addMenu(String menu_name){

        HashMap<String, String> data = prefManager.getUserDetails();
        String userId =data.get("id");
        String Date = date.getText().toString();
        Log.d("TAG", "===userId: "+userId);
        Log.d("TAG", "===userId: "+Date);

        Gson gson = new GsonBuilder().create();
        JsonArray jsonArray = gson.toJsonTree(DataModel).getAsJsonArray();
        String userData = jsonArray.toString();

        Log.d("TAG", "===onClick: "+userData);
        Log.d("TAG", "===menu name: "+menu_name);
        ProgressDialog progressDialog = new ProgressDialog(UtensilsChefActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        APIClient.getAPIService().AddUtensil(menu_name,userId,Date,"1",userData).enqueue(new Callback<SubmitMenuResponse>() {
            @Override
            public void onResponse(Call<SubmitMenuResponse> call, Response<SubmitMenuResponse> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    if (response.body().response){


                        Toast.makeText(UtensilsChefActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(UtensilsChefActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(UtensilsChefActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubmitMenuResponse> call, Throwable t) {

            }
        });
    }    private void showBottomSheetDialog2(){
        final Dialog dialog = new Dialog(UtensilsChefActivity.this);
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

}
