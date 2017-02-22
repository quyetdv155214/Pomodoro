package com.example.quyet.podomoro.networks.services;

import com.example.quyet.podomoro.databases.models.Task;
import com.example.quyet.podomoro.networks.jsonmodel.TaskResponeJson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by quyetdv on 2/21/2017.
 */

public interface AddNewTaskService {
    @POST("task")
    Call<TaskResponeJson> addNewTask(@Header("Content-Type") String type, @Header("Authorization") String token, @Body TaskResponeJson task);
}
