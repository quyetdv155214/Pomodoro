package com.example.quyet.podomoro.networks.services;

import com.example.quyet.podomoro.databases.models.Task;
import com.example.quyet.podomoro.networks.jsonmodel.TaskResponeJson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by quyetdv on 2/21/2017.
 */

public interface GetTasksService {
    @GET("task")
    Call<List<TaskResponeJson>> getTasks(@Header("Authorization") String contentRange) ;
}
