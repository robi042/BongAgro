package com.project.bongoagro.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.bongoagro.Adapter.CategoryAdapter;
import com.project.bongoagro.Model.CategoryModel;
import com.project.bongoagro.R;
import com.project.bongoagro.Retrofit.RetrofitClient;
import com.project.bongoagro.helper.SessionManager;
import com.project.bongoagro.helper.CheckInternetConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    private ImageView logout;
    private RecyclerView category_recyle_view;
    private RecyclerView.Adapter adapter;
    private List<CategoryModel> allCategory;
    boolean doubleBackToExitPressedOnce = false;
    ProgressDialog progressDialog;
    CheckInternetConnection chInternet = new CheckInternetConnection(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        chInternet.checkInternetConnection();
        logout = findViewById(R.id.logout);
        category_recyle_view = findViewById(R.id.category_recyle_view);

        category_recyle_view.setHasFixedSize(true);
        category_recyle_view.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String token = "Bearer "+sharedPreferences.getString("token", "");


        // Find custom Toolbar and set it as ActionBar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar); // No more conflicts

        // Remove default title
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#057CA5"));
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Removes all saved data
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<List<CategoryModel>> categoryCall = RetrofitClient
                .getInstance()
                .getApi()
                .category(token);
        categoryCall.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if(response.isSuccessful()) {
                    progressDialog.dismiss();
                    if(response.body().size() < 1){
                        Toast.makeText(getApplicationContext(), "No data found!!", Toast.LENGTH_LONG).show();
                    }
                     allCategory = response.body();
                }
                else{
                    progressDialog.dismiss();
                    if(response.code() == 400){
                        Toast.makeText(getApplicationContext(), "Bad Request!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    }
                    else if(response.code() == 500){
                        Toast.makeText(getApplicationContext(), "Internal server error!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Session expired, Please login!", Toast.LENGTH_LONG).show();
                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        sessionManager.logout();
                    }

                }
                adapter = new CategoryAdapter(allCategory, getApplicationContext());

                category_recyle_view.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            finishAffinity();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }
}
