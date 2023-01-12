package com.icss.aasharambhoj;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.icss.aasharambhoj.model_class.LoginResponse;
import com.icss.aasharambhoj.network.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView loginButton;
    TextView sign_up;
    EditText email, password;
    public Boolean isValidation = null;
    PrefManager prefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        loginButton = findViewById(R.id.text2);

        email = findViewById(R.id.edit);
        password = findViewById(R.id.edit1);

        prefManager = new PrefManager(getApplicationContext());
        loginButton.setOnClickListener(view -> {


            loginCall();

        });

    }

    private void loginCall() {

        String Email = email.getText().toString();
        String Password = password.getText().toString();

        Log.d("TAG", "==email: " + Email);
        Log.d("TAG", "==pass: " + Password);

        if (Email.isEmpty()) {
            email.requestFocus();
            email.setError("Please Enter Email");
            return;
        }
        if (Password.isEmpty()) {
            password.requestFocus();
            password.setError("Please Enter Password");
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        APIClient.getAPIService().login(Email, Password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().response) {
//                        Log.d("TAG", "onResponse: id " + response.body().data.get(0).id +
//                                " name " + response.body().data.get(0).name + " type " +
//                                "" + response.body().data.get(0).type);
                        prefManager.createLogin(String.valueOf(response.body().data.get(0).id), response.body().data.get(0).name, response.body().data.get(0).type);
                        Intent intent = new Intent(LoginActivity.this, ChefHomeActivity.class);
                        Toast.makeText(LoginActivity.this, "Welcome...", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Something went wrong...!!!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(LoginActivity.this, "Something went wrong...!!!!!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
