package com.project.bongoagro.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientWeather {
    private static final String BASE_URL="https://api.weatherapi.com/v1/";
    private static RetrofitClientWeather instance;
    private Retrofit retrofit;

    private RetrofitClientWeather(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClientWeather getInstance(){
        if(instance == null){
            instance = new RetrofitClientWeather();
        }
        return instance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
