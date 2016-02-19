package com.nodomain.smartmirror.Providers.Weather.OpenWeather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Florescu George Cătălin on 24.10.2015.
 */
public class WeatherStatus {
    @SerializedName("description")
    public String description;

    @SerializedName("icon")
    public String icon;
}
