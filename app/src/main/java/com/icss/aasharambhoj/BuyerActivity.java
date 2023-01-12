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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.adapter.MenuNameAdapter;
import com.icss.aasharambhoj.model_class.GetMenuResponse;
import com.icss.aasharambhoj.network.APIClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyerActivity extends AppCompatActivity {
    TextView ingredint;
    TextView untensils;
    String data;
    ImageView share;
    TextView today_menu;
    String vendorId;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date_c, encodedString;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_home_activity);
        ingredint = findViewById(R.id.kundali_matching);
        untensils = findViewById(R.id.untensils1);
        share = findViewById(R.id.btnnn);

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();
        date_c = timeStampFormat.format(myDate);

        Log.d("TAG", "==date: " + date_c);
        today_menu = findViewById(R.id.kundli_making);
        today_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerActivity.this, DishAcitivity.class);
                startActivity(intent);
            }
        });

        ingredint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                show_dailog();
            }
        });

        untensils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerActivity.this, UtensilsActivityBuyerr.class);
                startActivity(intent);
            }
        });
       /* date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == date) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(BuyerActivity.this,
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
        });*/
    }

    private void show_dailog() {
        Dialog dialogView = new Dialog(BuyerActivity.this);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setContentView(R.layout.popup_item_buyer_activity);
        Window window = dialogView.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        TextView date = dialogView.findViewById(R.id.date);
        TextView submit = dialogView.findViewById(R.id.submit);
        TextView share = dialogView.findViewById(R.id.share);
        recyclerView = dialogView.findViewById(R.id.recyclr);


//        String urlAsString = "https://brdistribution.in/aashram_bhoj/web/menu_detail_preview_combine?date=" + date + "&menu_name=" + data;
        String urlAsString = "https://ihisaab.in/aashram_bhoj/web/menu_detail_preview_combine?date=" + date + "&menu_name=" + data;


        try {
            encodedString = URLEncoder.encode(urlAsString, "UTF-8").replaceAll("\\+", "%20");
            Log.d("TAG", "====onCreate: " + encodedString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("My_Vendor", MODE_PRIVATE);
                vendorId = sharedPreferences.getString("ID", null);
                Log.d("TAG", "====iddd: " + vendorId);
                if (!date.getText().toString().isEmpty()) {
                    Intent intent = new Intent(BuyerActivity.this, PdfViewActivity.class);
                    intent.putExtra("date", date.getText().toString());
                    intent.putExtra("data", vendorId);
                    startActivity(intent);
                } else {
                    Toast.makeText(BuyerActivity.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                }

            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!date.getText().toString().isEmpty()) {
                    SharedPreferences sharedPreferences = getSharedPreferences("My_Vendor", MODE_PRIVATE);
                    vendorId = sharedPreferences.getString("ID", null);

//                    String urlAsString = "https://brdistribution.in/aashram_bhoj/web/menu_detail_preview_combine?date=" + date.getText().toString() + "&menu_name=" + vendorId;
                    String urlAsString = "https://ihisaab.in/aashram_bhoj/web/menu_detail_preview_combine?date=" + date.getText().toString() + "&menu_name=" + vendorId;
                    String pdfurl = urlAsString.replaceAll(" ", "%20");
                    Log.d("TAG", "====onCreate: " + pdfurl);

                    try {
                        encodedString = URLEncoder.encode(urlAsString, "UTF-8").replaceAll(" ", "%20");
                        Log.d("TAG", "====onCreate: " + encodedString);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Intent share = new Intent(android.content.Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                    // Add data to the intent, the receiving app will decide
                    // what to do with it.
                    share.putExtra(Intent.EXTRA_SUBJECT, "Share PDF");
                    share.putExtra(Intent.EXTRA_TEXT, pdfurl);

                    startActivity(Intent.createChooser(share, "Share link!"));

                    Log.d("TAG", "===url: " + "https://brdistribution.in/aashram_bhoj/web/menu_detail_preview_combine?date=" + date.getText().toString() + "&menu_name=" + vendorId);

                } else {
                    Toast.makeText(BuyerActivity.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                }
            }
        });
        date.setText(date_c);
        String date_text = date.getText().toString();
        Log.d("TAG", "===show_dailog: " + date_text + " , " + date_c);
        getMenu(date_text);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == date) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(BuyerActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    date.setText(year + "-" + getMonth(monthOfYear + 1) + "-" + dayOfMonth);


                                    Log.d("TAG", "====onDateSet: " + getMonth(monthOfYear + 1));
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

    private void getMenu(String date_text) {
        APIClient.getAPIService().get_Menu(date_text).enqueue(new Callback<GetMenuResponse>() {
            @Override
            public void onResponse(Call<GetMenuResponse> call, Response<GetMenuResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().response) {

                        recyclerView.setAdapter(new MenuNameAdapter(response.body().data, BuyerActivity.this));
                        recyclerView.setLayoutManager(new LinearLayoutManager(BuyerActivity.this, RecyclerView.VERTICAL, false));


                    } else {
                        Toast.makeText(BuyerActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BuyerActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetMenuResponse> call, Throwable t) {

            }
        });
    }


}
