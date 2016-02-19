package com.nodomain.smartmirror.Providers.News.Digi24;

import java.util.Calendar;

/**
 * Created by Florescu George Cătălin on 13.02.2016.
 * Smart Mirror project
 */
public class Digi24NewsChecker {
    public static boolean isTodayNews(String date) {
        return date.split(" ")[1].equals(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
    }
}
