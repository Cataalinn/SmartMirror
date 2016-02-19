package com.nodomain.smartmirror.NewsFeed.Adapter;

/**
 * Created by Florescu George Cătălin on 03.02.2016.
 * Smart Mirror project
 */
public class NewsFeed {
    private int mFeeResourceId;
    private String mFeedInformation;
    private boolean hasUrl = false;
    private String url;
    private boolean news;


    public NewsFeed(String feedInformation, String url, boolean isNews) {
        this.mFeedInformation = feedInformation;
        this.hasUrl = true;
        this.url = url;
        this.news = isNews;
    }

    public NewsFeed(String feedInformation, int feeResourceId) {
        this.mFeeResourceId = feeResourceId;
        this.mFeedInformation = feedInformation;
        this.hasUrl = false;
    }

    public int getFeeResourceId() {
        return mFeeResourceId;
    }

    public void setFeeResourceId(int feeResourceId) {
        this.mFeeResourceId = feeResourceId;
    }

    public String getFeedInformation() {
        return mFeedInformation;
    }

    public void setFeedInformation(String feedInformation) {
        this.mFeedInformation = feedInformation;
    }

    public boolean isHasUrl() {
        return hasUrl;
    }

    public String getUrl() {
        return url;
    }

    public boolean isNews() {
        return news;
    }


}
