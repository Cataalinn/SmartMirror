package com.nodomain.smartmirror.Providers.Weather.TheWeatherUnderground;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Florescu George Cătălin on 14.02.2016.
 * Smart Mirror project
 */
public class CurrentObservation {

    @SerializedName("display_location")
    public DisplayLocation displayLocation;

    @SerializedName("weather")
    public String weather;

    @SerializedName("relative_humidity")
    public String relativeHumidity;

    @SerializedName("temp_c")
    public Integer temperature;

    @SerializedName("wind_string")
    public String windInfo;

    @SerializedName("feelslike_c")
    public String temperatureRealFeel;

    @SerializedName("icon_url")
    public String iconURL;

    @SerializedName("UV")
    public String ultraVioletsIndex;
}
