package com.icss.aasharambhoj;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.icss.aasharambhoj.adapter.RememberPointsAdapter;
import com.icss.aasharambhoj.model_class.AddNewData;
import com.icss.aasharambhoj.model_class.GetDishesResponse;
import com.icss.aasharambhoj.model_class.NewMenuModel;
import com.icss.aasharambhoj.model_class.SubmitMenuResponse;
import com.icss.aasharambhoj.model_class.remember.RememberInitRes;
import com.icss.aasharambhoj.network.APIClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity implements AddNewData {
    RecyclerView recyclerView;
    List<MenuDataModel> menuDataModels;
    EditText date;
    ImageView back;
    TextView txt_submit;
    String ID;
    List<NewMenuModel> vendorList;
    PrefManager prefManager;
    List<NewMenuModel> DataModel;
    String dishId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        recyclerView = findViewById(R.id.recycler);
        txt_submit = findViewById(R.id.txt_submit);
        date = findViewById(R.id.date);
        back = findViewById(R.id.iv_image1);

        prefManager = new PrefManager(getApplicationContext());
        ID = getIntent().getStringExtra("id");

        Log.d("TAG", "===onCreate: " + ID);

        vendorList = new ArrayList<>();
        DataModel = new ArrayList<>();

//sir
        txt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!dishId.equalsIgnoreCase("")) {
                    showRememberDialog(dishId);
                } else {
                    showBottomSheetDialog2();
                }
              /*  SharedPreferences sharedPreferences =  getSharedPreferences(MenuAdapter.PREF_VENDOR,MODE_PRIVATE);
                Gson gson = new Gson();
                String vendor = sharedPreferences.getString("vendor",null);
                Type type = new TypeToken<ArrayList<NewMenuModel>>(){}.getType();
                vendorList = gson.fromJson(vendor,type);
                if (vendorList!=null) {
                    Log.d("TAG", "===vendorList: " + vendorList.size());
                }else {
                    Toast.makeText(MenuActivity.this, "No Value", Toast.LENGTH_SHORT).show();
                }*/

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
     /*   GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        menuDataModels=new ArrayList<>();
        menuDataModels.add(new MenuDataModel("Daal Bati"));
        menuDataModels.add(new MenuDataModel("Raj Bhog"));
        menuDataModels.add(new MenuDataModel("chili paneer"));
        menuDataModels.add(new MenuDataModel("satvik bhojan"));

        MenuAdapter menuAdapter=new MenuAdapter(menuDataModels, MenuActivity.this);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(menuAdapter);*/

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == date) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(MenuActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    date.setText(year + "-" + getMonth(monthOfYear + 1) + "-" + dayOfMonth);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });


        Get_Dishes();
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

    public void Get_Dishes() {
        ProgressDialog progressDialog = new ProgressDialog(MenuActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        APIClient.getAPIService().GetMenuNew().enqueue(new Callback<GetDishesResponse>() {
            @Override
            public void onResponse(Call<GetDishesResponse> call, Response<GetDishesResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().response) {


                        recyclerView.setAdapter(new MenuAdapter(response.body().data, MenuActivity.this, MenuActivity.this));
                        recyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this, RecyclerView.VERTICAL, false));

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MenuActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(MenuActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetDishesResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void click(String dish_id, String quantity, String serving_type) {
        dishId = dish_id;
        Log.d("TAG", "===new: " + dish_id);
        Log.d("TAG", "===new: " + quantity);
        Log.d("TAG", "===new: " + serving_type);
        DataModel.add(new NewMenuModel(dish_id, quantity, serving_type));
    }

    public void addMenu(String menuName) {

        HashMap<String, String> data = prefManager.getUserDetails();
        String userId = data.get("id");
        String Date = date.getText().toString();


        Gson gson = new GsonBuilder().create();
        JsonArray jsonArray = gson.toJsonTree(DataModel).getAsJsonArray();
        String userData = jsonArray.toString();
        Log.d("TAG", "===userId: " + userId);
        Log.d("TAG", "===userId: " + ID);
        Log.d("TAG", "===userId: " + Date);
        Log.d("TAG", "===userId: " + menuName);
        Log.d("TAG", "===onClick: " + userData);
        ProgressDialog progressDialog = new ProgressDialog(MenuActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        APIClient.getAPIService().AddMenu(menuName, userId, Date, ID, userData).enqueue(new Callback<SubmitMenuResponse>() {
            @Override
            public void onResponse(Call<SubmitMenuResponse> call, Response<SubmitMenuResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().response) {


                        Toast.makeText(MenuActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MenuActivity.this, "Failed hi", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(MenuActivity.this, "Failed hello", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubmitMenuResponse> call, Throwable t) {

            }
        });
    }

    private void showBottomSheetDialog2() {
        final Dialog dialog = new Dialog(MenuActivity.this);
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


    private void showRememberDialog(String dishId) {
        Dialog dialogView = new Dialog(MenuActivity.this);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setContentView(R.layout.dialog_remember);
        Window window = dialogView.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        TextView submit = dialogView.findViewById(R.id.submit);
        recyclerView = dialogView.findViewById(R.id.rv_points);

        APIClient.getAPIService().getPointsRemember(dishId).enqueue(new Callback<RememberInitRes>() {
            @Override
            public void onResponse(Call<RememberInitRes> call, Response<RememberInitRes> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse()) {

                        recyclerView.setAdapter(new RememberPointsAdapter(response.body().getData(), MenuActivity.this));
                        recyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this, RecyclerView.VERTICAL, false));


                    } else {
                        Toast.makeText(MenuActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MenuActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RememberInitRes> call, Throwable t) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogView != null && dialogView.isShowing()) {
                    dialogView.dismiss();
                    showBottomSheetDialog2();

                }

            }
        });

        dialogView.setCanceledOnTouchOutside(true);
        dialogView.show();
    }


}
