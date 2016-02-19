package com.nodomain.smartmirror.NewsFeed.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nodomain.smartmirror.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Florescu George Cătălin on 03.02.2016.
 * Smart Mirror project
 */
public class NewsFeedRecyclerViewAdapter extends RecyclerView.Adapter<NewsFeedViewHolder> {

    private List<NewsFeed> mNewsFeeds;
    private Context context;

    public NewsFeedRecyclerViewAdapter(List<NewsFeed> mNewsFeeds, Context context) {
        this.mNewsFeeds = mNewsFeeds;
        this.context = context;
    }

    @Override
    public NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_feed_recycle_view, parent, false);
        return new NewsFeedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsFeedViewHolder holder, int position) {
        final NewsFeed newsFeed = mNewsFeeds.get(position);

        holder.itemView.setTag(newsFeed);

        if (!newsFeed.isHasUrl()) {
            holder.newsFeedWidget.setFeedImage(newsFeed.getFeeResourceId());
        } else {
            Picasso.with(context).load(Uri.parse(newsFeed.getUrl())).into(holder.newsFeedWidget.getFeedImage());
        }
        holder.newsFeedWidget.setFeedInfo(newsFeed.getFeedInformation());
    }

    @Override
    public int getItemCount() {
        return mNewsFeeds.size();
    }

    public void addNewsFeed(NewsFeed newsFeed) {
        mNewsFeeds.add(newsFeed);
        notifyItemInserted(mNewsFeeds.size());
    }

    public void scrollItem() {
        if (mNewsFeeds.size() > 0) {
            NewsFeed newsFeed = mNewsFeeds.get(0);
            mNewsFeeds.remove(0);
            notifyItemRemoved(0);
            mNewsFeeds.add(newsFeed);
        }
    }

    public void shuffleNews() {
        Collections.shuffle(mNewsFeeds);
        notifyDataSetChanged();
    }

    public void clearNews() {
        ArrayList<NewsFeed> copy = new ArrayList<>();
        copy.addAll(mNewsFeeds);
        mNewsFeeds.clear();

        for (NewsFeed newsFeed : copy) {
            if (!newsFeed.isNews()) {
                mNewsFeeds.add(newsFeed);
            }
        }
    }
}
