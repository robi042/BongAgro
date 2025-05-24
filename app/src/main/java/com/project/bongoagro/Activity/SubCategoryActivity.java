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
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.project.bongoagro.Adapter.AdapterClassSubCategory;
import com.project.bongoagro.R;
import com.project.bongoagro.Retrofit.RetrofitClient;
import com.project.bongoagro.Model.SubCategoryModel;
import com.project.bongoagro.helper.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryActivity extends AppCompatActivity {
    private RecyclerView sub_category_recyle_view;
    private RecyclerView.Adapter adapter;
    private List<SubCategoryModel> allCategory;
    private String categoryId;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        sub_category_recyle_view = findViewById(R.id.sub_category_recyle_view);

        Intent intent = getIntent();
        if (intent.hasExtra("category_id")) {
            categoryId = intent.getStringExtra("category_id");
        }
        sub_category_recyle_view.setHasFixedSize(true);
        sub_category_recyle_view.setLayoutManager(new LinearLayoutManager(this));

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

        Call<List<SubCategoryModel>> subCategory = RetrofitClient
                .getInstance()
                .getApi()
                .sub_category(token, Integer.parseInt(categoryId));
        subCategory.enqueue(new Callback<List<SubCategoryModel>>() {
            @Override
            public void onResponse(Call<List<SubCategoryModel>> call, Response<List<SubCategoryModel>> response) {
                if(response.isSuccessful()) {
                    progressDialog.dismiss();
                    if(response.body().size() < 1){
                        Toast.makeText(getApplicationContext(), "No data found!!", Toast.LENGTH_LONG).show();
                    }else {
                        allCategory = response.body();
                    }
                }
                else{
                    progressDialog.dismiss();
                    //Log.e("API_ERROR", "Response Code: " + response.code() + ", Message: " + response.message());
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
                adapter = new AdapterClassSubCategory(allCategory, getApplicationContext());

                sub_category_recyle_view.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SubCategoryModel>> call, Throwable t) {
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