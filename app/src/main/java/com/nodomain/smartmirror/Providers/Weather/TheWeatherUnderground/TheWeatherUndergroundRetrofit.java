package com.nodomain.smartmirror.Providers.Weather.TheWeatherUnderground;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Florescu George Cătălin on 14.02.2016.
 * Smart Mirror project
 */
public class TheWeatherUndergroundRetrofit {

    public static TheWeatherUndergroundAPI getAPI() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl("http://api.wunderground.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return restAdapter.create(TheWeatherUndergroundAPI.class);
    }
}
