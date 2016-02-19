package com.nodomain.smartmirror.Providers.Weather.OpenWeather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Florescu George Cătălin on 24.10.2015.
 */
public class WeatherWind {
    @SerializedName("speed")
    public Double windSpeed;

    @SerializedName("deg")
    public Double windDirection;
}
