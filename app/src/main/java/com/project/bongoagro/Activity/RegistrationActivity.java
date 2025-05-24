package com.project.bongoagro.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.bongoagro.R;
import com.project.bongoagro.Model.RegistrationModel;
import com.project.bongoagro.Retrofit.RetrofitClient;
import com.project.bongoagro.Model.RoleModel;
import com.project.bongoagro.Model.signupJavaRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private TextView loginText;
    private EditText phone_number, password, rewrite_password;
    private Spinner select_type;
    private Button submit_btn;
    private List<String> categories;
    private ProgressDialog progressDialog;
    int roleId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

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
        rewrite_password = findViewById(R.id.rewrite_password);
        select_type = findViewById(R.id.select_type);
        submit_btn = findViewById(R.id.submit_btn);
        progressDialog = new ProgressDialog(this);

        Call<List<RoleModel>> roleCall = RetrofitClient
                .getInstance()
                .getApi()
                .role();
        roleCall.enqueue(new Callback<List<RoleModel>>() {
            @Override
            public void onResponse(Call<List<RoleModel>> call, Response<List<RoleModel>> response) {
                if(response.isSuccessful() && response.body().size() > 0){
                    List<RoleModel> a = response.body();
                    categories = new ArrayList<String>();
                    List<Integer> roleIds = new ArrayList<>();
                    categories.add("Please select your type");
                    roleIds.add(-1);

                    for (RoleModel category: a) {
                        categories.add(category.getName());
                        roleIds.add(category.getId());
                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, categories);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    select_type.setAdapter(dataAdapter);

                    select_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position > 0) {
                                int selectedRoleId = roleIds.get(position);
                                Log.d("Selected Role ID", "Role ID: " + selectedRoleId);
                                // Update roleId variable
                                roleId = selectedRoleId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Do nothing
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<RoleModel>> call, Throwable throwable) {

                Toast.makeText(RegistrationActivity.this,
                        "Something went wrong!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();
                if (TextUtils.isEmpty(phone_number.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(rewrite_password.getText().toString())
                        || roleId <=0)
                {
                    Toast.makeText(RegistrationActivity.this,
                            "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                }
                else if(!password.getText().toString().equals(rewrite_password.getText().toString())){
                    Toast.makeText(RegistrationActivity.this,
                            "Password mismatch!"+ password.getText().toString() + ""+rewrite_password.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    signupJavaRequest request = new signupJavaRequest(
                            phone_number.getText().toString(),
                            password.getText().toString(),
                            rewrite_password.getText().toString(),
                            roleId
                    );
                    Call<RegistrationModel> registrationCall = RetrofitClient
                            .getInstance()
                            .getApi()
                            .registration(request);
                    registrationCall.enqueue(new Callback<RegistrationModel>() {
                        @Override
                        public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                            if(response.body().isSignupSuccess() == true){
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), response.body().getSuccessMessage(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);

                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), response.body().getErrorMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegistrationModel> call, Throwable throwable) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Something went wrong. Try again later!", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }
}