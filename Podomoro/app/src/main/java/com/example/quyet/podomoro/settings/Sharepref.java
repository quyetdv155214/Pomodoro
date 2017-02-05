package com.example.quyet.podomoro.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by quyet on 1/14/2017.
 */

public class Sharepref {
    private static final String SHARED_PREFS_NAME = "SP";
    private static final String LOGIN_KEY = "login";
    private SharedPreferences sharedPreferences;
    Gson gson ;
    private static Sharepref instance;
    public static Sharepref getInstance() {
        return instance;
    }
    public static  void init(Context context){
        instance  = new Sharepref(context);
    }
    private Sharepref(Context context) {
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

    public  String getAccessToken(){
        return (getLoginCredentials() != null) ? getLoginCredentials().getAccessToken() : null;
    }

}
