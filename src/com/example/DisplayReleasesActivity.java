package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayReleasesActivity extends Activity {
    private static final String TAG = DisplayReleasesActivity.class.getName();

    public final static String RELEASE_DATA = new StringBuilder(DisplayReleasesActivity.class.getPackage().getName()).append(".RELEASE_DATA").toString();
    public final static String TITLE = new StringBuilder(DisplayReleasesActivity.class.getPackage().getName()).append(".TITLE").toString();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        TextView textView = new TextView(this);
        textView.setVerticalScrollBarEnabled(true);
        textView.setTextSize(40);

        String resultString = intent.getStringExtra(RELEASE_DATA);

        String shippingDate = "";
        try {
            JSONObject jsonData = new JSONObject(resultString);
            shippingDate = (String) jsonData.get("shipping_date");
            final JSONArray jsonArray = jsonData.names();
            for (int i = 0; i < jsonArray.length(); i++) {
                Log.d(TAG, jsonArray.get(i).toString());
            }
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse response", e);
        }

        textView.setText(resultString);

        setContentView(textView);
        setTitle(intent.getStringExtra(TITLE).concat(" : ").concat(shippingDate));
    }
}