package com.nodomain.smartmirror.NewsFeed.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nodomain.smartmirror.NewsFeed.Adapter.NewsFeed;
import com.nodomain.smartmirror.NewsFeed.Adapter.NewsFeedRecyclerViewAdapter;
import com.nodomain.smartmirror.Providers.News.Digi24.Digi24Items;
import com.nodomain.smartmirror.Providers.News.Digi24.Digi24RSS;
import com.nodomain.smartmirror.Providers.News.Digi24.Digi24Retrofit;
import com.nodomain.smartmirror.Providers.News.ProTV.ProTVRetrofit;
import com.nodomain.smartmirror.Providers.News.ProTV.ProTvItems;
import com.nodomain.smartmirror.Providers.News.ProTV.ProTvNewsChecker;
import com.nodomain.smartmirror.Providers.News.ProTV.ProTvRSS;
import com.nodomain.smartmirror.Providers.Weather.TheWeatherUnderground.TheWeatherUndergroundResponse;
import com.nodomain.smartmirror.Providers.Weather.TheWeatherUnderground.TheWeatherUndergroundRetrofit;
import com.nodomain.smartmirror.R;
import com.nodomain.smartmirror.Utils.CalendarContentResolver;
import com.nodomain.smartmirror.Widgets.WeatherWidget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends Fragment {

    @Bind(R.id.news_feed_recycler_view)
    RecyclerView newsFeedRecyclerView;

    @Bind(R.id.time_widget)
    TextView time;

    @Bind(R.id.weather_widget)
    WeatherWidget weatherWidget;

    private NewsFeedRecyclerViewAdapter mAdapter;
    private BroadcastReceiver mBroadcastReceiver;
    private List<NewsFeed> mNewsFeedsList;
    private final static long SECOND = 1000;
    private final static long MINUTE = 60 * SECOND;
    private final static long HALF_HOUR = 30 * MINUTE;
    private final static long HOUR = 2 * HALF_HOUR;
    private final static long DAY = HOUR * 24;

    @Override
    public void onStart() {
        super.onStart();
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context ctx, Intent intent) {
                if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0)
                    time.setText(new SimpleDateFormat("HH:mm", Locale.US).format(new Date()));
            }
        };

        getActivity().registerReceiver(mBroadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBroadcastReceiver != null)
            getActivity().unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mNewsFeedsList = new ArrayList<>();

        updateCalendar();
        updateNews();
        updateWeather();

        initRecyclerView();

        scrollEvents();
    }

    private void initRecyclerView() {
        mAdapter = new NewsFeedRecyclerViewAdapter(mNewsFeedsList, getContext());

        final LinearLayoutManager layoutParams = new LinearLayoutManager(getActivity());
        newsFeedRecyclerView.setLayoutManager(layoutParams);
        newsFeedRecyclerView.setHasFixedSize(true);
        newsFeedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        newsFeedRecyclerView.setAdapter(mAdapter);
    }

    private void scrollEvents() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        mAdapter.scrollItem();
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, MINUTE);
    }

    private void updateWeather() {
        final Handler handler = new Handler();
        Timer timer = new Timer();

        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        TheWeatherUndergroundRetrofit.getAPI().getWeather().enqueue(new Callback<TheWeatherUndergroundResponse>() {
                            @Override
                            public void onResponse(Call<TheWeatherUndergroundResponse> call, Response<TheWeatherUndergroundResponse> response) {
                                weatherWidget.setWeather(response.body(), getContext());
                            }

                            @Override
                            public void onFailure(Call<TheWeatherUndergroundResponse> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, HOUR); //execute in every 20s
    }

    private void updateNews() {
        final Handler handler = new Handler();
        Timer timer = new Timer();

        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        mAdapter.clearNews();

                        ProTVRetrofit.getAPI().getNews("").enqueue(new Callback<ProTvRSS>() {
                            @Override
                            public void onResponse(Call<ProTvRSS> call, Response<ProTvRSS> response) {
                                List<ProTvItems> proTvRSS = response.body().proTvChannel.proTvItems;

                                for (ProTvItems proTvItems : proTvRSS) {
                                    if (ProTvNewsChecker.isTodayNews(proTvItems.publicationDate)) {
                                        mNewsFeedsList.add(new NewsFeed(proTvItems.title, proTvItems.imageUrl.imageUrl, true));
                                    }
                                }

                                mAdapter.shuffleNews();
                            }

                            @Override
                            public void onFailure(Call<ProTvRSS> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });

                        Digi24Retrofit.getAPI().getNews().enqueue(new Callback<Digi24RSS>() {
                            @Override
                            public void onResponse(Call<Digi24RSS> call, Response<Digi24RSS> response) {
                                List<Digi24Items> digi24Itemses = response.body().digi24Channel.digi24Items;

                                for (Digi24Items digi24Items : digi24Itemses) {
                                    if (ProTvNewsChecker.isTodayNews(digi24Items.publicationDate)) {
                                        mNewsFeedsList.add(new NewsFeed(digi24Items.title, digi24Items.imageUrl.imageUrl, true));
                                    }
                                }
                                mAdapter.shuffleNews();
                            }

                            @Override
                            public void onFailure(Call<Digi24RSS> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, HALF_HOUR); //execute in every 20s
    }

    private void updateCalendar() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        final CalendarContentResolver calendar = new CalendarContentResolver(getActivity());
                        mNewsFeedsList.addAll(calendar.getEvents());
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, DAY); //execute in every 20s
    }
}
