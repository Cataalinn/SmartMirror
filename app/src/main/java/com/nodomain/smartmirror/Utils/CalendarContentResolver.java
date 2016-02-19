package com.nodomain.smartmirror.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.nodomain.smartmirror.NewsFeed.Adapter.NewsFeed;
import com.nodomain.smartmirror.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Florescu George Cătălin on 04.02.2016.
 * Smart Mirror project
 */
public class CalendarContentResolver {
    public static final String[] FIELDS = {"calendar_id", "title", "description", "dtstart", "dtend", "eventLocation"};


    public static final Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/events");

    ContentResolver contentResolver;
    List<String> calendars = new ArrayList<>();

    public CalendarContentResolver(Context ctx) {
        contentResolver = ctx.getContentResolver();
    }

    private List<String> get() {

        Cursor cursor = contentResolver.query(CALENDAR_URI, FIELDS, null, null, "dtstart");
        if (cursor != null) {
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String id = cursor.getString(0);
                        String displayName = cursor.getString(1);
                        String description = cursor.getString(2);
                        String start = cursor.getString(3);
                        String end = cursor.getString(4);
                        String location = cursor.getString(5);

                        if (start != null) {
                            if (eventIsInFuture(start) && eventIsNext2Days(start)) {
                                StringBuilder stringBuilder = new StringBuilder();

                                if (!displayName.isEmpty()) {
                                    stringBuilder.append("Event: ").append(displayName).append(" ");
                                }
                                if (!description.isEmpty()) {
                                    stringBuilder.append(": ").append(description);
                                }
                                if (!getDate(start).isEmpty()) {
                                    stringBuilder.append(" | Starting on: ").append(getDate(start));
                                }
                                if (!getDate(end).isEmpty()) {
                                    stringBuilder.append(" -- Ending on: ").append(getDate(end));
                                }
                                if (location != null && !location.isEmpty()) {
                                    stringBuilder.append(" | Location: ").append(location);
                                }

                                if (!calendars.contains(stringBuilder.toString()))
                                    calendars.add(stringBuilder.toString());
                            }
                        }
                    }
                }

                if (calendars.size() == 0) {
                    calendars.add("There are no events in 2 days starting now");
                }
            } catch (AssertionError ex) {
                ex.printStackTrace();
            } finally {
                cursor.close();
            }
        }

        return calendars;
    }


    public static String getDate(String milliSeconds) {
        if (milliSeconds != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.US);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(milliSeconds));
            return formatter.format(calendar.getTime());
        } else {
            return "";
        }
    }

    private boolean eventIsInFuture(String start) {
        return new Date(Long.parseLong(start)).after(new Date(System.currentTimeMillis()));
    }

    private boolean eventIsNext2Days(String start) {
        return new Date(Long.parseLong(start)).before(nextDay());
    }

    private Date nextDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public List<NewsFeed> getEvents() {
        final List<NewsFeed> newsFeeds = new ArrayList<>();
        for (String s : get()) {
            newsFeeds.add(new NewsFeed(s, R.mipmap.calendar));
        }

        return newsFeeds;
    }
}