package com.nodomain.smartmirror.NewsFeed.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nodomain.smartmirror.R;
import com.nodomain.smartmirror.Widgets.NewsFeedWidget;

/**
 * Created by Florescu George Cătălin on 03.02.2016.
 * Smart Mirror project
 */
public class NewsFeedViewHolder extends RecyclerView.ViewHolder {

    public NewsFeedWidget newsFeedWidget;

    public NewsFeedViewHolder(View itemView) {
        super(itemView);

        newsFeedWidget = (NewsFeedWidget) itemView.findViewById(R.id.news_feed_widget);
    }
}
