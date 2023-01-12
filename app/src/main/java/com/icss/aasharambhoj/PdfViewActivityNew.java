package com.icss.aasharambhoj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.icss.aasharambhoj.adapter.MenuNameAdapter;
import com.icss.aasharambhoj.model_class.DishByCategoryResponse;
import com.icss.aasharambhoj.model_class.GetMenuResponse;
import com.icss.aasharambhoj.network.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PdfViewActivityNew extends AppCompatActivity {
    WebView webView;

    String pdfurl,date,data,menuID ;
    Button delete,Print;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer_new);

        webView = findViewById(R.id.webview);
        delete = findViewById(R.id.delete);
        Print = findViewById(R.id.print);

       //pdfurl = getIntent().getStringExtra("date");
       date = getIntent().getStringExtra("date");
       data = getIntent().getStringExtra("data");
        menuID = getIntent().getStringExtra("menuID");
     //   Log.d("TAG", "====onCreate: "+pdfurl);
        Log.d("TAG", "====onCreate: "+date);
        Log.d("TAG", "====onCreate: "+data);
        Log.d("TAG", "====onCreate: "+menuID);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        String urlAsString ="https://brdistribution.in/aashram_bhoj/web/menu_detail_preview?date="+date+"&menu_name="+data;
        String urlAsString ="https://ihisaab.in/aashram_bhoj/web/menu_detail_preview?date="+date+"&menu_name="+data;
        String pdfurl=urlAsString.replaceAll(" ", "%20");
        Log.d("TAG", "====onCreate: "+pdfurl);
         webView.loadUrl(pdfurl);
        //webView.loadUrl("https://www.srpulses.com/pharma/uploads/Get_Started_With_Smallpdf.pdf");

        Print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(pdfurl));
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMenu();
            }
        });

    }
    public void deleteMenu(){
        APIClient.getAPIService().deleteMenu(menuID).enqueue(new Callback<DishByCategoryResponse>() {
            @Override
            public void onResponse(Call<DishByCategoryResponse> call, Response<DishByCategoryResponse> response) {
                if(response.isSuccessful()){
                    if (response.body().response) {

                        Intent intent= new Intent(PdfViewActivityNew.this,ChefHomeActivity.class);
                        startActivity(intent);

                        finish();
                    }else{
                        Toast.makeText(PdfViewActivityNew.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(PdfViewActivityNew.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DishByCategoryResponse> call, Throwable t) {

            }
        });
    }
   }