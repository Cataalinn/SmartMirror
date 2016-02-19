package com.nodomain.smartmirror.Providers.Weather.OpenWeather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Florescu George Cătălin on 24.10.2015.
 * <p/>
 * WeatherObject - response from server will be converted in this object.
 * All information will be available in this object and can be display to view in CardView
 */
public class WeatherObject {

    @SerializedName("weather")
    public List<WeatherStatus> weatherStatus;

    @SerializedName("main")
    public WeatherIndicators weatherIndicators;

    @SerializedName("wind")
    public WeatherWind weatherWind;

    @SerializedName("name")
    public String searchedPlace;
}

