package com.example.quyet.podomoro.networks.services;

import com.example.quyet.podomoro.networks.jsonmodel.DeleteResponseJSon;
import com.example.quyet.podomoro.networks.jsonmodel.TaskResponseJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by quyet on 2/23/2017.
 */

public interface TaskService {
    @POST("task")
    Call<TaskResponseJson> addNewTask(@Body TaskResponseJson task);
    @GET("task")
    Call<List<TaskResponseJson>> getTasks() ;
    @PUT("task/{local_id}")
    Call<TaskResponseJson> editTask(@Path("local_id") String localID, @Body TaskResponseJson taskEdited);
    @DELETE("task/{local_id}")
    Call<DeleteResponseJSon> deleteTask(@Path("local_id") String localID);
}
