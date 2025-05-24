package com.project.bongoagro.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.bongoagro.Model.LoginModel;
import com.project.bongoagro.Model.LoginRequest;
import com.project.bongoagro.R;
import com.project.bongoagro.Retrofit.RetrofitClient;
import com.project.bongoagro.helper.CheckInternetConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView loginText;
    private EditText phone_number, password;
    private Button submit_btn;
    private ProgressDialog progressDialog;
    CheckInternetConnection checkInternetConnection = new CheckInternetConnection(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkInternetConnection.checkInternetConnection();

        getSupportActionBar().setElevation(0);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#D3E6F7"));
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#D3E6F7")));

        loginText = findViewById(R.id.signInText1);
        phone_number = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);
        submit_btn = findViewById(R.id.submit_btn);
        progressDialog = new ProgressDialog(this);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Logging...");
                progressDialog.show();
                if (TextUtils.isEmpty(phone_number.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    LoginRequest request = new LoginRequest(phone_number.getText().toString(), password.getText().toString());
                    Call<LoginModel> loginCall = RetrofitClient
                            .getInstance()
                            .getApi()
                            .login(request);
                    loginCall.enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                            if(response.isSuccessful()){
                                progressDialog.dismiss();

                                Integer roleId = response.body().getRoleName();
                                String fullName = response.body().getFullName();
                                String token = response.body().getToken();
                                Integer userId = response.body().getUserId();

                                // Save data in SharedPreferences
                                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("roleName", String.valueOf(roleId));
                                editor.putString("fullName", fullName);
                                editor.putString("token", token);
                                editor.putString("userId", String.valueOf(userId));
                                editor.apply();

                                Toast.makeText(getApplicationContext(), "You are logged in..", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                startActivity(intent);

                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Incorrect Username or Password!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable throwable) {
                            Toast.makeText(getApplicationContext(), "Something went wrong. Try again later!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}