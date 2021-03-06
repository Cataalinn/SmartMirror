package com.nodomain.smartmirror.Widgets;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Florescu George Cătălin on 04.02.2016.
 * Smart Mirror project
 */
public class AutoScrollingTextView extends TextView {
    public AutoScrollingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public AutoScrollingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoScrollingTextView(Context context) {
        super(context);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused) {
            super.onFocusChanged(true, direction, previouslyFocusedRect);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean focused) {
        if (focused) {
            super.onWindowFocusChanged(true);
        }
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}