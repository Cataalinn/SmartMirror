package com.nodomain.smartmirror.Providers.News.ProTV;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
public class ProTVRetrofit {

    public static ProTVApi getAPI() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl("http://rss.stirileprotv.ro")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        return restAdapter.create(ProTVApi.class);
    }
}
