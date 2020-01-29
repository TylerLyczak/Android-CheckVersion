package com.tylerlyczak.firstruncheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private void checkFirstOpen ()  {

        final String FILE_NAME = "VersionFile";
        final String APP_VERSION_CODE = "VersionCode";
        final int ERROR = -1;

        // Gets the current version of the app
        int currentVersion = BuildConfig.VERSION_CODE;

        // Gets the saved version
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        int savedVersion = sharedPreferences.getInt(APP_VERSION_CODE, ERROR);

        // Checks if the app is opened for the first time or is upgraded
        // The app is already updated and already opened
        if (currentVersion == savedVersion) {
            Toast.makeText(MainActivity.this, "Already Opened/Same Version", Toast.LENGTH_SHORT).show();
            return;
        }
        // This is the first time the user has opened the app
        else if (savedVersion == ERROR) {
            Toast.makeText(MainActivity.this, "First Time Opened", Toast.LENGTH_SHORT).show();
        }
        // The user downloaded an upgrade
        else if (currentVersion > savedVersion) {
            Toast.makeText(MainActivity.this, "Upgraded", Toast.LENGTH_SHORT).show();
        }
        // ERROR
        else    {
            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
        }

        sharedPreferences.edit().putInt(APP_VERSION_CODE, currentVersion).apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkFirstOpen();
    }
}
