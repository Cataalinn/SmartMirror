package com.nodomain.smartmirror.Widgets;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nodomain.smartmirror.Providers.Weather.TheWeatherUnderground.TheWeatherUndergroundResponse;
import com.nodomain.smartmirror.Providers.Weather.OpenWeather.WeatherObject;
import com.nodomain.smartmirror.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
public class WeatherWidget extends RelativeLayout {

    private ImageView weatherIconIv;
    private TextView temperatureValueTv, weatherDescriptionTv, placeTv, windTv;

    public WeatherWidget(Context context) {
        super(context);
        init(context);
    }

    public WeatherWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WeatherWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.temperature_widget, this);

        weatherIconIv = (ImageView) view.findViewById(R.id.temperature_image_view);
        temperatureValueTv = (AutoScrollingTextView) view.findViewById(R.id.temperature_value_text_view);
        weatherDescriptionTv = (AutoScrollingTextView) view.findViewById(R.id.temperature_description_text_view);
        placeTv = (AutoScrollingTextView) view.findViewById(R.id.temperature_place);
        windTv = (AutoScrollingTextView) view.findViewById(R.id.temperature_max_min_text_view);
    }

    public void setWeather(WeatherObject weather, Context context) {
        String weatherNow = weather.weatherStatus.get(0).description;
        String now = "Now: " + String.valueOf(weather.weatherIndicators.temperature) + "°C";
        String wind = "Wind from " + weather.weatherWind.windDirection + " at " + weather.weatherWind.windSpeed;
        String place = weather.searchedPlace;

        placeTv.setText(place);
        temperatureValueTv.setText(now);
        weatherDescriptionTv.setText(weatherNow);
        windTv.setText(wind);

        try {
            InputStream ins = getResources().openRawResource(getResources().getIdentifier("w" + weather.weatherStatus.get(0).icon, "raw", context.getPackageName()));
            weatherIconIv.setImageBitmap(BitmapFactory.decodeStream(ins));
        } catch (Exception e) {
            weatherIconIv.setImageResource(R.mipmap.ic_launcher);
        }
    }

    public void setWeather(TheWeatherUndergroundResponse response, Context context) {
        String wind = "Wind: " + response.currentObservation.windInfo;
        String now = "Now: " + String.valueOf(response.currentObservation.temperature) + "°C Real feel: " + String.valueOf(response.currentObservation.temperatureRealFeel) + "°C";
        String placeString = response.currentObservation.displayLocation.location;
        String weatherNow = "Now: " + response.currentObservation.weather;

        placeTv.setText(placeString);
        temperatureValueTv.setText(weatherNow);
        weatherDescriptionTv.setText(now);
        windTv.setText(wind);

        Picasso.with(context).load(response.currentObservation.iconURL).into(weatherIconIv);
    }
}
