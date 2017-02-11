package com.example.quyet.podomoro.networks.services;

import com.example.quyet.podomoro.networks.jsonmodel.RegisterResponseJson;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by quyet on 1/22/2017.
 */

public interface RegisterService {
    @POST("register")
    Call<RegisterResponseJson> register(@Body RequestBody body);
}
