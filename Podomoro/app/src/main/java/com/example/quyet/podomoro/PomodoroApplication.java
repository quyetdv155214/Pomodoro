package com.example.quyet.podomoro;

import android.app.Application;
import android.util.Log;

import com.example.quyet.podomoro.databases.DBContext;
import com.example.quyet.podomoro.databases.models.Task;

/**
 * Created by quyet on 1/14/2017.
 */

public class PomodoroApplication extends Application {
    private static final String TAG =PomodoroApplication.class.toString() ;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: a");
//        for (Task task : DBContext.instance.allTask()){
//            Log.d(TAG, "Task : " + task.toString());
//        }
    }
}
