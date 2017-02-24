package com.example.quyet.podomoro.ultil;

import android.util.Log;

import com.example.quyet.podomoro.databases.models.Task;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by quyetdv on 2/15/2017.
 */

public class Utils {
//    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final Utils instance = new Utils();
    private static final String TAG = "Utils";

    private Utils (){

    }
    public String getUUID(){
        String uuid = UUID.randomUUID().toString();
        Log.d(TAG, "getUUID: " + uuid);
        return uuid;
    }

}
