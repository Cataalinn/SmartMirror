package com.nodomain.smartmirror.Providers.Weather.OpenWeather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
public class OpenWeatherRetrofit {
    public static OpenWeatherAPI getAPI() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return restAdapter.create(OpenWeatherAPI.class);
    }
}
