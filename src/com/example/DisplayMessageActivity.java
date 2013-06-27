package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {

    public final static String RELEASE_DATA = new StringBuilder(DisplayMessageActivity.class.getPackage().getName()).append(".RELEASE_DATA").toString();
    public final static String TITLE = new StringBuilder(DisplayMessageActivity.class.getPackage().getName()).append(".TITLE").toString();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        TextView textView = new TextView(this);
        textView.setVerticalScrollBarEnabled(true);
        textView.setTextSize(40);

        String message = intent.getStringExtra(RELEASE_DATA);
        textView.setText(message);

        setContentView(textView);
        setTitle(intent.getStringExtra(TITLE));
    }
}