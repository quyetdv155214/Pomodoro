package com.example.quyet.podomoro;

import android.app.Application;
import android.util.Log;

/**
 * Created by quyet on 1/14/2017.
 */

public class PomodoroApplication extends Application {
    private static final String TAG =PomodoroApplication.class.toString() ;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: a");
    }
}
