package com.nodomain.smartmirror.Providers.Weather.OpenWeather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Florescu George Cătălin on 24.10.2015.
 */
public class WeatherIndicators {
    @SerializedName("temp")
    public Double temperature;

    @SerializedName("pressure")
    public Double pressure;

    @SerializedName("humidity")
    public Double humidity;

    @SerializedName("temp_min")
    public Double minimumTemperature;

    @SerializedName("temp_max")
    public Double maximumTemperature;

    @SerializedName("sea_level")
    public Double seaLevel;
}
