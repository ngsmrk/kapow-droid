package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MyActivity extends Activity
{
    private static final String TAG = MyActivity.class.getName();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.d(TAG, "Created");
    }

    public void sendMessage(View view) {
        Log.d(TAG, "Sending message");
    }
}
