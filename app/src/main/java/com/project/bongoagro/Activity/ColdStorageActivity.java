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
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.project.bongoagro.Adapter.AdapterClassColdStorage;
import com.project.bongoagro.Model.ColdStorageResponseModelClass;
import com.project.bongoagro.R;
import com.project.bongoagro.Retrofit.RetrofitClient;
import com.project.bongoagro.helper.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ColdStorageActivity extends AppCompatActivity {
    private RecyclerView coldStorage_recyle_view;
    private RecyclerView.Adapter adapter;
    private List<ColdStorageResponseModelClass> coldStorageResponseModelClasses;
    private String categoryId;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold_storage);

        coldStorage_recyle_view = findViewById(R.id.coldStorage_recyle_view);

        Intent intent = getIntent();
        if (intent.hasExtra("category_id")) {
            categoryId = intent.getStringExtra("category_id");
        }
        coldStorage_recyle_view.setHasFixedSize(true);
        coldStorage_recyle_view.setLayoutManager(new LinearLayoutManager(this));

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
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<List<ColdStorageResponseModelClass>> coldCall = RetrofitClient
                .getInstance()
                .getApi()
                .coldStorage(token);
        coldCall.enqueue(new Callback<List<ColdStorageResponseModelClass>>() {
            @Override
            public void onResponse(Call<List<ColdStorageResponseModelClass>> call, Response<List<ColdStorageResponseModelClass>> response) {
                if(response.isSuccessful()) {
                    progressDialog.dismiss();
                    if(response.body().size() < 1){
                        Toast.makeText(getApplicationContext(), "No data found!!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        coldStorageResponseModelClasses = response.body();
                    }
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
                adapter = new AdapterClassColdStorage(coldStorageResponseModelClasses, getApplicationContext());

                coldStorage_recyle_view.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ColdStorageResponseModelClass>> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}