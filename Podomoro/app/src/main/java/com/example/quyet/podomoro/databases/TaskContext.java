package com.example.quyet.podomoro.databases;

import android.util.Log;

import com.example.quyet.podomoro.databases.models.Task;
import com.example.quyet.podomoro.networks.NetContext;
import com.example.quyet.podomoro.networks.jsonmodel.TaskResponeJson;
import com.example.quyet.podomoro.networks.services.AddNewTaskService;
import com.example.quyet.podomoro.networks.services.GetTasksService;
import com.example.quyet.podomoro.settings.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by quyetdv on 2/21/2017.
 */

public class TaskContext {

    public  static  final TaskContext instance = new TaskContext();

    private TaskContext() {
    }
    public  void getTaskFromServer() {
        GetTasksService getTasksService = NetContext.instance.retrofit.create(GetTasksService.class);
        final String token = "JWT " + SharedPrefs.instance.getLoginCredentials().getAccessToken();

        getTasksService.getTasks(token).enqueue(new Callback<List<TaskResponeJson>>() {
            @Override
            public void onResponse(Call<List<TaskResponeJson>> call, Response<List<TaskResponeJson>> response) {
                List<Task> tasks = new ArrayList<>();
                Log.d(TAG, "onResponse: "+ response.code());
                if (response.body() != null){
                    for (TaskResponeJson t :
                            response.body()) {
//                        if (t.getName()!= null)
                     tasks.add(new Task(
                             t.getName(),
                             t.getColor(),
                             t.getPayment_per_hour(),
                             t.isDone(),
                             t.getId()
                     ));

                    }
                    DBContext.instance.setTasks(tasks);
                }
            }
            @Override
            public void onFailure(Call<List<TaskResponeJson>> call, Throwable t) {
                Log.d(TAG, String.format("onFailure: f %s", t.getCause().toString()) );
            }
        });
    }
    public void addNewTask(Task newTask){
        AddNewTaskService addNewTaskService = NetContext.instance.retrofit.create(AddNewTaskService.class);

        String token= "JWT "+ SharedPrefs.instance.getLoginCredentials().getAccessToken();
        // public TaskResponeJson(String name, String color, double payment_per_hour, boolean isDone, String id, String local_id, String due_date)
        TaskResponeJson newTaskResponeJson = new TaskResponeJson(
                newTask.getName(),
                newTask.getColor(),
                newTask.getPayment_per_hour(),
                newTask.isDone(),
                newTask.getId(),
                newTask.getLocal_id(),
                newTask.getDue_date()
        );
        addNewTaskService.addNewTask("application/json",token,newTaskResponeJson).enqueue(new Callback<TaskResponeJson>() {
            @Override
            public void onResponse(Call<TaskResponeJson> call, Response<TaskResponeJson> response) {
                Log.d(TAG, String.format("addNewTask: %s %s",response.code(), response.body().toString()));
            }

            @Override
            public void onFailure(Call<TaskResponeJson> call, Throwable t) {
                Log.d(TAG, String.format("onFailure: %s", t.getCause().toString()));
            }
        });
    }
}
