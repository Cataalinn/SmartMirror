package com.nodomain.smartmirror.Widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nodomain.smartmirror.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Florescu George Cătălin on 03.02.2016.
 * Smart Mirror project
 */
public class NewsFeedWidget extends LinearLayout {

    private CircleImageView feedImage;
    private AutoScrollingTextView feedInfo;

    public NewsFeedWidget(Context context) {
        super(context);

        init(context);
    }

    public NewsFeedWidget(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public NewsFeedWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.news_feed_widget, this);

        feedImage = (CircleImageView) view.findViewById(R.id.feed_image_view);
        feedInfo = (AutoScrollingTextView) view.findViewById(R.id.feed_text_view);
    }

    public void setFeedImage(int feedResourceImage) {
        feedImage.setImageResource(feedResourceImage);
    }

    public ImageView getFeedImage() {
        return feedImage;
    }

    public void setFeedInfo(String feedInformation) {
        feedInfo.setText(feedInformation);
    }
}
