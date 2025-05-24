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
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.bongoagro.Model.SellerTypeModel;
import com.project.bongoagro.Model.TranspotationModel;
import com.project.bongoagro.R;
import com.project.bongoagro.Retrofit.RetrofitClient;
import com.project.bongoagro.helper.SessionManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransportActivity extends AppCompatActivity {
    private EditText departureStation, destination, rent, contact;
    ProgressDialog progressDialog;
    private Spinner vehicleType;
    private Button submit_btn;
    private List<String> categories;
    int vehicleIdR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        departureStation = findViewById(R.id.departureStation);
        destination = findViewById(R.id.destination);
        rent = findViewById(R.id.rent);
        contact = findViewById(R.id.contact);
        vehicleType = findViewById(R.id.vehicleTypeId);
        submit_btn = findViewById(R.id.submit_btn);

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

        Call<TranspotationModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .getTranportInfo(token);
        call.enqueue(new Callback<TranspotationModel>() {
            @Override
            public void onResponse(Call<TranspotationModel> call, Response<TranspotationModel> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    departureStation.setText(response.body().getDepartureStation());
                    destination.setText(response.body().getDestination());
                    rent.setText(String.valueOf(response.body().getRent()));
                    contact.setText((response.body().getContactNumber()));

                    Call<List<SellerTypeModel>> call2 = RetrofitClient
                            .getInstance()
                            .getApi()
                            .getVehicleType(token);

                    call2.enqueue(new Callback<List<SellerTypeModel>>() {
                        @Override
                        public void onResponse(Call<List<SellerTypeModel>> call, Response<List<SellerTypeModel>> response2) {
                            if(response2.isSuccessful() && response2.body() != null){
                                List<SellerTypeModel> sellerTpeList = response2.body();
                                categories = new ArrayList<>();
                                int selectedIndex = 0;

                                int selectedAreaId = response.body().getVehicleTypeId();

                                for (int i = 0; i < sellerTpeList.size(); i++) {
                                    categories.add(sellerTpeList.get(i).getName());

                                    if (sellerTpeList.get(i).getId().equals(selectedAreaId)) {
                                        selectedIndex = i;
                                    }
                                }

                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, categories);
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                vehicleType.setAdapter(dataAdapter);
                                vehicleType.setSelection(selectedIndex);

                                vehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        vehicleIdR = sellerTpeList.get(position).getId();

                                        // You can use this ID wherever needed
                                        Log.d("SelectedArea", "ID: " + vehicleIdR );
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
            public void onFailure(Call<TranspotationModel> call, Throwable t) {

            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Updating...");
                progressDialog.show();
                TranspotationModel transpotationModel = new TranspotationModel(
                        0,
                        vehicleIdR,
                        departureStation.getText().toString(),
                        destination.getText().toString(),
                        Double.valueOf(rent.getText().toString()),
                        contact.getText().toString(),
                        Integer.parseInt(userId)
                );

                Call<ResponseBody> call1 = RetrofitClient
                        .getInstance()
                        .getApi()
                        .tranportUpdate(transpotationModel, token);
                call1.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            Toast.makeText(TransportActivity.this,
                                    "Updated successfully!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), SellerActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(TransportActivity.this,
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        return true;
    }
}