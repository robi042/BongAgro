package com.project.bongoagro.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.bongoagro.Model.ResetPassResponse;
import com.project.bongoagro.R;
import com.project.bongoagro.Model.ResetPasswordRequest;
import com.project.bongoagro.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText phone_number;
    private Button submit_btn;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        phone_number = findViewById(R.id.phone_number);
        submit_btn = findViewById(R.id.submit_btn);
        getSupportActionBar().setElevation(0);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#D3E6F7"));
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#D3E6F7")));
        progressDialog = new ProgressDialog(this);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Processing...");
                progressDialog.show();
                if (TextUtils.isEmpty(phone_number.getText().toString()))
                {
                    progressDialog.dismiss();
                    Toast.makeText(ResetPasswordActivity.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    ResetPasswordRequest request = new ResetPasswordRequest(phone_number.getText().toString(), "0");
                    Call<ResetPassResponse> loginCall = RetrofitClient
                            .getInstance()
                            .getApi()
                            .resetPass(request);
                    loginCall.enqueue(new Callback<ResetPassResponse>() {
                        @Override
                        public void onResponse(Call<ResetPassResponse> call, Response<ResetPassResponse> response) {
                            if(response.isSuccessful()){
                                progressDialog.dismiss();

                                int auth = response.body().getAuthCode();
                                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                intent.putExtra("auth_code", String.valueOf(auth));
                                intent.putExtra("username", response.body().getUsername());
                                startActivity(intent);

                            }
                            else{
                                progressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResetPassResponse> call, Throwable throwable) {
                            Toast.makeText(getApplicationContext(), "Something went wrong. Try again later!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });


    }
}