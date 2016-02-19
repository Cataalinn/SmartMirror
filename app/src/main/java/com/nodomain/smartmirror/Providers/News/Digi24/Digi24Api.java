package com.nodomain.smartmirror.Providers.News.Digi24;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
public interface Digi24Api {
    @GET("/rss/Stiri/Digi24/")
    Call<Digi24RSS> getNews();
}
