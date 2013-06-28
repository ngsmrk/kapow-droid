package com.kapow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisplayReleasesActivity extends Activity {
    private static final String TAG = DisplayReleasesActivity.class.getName();

    public final static String RELEASE_DATA = new StringBuilder(DisplayReleasesActivity.class.getPackage().getName()).append(".RELEASE_DATA").toString();
    public final static String TITLE = new StringBuilder(DisplayReleasesActivity.class.getPackage().getName()).append(".TITLE").toString();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();
        final String resultString = intent.getStringExtra(RELEASE_DATA);

        List<String> titles = new ArrayList<String>();
        String shippingDate = "";
        try {
            JSONObject jsonData = new JSONObject(resultString);
            shippingDate = (String) jsonData.get("shipping_date");
            final JSONArray jsonArray = jsonData.getJSONArray("comix");
            for (int i = 0; i < jsonArray.length(); i++) {
                final String title = ((JSONObject) jsonArray.get(i)).get("title").toString();
                Log.d(TAG, title);
                titles.add(title);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse response", e);
        }

        String[] array = titles.toArray((new String[0]));
        Log.d(TAG, new Integer(array.length).toString());

        setContentView(R.layout.release_list_view);

        setTitle(intent.getStringExtra(TITLE).concat(" : ").concat(shippingDate));
    }
}