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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.bongoagro.Adapter.AdapterClassProduct;
import com.project.bongoagro.Adapter.AdapterClassProductPrice;
import com.project.bongoagro.Model.PriceItem;
import com.project.bongoagro.Model.PriceResponse;
import com.project.bongoagro.Model.PriceUpdateRequest;
import com.project.bongoagro.Model.ProductModel;
import com.project.bongoagro.Model.SellerTypeModel;
import com.project.bongoagro.R;
import com.project.bongoagro.Retrofit.RetrofitClient;
import com.project.bongoagro.helper.SessionManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriceActivity extends AppCompatActivity {
    private EditText price;
    ProgressDialog progressDialog;
    private Spinner productId, productId1;
    private Button submit_btn;
    private List<String> categories;
    private List<PriceItem> priceItems;
    int product_id, sub_category_id, product_id1;
    private RecyclerView priceRecyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        price = findViewById(R.id.price);
        productId = findViewById(R.id.productId);
        productId1 = findViewById(R.id.productId1);
        priceRecyclerView = findViewById(R.id.priceRecyclerView);
        submit_btn = findViewById(R.id.submit_btn);

        priceRecyclerView.setHasFixedSize(true);
        priceRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", "");
        String userId = sharedPreferences.getString("userId", "");


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

        Call<List<ProductModel>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getProducts(token);
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                progressDialog.dismiss();
                if(response.isSuccessful() && response.body() != null){
                    List<ProductModel> sellerTpeList = response.body();
                    categories = new ArrayList<>();
                    int selectedIndex = 0;


                    for (int i = 0; i < sellerTpeList.size(); i++) {
                        categories.add(sellerTpeList.get(i).getName());
                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, categories);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    productId.setAdapter(dataAdapter);
                    productId.setSelection(selectedIndex);
                    productId1.setAdapter(dataAdapter);
                    productId1.setSelection(selectedIndex);

                    productId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            product_id = sellerTpeList.get(position).getId();
                            sub_category_id = sellerTpeList.get(position).getSubCategoryId();

                            // You can use this ID wherever needed

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Optional: handle case when nothing is selected
                        }
                    });

                    productId1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            progressDialog.show();
                            product_id1 = sellerTpeList.get(position).getId();
                            Log.d("SelectedArea", "ID: " + product_id1 );

                            Call<PriceResponse> call1 = RetrofitClient
                                    .getInstance()
                                    .getApi()
                                    .getPriceList(token, product_id1);
                            call1.enqueue(new Callback<PriceResponse>() {
                                @Override
                                public void onResponse(Call<PriceResponse> call, Response<PriceResponse> response) {
                                    if(response.isSuccessful()) {
                                        progressDialog.dismiss();
                                        if(response.body().getData().size() < 1){
                                            Toast.makeText(getApplicationContext(), "No data found!!", Toast.LENGTH_LONG).show();
                                        }else {
                                            priceItems = response.body().getData();
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
                                    adapter = new AdapterClassProductPrice(priceItems, getApplicationContext());

                                    priceRecyclerView.setAdapter(adapter);
                                }

                                @Override
                                public void onFailure(Call<PriceResponse> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Log.d("SelectedArea", "list: " + t );
                                }
                            });

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Optional: handle case when nothing is selected
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Updating...");
                progressDialog.show();
                PriceUpdateRequest priceUpdateRequest = new PriceUpdateRequest(
                        0,
                        product_id,
                        Double.parseDouble(price.getText().toString()),
                        Integer.parseInt(userId)

                );
                Call<ResponseBody> productModelCall = RetrofitClient
                        .getInstance()
                        .getApi()
                        .priceUpdate(priceUpdateRequest, token);
                productModelCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            Toast.makeText(PriceActivity.this,
                                    "Updated successfully!",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(PriceActivity.this,
                                    "Updated failed!",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}