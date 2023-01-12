package com.icss.aasharambhoj;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class PdfViewActivity extends AppCompatActivity {
    WebView webView;

    String pdfurl,date,data,encodedString ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);

        webView = findViewById(R.id.webview);

       //pdfurl = getIntent().getStringExtra("date");
       date = getIntent().getStringExtra("date");
       data = getIntent().getStringExtra("data");
     //   Log.d("TAG", "====onCreate: "+pdfurl);
        Log.d("TAG", "====onCreate: "+date);
        Log.d("TAG", "====onCreate: "+data);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

//        String urlAsString ="https://brdistribution.in/aashram_bhoj/web/menu_detail_preview_combine?date="+date+"&menu_name="+data;
        String urlAsString ="https://ihisaab.in/aashram_bhoj/web/menu_detail_preview_combine?date="+date+"&menu_name="+data;
        pdfurl=urlAsString.replaceAll(" ", "%20");
        Log.d("TAG", "====onCreate: "+pdfurl);
        //webView.loadUrl("https://www.srpulses.com/pharma/uploads/Get_Started_With_Smallpdf.pdf");

        //String temp = http://www.arteonline.mobi/iphone/output.php?gallery=MALBA%20-%20MUSEO%20DE%20ARTE%20LATINOAMERICANO%20DE%20BUENOS%20AIRES

        try {
             encodedString = URLEncoder.encode(urlAsString,"UTF-8").replaceAll("\\+", "%20");
            Log.d("TAG", "====onCreate: "+encodedString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
       // webView.loadUrl(pdfurl);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(pdfurl));
        startActivity(i);
    }
   }