package com.example.quyet.podomoro.networks.jsonmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by quyet on 1/22/2017.
 */

public class RegisterResponseJson {
    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
