package com.example.quyet.podomoro.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by quyet on 1/14/2017.
 */

public class SharedPrefs {
    private static final String SHARED_PREFS_NAME = "SP";
    private static final String LOGIN_KEY = "LOGIN";

    private static final String SETTING_KEY = "SETTING";
    private SharedPreferences sharedPreferences;
    Gson gson ;
    private static SharedPrefs instance;
    public static SharedPrefs getInstance() {
        return instance;
    }
    public static  void init(Context context){
        instance  = new SharedPrefs(context);
    }
    private SharedPrefs(Context context) {
        this.sharedPreferences = context.getSharedPreferences(
                SHARED_PREFS_NAME,
                Context.MODE_PRIVATE
        );
        gson = new Gson();
    }
    public void put(LoginCredentials loginCredentials){

        String loginJson = gson.toJson(loginCredentials);
        sharedPreferences.edit().putString(LOGIN_KEY, loginJson).commit();
    }
    public LoginCredentials getLoginCredentials(){
        String loginJson = sharedPreferences.getString(LOGIN_KEY, null);
        if (loginJson == null)
        {
            return null;
        }
        LoginCredentials loginCredentials  = gson.fromJson(loginJson, LoginCredentials.class);

        return  loginCredentials;
    }
    public void putSetting(Setting setting) {

        String loginJson = gson.toJson(setting);
        sharedPreferences.edit().putString(SETTING_KEY, loginJson).commit();
    }

    public Setting getSetting() {
        String settingJson = sharedPreferences.getString(SETTING_KEY, null);
        if (settingJson == null) {
            return null;
        }
        Setting setting = gson.fromJson(settingJson, Setting.class);

        return setting;
    }


}
