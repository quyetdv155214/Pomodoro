package com.example.quyet.podomoro.networks.services;

import com.example.quyet.podomoro.networks.jsonmodel.LoginResponseJson;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by quyet on 1/18/2017.
 */

public interface LoginService {
    @POST("login")
    Call<LoginResponseJson> login(@Body RequestBody body);
}
