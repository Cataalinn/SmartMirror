package com.nodomain.smartmirror.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.nodomain.smartmirror.NewsFeed.Fragment.NewsFeedFragment;
import com.nodomain.smartmirror.R;
import com.nodomain.smartmirror.Utils.FragmentUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentUtils.addFragment(this, new NewsFeedFragment(), R.id.content_frame);

        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
