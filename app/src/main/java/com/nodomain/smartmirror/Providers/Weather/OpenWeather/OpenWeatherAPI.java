package com.nodomain.smartmirror.Providers.Weather.OpenWeather;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
public interface OpenWeatherAPI {
    @GET("data/2.5/weather?q=Pitesti,ro&units=metric&type=accurate&appid=[your_api_key]&lang=ro")
    Call<WeatherObject> getWeather();
}
