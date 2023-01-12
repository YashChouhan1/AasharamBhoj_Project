package com.icss.aasharambhoj;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.adapter.CategoryAdapter;
import com.icss.aasharambhoj.adapter.CategoryAdapterNew;
import com.icss.aasharambhoj.adapter.MenuNameAdapter;
import com.icss.aasharambhoj.model_class.CategoryResponse;
import com.icss.aasharambhoj.model_class.GetMenuResponse;
import com.icss.aasharambhoj.network.APIClient;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChefHomeActivity extends AppCompatActivity {
    TextView menu;
    TextView untensils;
    TextView category;
    CardView viewMenu, prefixMenu;
    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date_c;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_home_activity);
        menu = findViewById(R.id.menu);
        untensils = findViewById(R.id.untensils);
        category = findViewById(R.id.category);
        viewMenu = findViewById(R.id.card5);
        prefixMenu = findViewById(R.id.card4);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChefHomeActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();
        date_c = timeStampFormat.format(myDate);

        Log.d("TAG", "==date: " + date_c);

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_dailog();
            }


        });
        untensils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChefHomeActivity.this, UtensilsChefActivity.class);
                startActivity(intent);
            }
        });


        viewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_dailogNew();
            }
        });

        prefixMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_dailog2();
                // startActivity(new Intent(ChefHomeActivity.this,PrefixActivity.class));
            }
        });
    }

    private void show_dailog() {
        Dialog dialogView = new Dialog(ChefHomeActivity.this);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setContentView(R.layout.category_activity);
        Window window = dialogView.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        RecyclerView recyclerView = dialogView.findViewById(R.id.recyc);

        APIClient.getAPIService().Get_categ().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().response) {
                        recyclerView.setAdapter(new CategoryAdapter(response.body().data, ChefHomeActivity.this));
                        recyclerView.setLayoutManager(new LinearLayoutManager(ChefHomeActivity.this, RecyclerView.VERTICAL, false));
                    } else {
                        Toast.makeText(ChefHomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChefHomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });




       /* TextView breakfast = dialogView.findViewById(R.id.brealfast);
       TextView dinner = dialogView.findViewById(R.id.dinner);
        TextView lunch= dialogView.findViewById(R.id.lunch);*/
        /*lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ChefHomeActivity.this,MenuActivity.class);
                startActivity(intent);


            }
        });*/
     /*   lunch.setOnClickListener(view -> {
            String  obj1=lunch.getText().toString();
            category.setText(obj1);
            Intent intent= new Intent(ChefHomeActivity.this,MenuActivity.class);
            startActivity(intent);
        });
        dinner.setOnClickListener(view -> {
            String  obj2=dinner.getText().toString();
            category.setText(obj2);
            Intent intent= new Intent(ChefHomeActivity.this,MenuActivity.class);
            startActivity(intent);
        });
        breakfast.setOnClickListener(view -> {
            String  obj3=breakfast.getText().toString();
            category.setText(obj3);
            dialogView.onBackPressed();

        });*/


        dialogView.setCanceledOnTouchOutside(true);
        dialogView.show();

    }

    private void show_dailog2() {
        Dialog dialogView = new Dialog(ChefHomeActivity.this);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setContentView(R.layout.category_activity);
        Window window = dialogView.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        recyclerView = dialogView.findViewById(R.id.recyc);

        APIClient.getAPIService().Get_categ().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().response) {


                        recyclerView.setAdapter(new CategoryAdapterNew(response.body().data, ChefHomeActivity.this));
                        recyclerView.setLayoutManager(new LinearLayoutManager(ChefHomeActivity.this, RecyclerView.VERTICAL, false));

                    } else {

                        Toast.makeText(ChefHomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChefHomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });




       /* TextView breakfast = dialogView.findViewById(R.id.brealfast);
       TextView dinner = dialogView.findViewById(R.id.dinner);
        TextView lunch= dialogView.findViewById(R.id.lunch);*/
        /*lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ChefHomeActivity.this,MenuActivity.class);
                startActivity(intent);


            }
        });*/
     /*   lunch.setOnClickListener(view -> {
            String  obj1=lunch.getText().toString();
            category.setText(obj1);
            Intent intent= new Intent(ChefHomeActivity.this,MenuActivity.class);
            startActivity(intent);
        });
        dinner.setOnClickListener(view -> {
            String  obj2=dinner.getText().toString();
            category.setText(obj2);
            Intent intent= new Intent(ChefHomeActivity.this,MenuActivity.class);
            startActivity(intent);
        });
        breakfast.setOnClickListener(view -> {
            String  obj3=breakfast.getText().toString();
            category.setText(obj3);
            dialogView.onBackPressed();

        });*/


        dialogView.setCanceledOnTouchOutside(true);
        dialogView.show();

    }

    private void show_dailogNew() {
        Dialog dialogView = new Dialog(ChefHomeActivity.this);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setContentView(R.layout.popup_item_buyer_activity_new);
        Window window = dialogView.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        TextView date = dialogView.findViewById(R.id.date);
        TextView submit = dialogView.findViewById(R.id.submit);
        TextView share = dialogView.findViewById(R.id.share);
        recyclerView = dialogView.findViewById(R.id.recyclr);

        date.setText(date_c);

        SharedPreferences sharedPreferences = getSharedPreferences("My_Vendor", MODE_PRIVATE);
        String vendorId = sharedPreferences.getString("ID", null);

        String urlAsString ="https://ihisaab.in/aashram_bhoj/web/menu_detail_preview?date="+date.getText().toString()+"&menu_name="+vendorId;
        String pdfurl=urlAsString.replaceAll(" ", "%20");

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = pdfurl.toString();
                String shareSub = "Aashram Bhoj";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Share using"));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("My_Vendor", MODE_PRIVATE);
                String vendorId = sharedPreferences.getString("ID", null);
                String menuID = sharedPreferences.getString("MENUID", null);
                Log.d("TAG", "====iddd: " + vendorId);
                if (!date.getText().toString().isEmpty()) {
                    Intent intent = new Intent(ChefHomeActivity.this, PdfViewActivityNew.class);
                    intent.putExtra("date", date.getText().toString());
                    intent.putExtra("data", vendorId);
                    intent.putExtra("menuID", menuID);
                    startActivity(intent);
                } else {
                    Toast.makeText(ChefHomeActivity.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                }

            }
        });
/*
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!date.getText().toString().isEmpty()) {


                    Intent share = new Intent(android.content.Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                    // Add data to the intent, the receiving app will decide
                    // what to do with it.
                    share.putExtra(Intent.EXTRA_SUBJECT, "Share PDF");
                    share.putExtra(Intent.EXTRA_TEXT, "https://brdistribution.in/aashram_bhoj/web/menu_detail_preview_combine?date="+ date.getText().toString()+"&menu_name="+vendorId);

                    startActivity(Intent.createChooser(share, "Share link!"));



                } else{
                    Toast.makeText(ChefHomeActivity.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == date) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(ChefHomeActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    date.setText(year + "-" + getMonth(monthOfYear + 1) + "-" + dayOfMonth);

                                    if (!date.getText().toString().equals("")) {
                                        getMenu(date.getText().toString());
                                    }
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });


        dialogView.setCanceledOnTouchOutside(true);
        dialogView.show();
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

    private void getMenu(String toString) {
        APIClient.getAPIService().get_Menu(toString).enqueue(new Callback<GetMenuResponse>() {
            @Override
            public void onResponse(Call<GetMenuResponse> call, Response<GetMenuResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().response) {

                        recyclerView.setAdapter(new MenuNameAdapter(response.body().data, ChefHomeActivity.this));
                        recyclerView.setLayoutManager(new LinearLayoutManager(ChefHomeActivity.this, RecyclerView.VERTICAL, false));


                    } else {
                        Toast.makeText(ChefHomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChefHomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetMenuResponse> call, Throwable t) {

            }
        });
    }
}
