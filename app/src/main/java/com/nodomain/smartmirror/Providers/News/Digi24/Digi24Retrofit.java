package com.nodomain.smartmirror.Providers.News.Digi24;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
public class Digi24Retrofit {

    public static Digi24Api getAPI() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl("http://www.digi24.ro")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        return restAdapter.create(Digi24Api.class);
    }
}
