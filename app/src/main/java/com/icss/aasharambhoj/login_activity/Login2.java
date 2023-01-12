package com.icss.aasharambhoj.login_activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.icss.aasharambhoj.BuyerActivity;
import com.icss.aasharambhoj.LoginActivity;
import com.icss.aasharambhoj.R;
import com.icss.aasharambhoj.model_class.LoginBuyerResponse;
import com.icss.aasharambhoj.model_class.LoginResponse;
import com.icss.aasharambhoj.network.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login2 extends AppCompatActivity {
    TextView textView;
    TextView sign_up;
    EditText email, password;
    public Boolean isValidation=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_2);
        textView=findViewById(R.id.text2);
       
        email = findViewById(R.id.edit);
        password = findViewById(R.id.edit1 );
        textView.setOnClickListener(view -> {
           login_buyer_api();

        });

    }

    private void login_buyer_api(){

        String EditEmail = email.getText().toString();
        String EditPassword = password.getText().toString();

        Log.d("TAG", "==email: "+EditEmail);
        Log.d("TAG", "==pass: "+EditPassword);

        if (EditEmail.isEmpty()){
            email.requestFocus();
            email.setError("Please Enter Email");
            return;
        }
        if (EditPassword.isEmpty()){
            password.requestFocus();
            password.setError("Please Enter Password");
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        APIClient.getAPIService().login_buyer(EditEmail,EditPassword).enqueue(new Callback<LoginBuyerResponse>() {
            @Override
            public void onResponse(Call<LoginBuyerResponse> call, Response<LoginBuyerResponse> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    if (response.body().response){
                        Intent intent = new Intent(Login2.this, BuyerActivity.class);
                        startActivity(intent);
                        Toast.makeText(Login2.this, "Welcome", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(Login2.this, "Something went wrong...", Toast.LENGTH_SHORT).show();;
                    }

                }


            }

            @Override
            public void onFailure(Call<LoginBuyerResponse> call, Throwable t) {
                Toast.makeText(Login2.this, "Something went wrong...!!!!!", Toast.LENGTH_SHORT).show();

            }
        });






    }
}

