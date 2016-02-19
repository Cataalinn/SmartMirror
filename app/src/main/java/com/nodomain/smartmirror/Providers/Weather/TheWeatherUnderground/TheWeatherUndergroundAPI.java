package com.nodomain.smartmirror.Providers.Weather.TheWeatherUnderground;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Florescu George Cătălin on 14.02.2016.
 * Smart Mirror project
 */
public interface TheWeatherUndergroundAPI {
    @GET("/api/your_api_key/conditions/q/RO/Pitesti.json")
    Call<TheWeatherUndergroundResponse> getWeather();
}
