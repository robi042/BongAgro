package com.project.bongoagro.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.bongoagro.Adapter.AdapterClassCultivationGuide;
import com.project.bongoagro.Adapter.AdapterClassDisease;
import com.project.bongoagro.Model.CultivationGuide;
import com.project.bongoagro.Model.DiseaseResponse;
import com.project.bongoagro.R;
import com.project.bongoagro.Retrofit.RetrofitClient;
import com.project.bongoagro.helper.SessionManager;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiseaseActivity extends AppCompatActivity {
    private String productId, code;
    private RecyclerView componant_recyle_view;
    private RecyclerView.Adapter adapter;
    private List<CultivationGuide> diseaseResponses;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componant_details);
        componant_recyle_view = findViewById(R.id.componant_recyle_view);

        Intent intent = getIntent();
        if (intent.hasExtra("productId")) {
            productId = intent.getStringExtra("productId");
        }
        if (intent.hasExtra("code")) {
            code = intent.getStringExtra("code");
        }

        componant_recyle_view.setHasFixedSize(true);
        componant_recyle_view.setLayoutManager(new LinearLayoutManager(this));

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

        Call<List<CultivationGuide>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getProductComponentDetailByProductId(token, productId, code);
        call.enqueue(new Callback<List<CultivationGuide>>() {
            @Override
            public void onResponse(Call<List<CultivationGuide>> call, Response<List<CultivationGuide>> response) {
                if(response.isSuccessful()) {
                    progressDialog.dismiss();
                    if(response.body().size() < 1){
                        Toast.makeText(getApplicationContext(), "No data found!!", Toast.LENGTH_LONG).show();
                    }else {
                        diseaseResponses = response.body();
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
                adapter = new AdapterClassDisease(diseaseResponses, getApplicationContext());

                componant_recyle_view.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CultivationGuide>> call, Throwable t) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product_id", String.valueOf(productId));
        startActivity(intent);
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product_id", String.valueOf(productId));
        startActivity(intent);
        finish();
    }
}