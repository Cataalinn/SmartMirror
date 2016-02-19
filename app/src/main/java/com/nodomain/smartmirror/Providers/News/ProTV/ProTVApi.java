package com.nodomain.smartmirror.Providers.News.ProTV;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
public interface ProTVApi {
    @GET()
    Call<ProTvRSS> getNews(@Url String url);
}
