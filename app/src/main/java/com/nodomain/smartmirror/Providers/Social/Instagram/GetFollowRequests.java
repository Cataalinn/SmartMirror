package com.nodomain.smartmirror.Providers.Social.Instagram;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Florescu George Cătălin on 11.02.2016.
 * Smart Mirror project
 */
public class GetFollowRequests extends AsyncTask<String, String, ArrayList> {
    private final String TAG = this.getClass().getName();

    @Override
    protected ArrayList doInBackground(final String... params) {
        final ArrayList<String> followRequests = new ArrayList<>();
        String name = "";
        try {
            URL url = new URL(params[0]);

            Log.e(TAG, "Opening URL " + url.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
            String response = streamToString(urlConnection.getInputStream());
            Log.e("Instagram response", response);
            JSONObject jsonObj = (JSONObject) new JSONTokener(response).nextValue();
            //name = jsonObj.getJSONObject("data").getString("username");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!name.isEmpty())
            followRequests.add("You have a request from @" + name);

        return followRequests;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        arrayList.size();
    }

    private String streamToString(InputStream is) throws IOException {
        String str = "";

        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is));

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                reader.close();
            } finally {
                is.close();
            }

            str = sb.toString();
        }

        return str;
    }
}
