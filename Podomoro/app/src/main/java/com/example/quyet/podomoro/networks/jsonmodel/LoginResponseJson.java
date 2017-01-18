package com.example.quyet.podomoro.networks.jsonmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by quyet on 1/18/2017.
 */

public class LoginResponseJson {
    @SerializedName("code")
    private int code;
    @SerializedName("token")
    private String token;
    @SerializedName("message")
    private String message;




    public int getCode() {
        return code;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
