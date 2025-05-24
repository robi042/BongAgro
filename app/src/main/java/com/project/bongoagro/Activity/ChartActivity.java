
package com.project.bongoagro.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import com.project.bongoagro.Model.ChannelDataResponse;
import com.project.bongoagro.Model.Feed;
import com.project.bongoagro.R;

import java.util.ArrayList;
import java.util.List;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.components.XAxis;
import com.project.bongoagro.Retrofit.RetrofitClient;
import com.project.bongoagro.helper.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChartActivity extends AppCompatActivity {
    private LineChart tempChart, humidityChart, potassiumChart;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        tempChart = findViewById(R.id.tempChart);
        humidityChart = findViewById(R.id.humidityChart);
        potassiumChart = findViewById(R.id.potassiumChart);

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
        Call<ChannelDataResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getChartData(token, "Agro");
        call.enqueue(new Callback<ChannelDataResponse>() {
            @Override
            public void onResponse(Call<ChannelDataResponse> call, Response<ChannelDataResponse> response) {
                if(response.isSuccessful()) {
                    progressDialog.dismiss();
                    List<Feed> feeds = response.body().getFeeds();
                    setupChart(tempChart, feeds, "Temperature");
                    setupChart(humidityChart, feeds, "Humidity");
                    setupChart(potassiumChart, feeds, "Potassium");
                }
                else {
                    progressDialog.dismiss();
                    if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "Bad Request!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    } else if (response.code() == 500) {
                        Toast.makeText(getApplicationContext(), "Internal server error!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Session expired, Please login!", Toast.LENGTH_LONG).show();
                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        sessionManager.logout();
                    }
                }
            }

            @Override
            public void onFailure(Call<ChannelDataResponse> call, Throwable t) {

            }
        });
    }
    private void setupChart(LineChart chart, List<Feed> feeds, String type) {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < feeds.size(); i++) {
            Feed feed = feeds.get(i);
            float yVal = 0f;

            switch (type) {
                case "Temperature":
                    yVal = Float.parseFloat(feed.getTemperature());
                    break;
                case "Humidity":
                    yVal = Float.parseFloat(feed.getHumidity());
                    break;
                case "Potassium":
                    yVal = Float.parseFloat(feed.getPotassium());
                    break;
            }

            entries.add(new Entry(i, yVal));
        }

        LineDataSet dataSet = new LineDataSet(entries, type);
        dataSet.setColor(getResources().getColor(android.R.color.holo_red_dark));
        dataSet.setCircleColor(getResources().getColor(android.R.color.holo_red_dark));
        dataSet.setValueTextSize(10f);
        dataSet.setLineWidth(2f);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.getDescription().setText(type + " Chart");
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.animateX(1500);
        chart.invalidate();
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