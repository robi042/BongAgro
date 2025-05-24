package com.project.bongoagro.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
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

import com.project.bongoagro.Adapter.AdapterClassProductDetails;
import com.project.bongoagro.R;
import com.project.bongoagro.Retrofit.RetrofitClient;
import com.project.bongoagro.helper.SessionManager;
import com.project.bongoagro.Model.productDetailsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {

    private RecyclerView product_recyle_view;
    private RecyclerView.Adapter adapter;
    private List<productDetailsModel> allCategory;
    private String categoryId, back_id;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        product_recyle_view = findViewById(R.id.product_recyle_view);

        Intent intent = getIntent();
        if (intent.hasExtra("product_id")) {
            categoryId = intent.getStringExtra("product_id");
            back_id = intent.getStringExtra("back_id");
        }
        product_recyle_view.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        product_recyle_view.setLayoutManager(gridLayoutManager);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String token = "Bearer "+sharedPreferences.getString("token", "");


        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#057CA5"));
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<List<productDetailsModel>> subCategory = RetrofitClient
                .getInstance()
                .getApi()
                .product_details(token, Integer.parseInt(categoryId));
        subCategory.enqueue(new Callback<List<productDetailsModel>>() {
            @Override
            public void onResponse(Call<List<productDetailsModel>> call, Response<List<productDetailsModel>> response) {

                if(response.isSuccessful()) {
                    Log.e("API_ERROR", "Response Code: " + response.body().size());
                    progressDialog.dismiss();
                    if(response.body().size() < 1){
                        Toast.makeText(getApplicationContext(), "No data found!!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        allCategory = response.body();
                        back_id = allCategory.get(0).getSubCategoryId().toString();
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
                adapter = new AdapterClassProductDetails(allCategory, getApplicationContext());

                product_recyle_view.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<productDetailsModel>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("sub_category_id", String.valueOf(back_id));
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("sub_category_id", String.valueOf(back_id));
        startActivity(intent);
        finish();
    }

}