package com.nodomain.smartmirror.Providers.Weather.TheWeatherUnderground;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Florescu George Cătălin on 14.02.2016.
 * Smart Mirror project
 */
public class TheWeatherUndergroundResponse {

    @SerializedName("current_observation")
    public CurrentObservation currentObservation;
}
