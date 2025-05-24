package com.project.bongoagro.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.bongoagro.Model.WeatherResponse;
import com.project.bongoagro.R;

import java.util.List;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {

    private final List<WeatherResponse.ForecastDay> forecastList;

    public WeatherForecastAdapter(List<WeatherResponse.ForecastDay> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherResponse.ForecastDay forecast = forecastList.get(position);

        holder.dateText.setText(forecast.getDate());
        holder.tempText.setText(forecast.getDayWeather().getMaxTempC() + "°C / " + forecast.getDayWeather().getMinTempC()+ "°C");
        holder.descText.setText(forecast.getDayWeather().getCondition().getText());
        holder.sunriseText.setText("Sunrise: "+forecast.getAstro().getSunrise());
        holder.sunsetText.setText("Sunset: "+forecast.getAstro().getSunset());

        Glide.with(holder.itemView.getContext())
                .load("https:" + forecast.getDayWeather().getCondition().getIcon())
                .into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateText, tempText, descText, sunriseText, sunsetText;
        ImageView weatherIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            tempText = itemView.findViewById(R.id.tempText);
            descText = itemView.findViewById(R.id.descText);
            weatherIcon = itemView.findViewById(R.id.weatherIcon);
            sunriseText = itemView.findViewById(R.id.sunriseText);
            sunsetText = itemView.findViewById(R.id.sunsetText);
        }
    }
}
