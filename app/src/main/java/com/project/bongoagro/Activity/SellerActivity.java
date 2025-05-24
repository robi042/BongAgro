package com.project.bongoagro.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import com.project.bongoagro.Model.AreaModel;
import com.project.bongoagro.Model.ProductTypeModel;
import com.project.bongoagro.Model.SellerModel;
import com.project.bongoagro.Model.SellerTypeModel;
import com.project.bongoagro.Model.SellerUpdateRequest;
import com.project.bongoagro.R;
import com.project.bongoagro.Retrofit.RetrofitClient;
import com.project.bongoagro.helper.SessionManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerActivity extends AppCompatActivity {
    private EditText name, contact;
    ProgressDialog progressDialog;
    private Spinner area, sellerType, productType;
    private Button submit_btn;
    private List<String> categories, categories1, categories2;
    int areaIdR, sellerTypeIdR, productTypeIdR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        submit_btn = findViewById(R.id.submit_btn);
        area = findViewById(R.id.area);
        sellerType = findViewById(R.id.sellerType);
        productType = findViewById(R.id.productType);

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

        Call<SellerModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .getSellerInfo(token);
        call.enqueue(new Callback<SellerModel>() {
            @Override
            public void onResponse(Call<SellerModel> call, Response<SellerModel> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                   name.setText(response.body().getName());
                   contact.setText(response.body().getContactNumber());

                    Call<List<AreaModel>> call1 = RetrofitClient
                            .getInstance()
                            .getApi()
                            .getSellerArea(token);
                    call1.enqueue(new Callback<List<AreaModel>>() {
                        @Override
                        public void onResponse(Call<List<AreaModel>> call, Response<List<AreaModel>> response1) {
                            if(response1.isSuccessful() && response1.body() != null){
                                List<AreaModel> areaList = response1.body();
                                categories = new ArrayList<>();
                                int selectedIndex = 0;

                                int selectedAreaId = response.body().getAreaId();

                                for (int i = 0; i < areaList.size(); i++) {
                                    categories.add(areaList.get(i).getName());

                                    if (areaList.get(i).getId().equals(selectedAreaId)) {
                                        selectedIndex = i;
                                    }
                                }

                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, categories);
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                area.setAdapter(dataAdapter);
                                area.setSelection(selectedIndex);

                                area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        areaIdR = areaList.get(position).getId();

                                        // You can use this ID wherever needed
                                        Log.d("SelectedArea", "ID: " + areaIdR );
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        // Optional: handle case when nothing is selected
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<List<AreaModel>> call, Throwable t) {

                        }
                    });

                    Call<List<SellerTypeModel>> call2 = RetrofitClient
                            .getInstance()
                            .getApi()
                            .getSellerType(token);

                    call2.enqueue(new Callback<List<SellerTypeModel>>() {
                        @Override
                        public void onResponse(Call<List<SellerTypeModel>> call, Response<List<SellerTypeModel>> response2) {
                            if(response2.isSuccessful() && response2.body() != null){
                                List<SellerTypeModel> sellerTpeList = response2.body();
                                categories1 = new ArrayList<>();
                                int selectedIndex = 0;

                                int selectedAreaId = response.body().getSellerTypeId();

                                for (int i = 0; i < sellerTpeList.size(); i++) {
                                    categories1.add(sellerTpeList.get(i).getName());

                                    if (sellerTpeList.get(i).getId().equals(selectedAreaId)) {
                                        selectedIndex = i;
                                    }
                                }

                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, categories1);
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sellerType.setAdapter(dataAdapter);
                                sellerType.setSelection(selectedIndex);

                                sellerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        sellerTypeIdR = sellerTpeList.get(position).getId();

                                        // You can use this ID wherever needed
                                        Log.d("SelectedArea", "ID: " + sellerTypeIdR );
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        // Optional: handle case when nothing is selected
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<List<SellerTypeModel>> call, Throwable t) {

                        }
                    });

                    Call<List<ProductTypeModel>> call3 = RetrofitClient
                            .getInstance()
                            .getApi()
                            .getProductType(token);
                    call3.enqueue(new Callback<List<ProductTypeModel>>() {
                        @Override
                        public void onResponse(Call<List<ProductTypeModel>> call, Response<List<ProductTypeModel>> response3) {
                            if(response3.isSuccessful() && response3.body() != null){
                                List<ProductTypeModel> sellerTpeList = response3.body();
                                categories2 = new ArrayList<>();
                                int selectedIndex = 0;

                                int selectedAreaId = response.body().getSellerTypeId();

                                for (int i = 0; i < sellerTpeList.size(); i++) {
                                    categories2.add(sellerTpeList.get(i).getName());

                                    if (sellerTpeList.get(i).getId().equals(selectedAreaId)) {
                                        selectedIndex = i;
                                    }
                                }

                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, categories2);
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                productType.setAdapter(dataAdapter);
                                productType.setSelection(selectedIndex);

                                productType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        productTypeIdR = sellerTpeList.get(position).getId();

                                        // You can use this ID wherever needed
                                        Log.d("SelectedArea", "ID: " + productTypeIdR );
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        // Optional: handle case when nothing is selected
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<List<ProductTypeModel>> call, Throwable t) {

                        }
                    });

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
            }

            @Override
            public void onFailure(Call<SellerModel> call, Throwable t) {

            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Updating...");
                progressDialog.show();

                SellerUpdateRequest sellerUpdateRequest = new SellerUpdateRequest(
                        0,
                        name.getText().toString(),
                        areaIdR,
                        sellerTypeIdR,
                        productTypeIdR,
                        contact.getText().toString(),
                        Integer.parseInt(userId)
                );

                Call<ResponseBody> call5 = RetrofitClient
                        .getInstance()
                        .getApi()
                        .sellerUpdate(sellerUpdateRequest, token);
                call5.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            Toast.makeText(SellerActivity.this,
                                    "Updated successfully!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), SellerActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(SellerActivity.this,
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