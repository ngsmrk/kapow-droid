package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getName();

    public final static String EXTRA_MESSAGE = new StringBuilder(MainActivity.class.getPackage().getName()).append("EXTRA_MESSAGE").toString();

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

    public void sendMessage(View view) {
        Log.d(TAG, "Sending message");

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
