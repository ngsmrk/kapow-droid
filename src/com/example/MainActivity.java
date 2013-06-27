package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getName();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.d(TAG, "Created");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "Destroyed");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "Started");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "Stopped");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "Paused");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "Resumed");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "Saved Instance State");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "Restored Instance State");
    }

    public void getNewReleases(View view) throws JSONException, IOException {

        Log.d(TAG, "Getting new releases");

        String url = "http://kapow-api.herokuapp.com/releases/new.json";
        final String title = "New Releases";

        loadReleaseData(url, title);
    }

    public void getUpcomingReleases(View view) throws JSONException, IOException {

        Log.d(TAG, "Getting upcoming releases");

        String url = "http://kapow-api.herokuapp.com/releases/upcoming.json";
        final String title = "Upcoming Releases";

        loadReleaseData(url, title);
    }

    private void loadReleaseData(String url, String title) throws IOException, JSONException {
        JSONObject jsonData = fetchData(url);

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(DisplayMessageActivity.RELEASE_DATA, jsonData.toString());
        intent.putExtra(DisplayMessageActivity.TITLE, title);

        startActivity(intent);
    }

    private JSONObject fetchData(String url) throws IOException, JSONException {

        // TODO should be done async
        JSONObject jsonData = null;

        final boolean networkAvailable = new NetworkManager().isNetworkAvailable(this);
        Log.d(TAG, "Network available: " + networkAvailable);

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpPostRequest = new HttpGet(url);

        // Set HTTP parameters
        httpPostRequest.setHeader("Accept", "application/json");
        httpPostRequest.setHeader("Content-type", "application/json");
        httpPostRequest.setHeader("Accept-Encoding", "gzip"); // only set this parameter if you would like to use gzip compression
        long t = System.currentTimeMillis();
        HttpResponse response = httpclient.execute(httpPostRequest);

        Log.i(TAG, "HTTPResponse received in [" + (System.currentTimeMillis() - t) + "ms]");

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            Header contentEncoding = response.getFirstHeader("Content-Encoding");
            if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                instream = new GZIPInputStream(instream);
            }

            String resultString = convertStreamToString(instream);
            instream.close();

            Log.i(TAG, "HTTPResponse content: " + resultString);

            jsonData = new JSONObject(resultString);
            Log.i(TAG, "<JSONObject>\n" + jsonData.toString() + "\n</JSONObject>");
        }

        return jsonData;
    }

    private String convertStreamToString(InputStream is) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } finally {
            is.close();
        }
        return sb.toString();
    }
}
