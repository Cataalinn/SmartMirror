package com.nodomain.smartmirror.Providers.Weather.TheWeatherUnderground;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Florescu George Cătălin on 14.02.2016.
 * Smart Mirror project
 */
public interface TheWeatherUndergroundAPI {
    @GET("/api/8160d9703d72f1dc/conditions/q/RO/Pitesti.json")
    Call<TheWeatherUndergroundResponse> getWeather();
}
