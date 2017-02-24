package com.example.quyet.podomoro.databases;

import android.util.Log;

import com.example.quyet.podomoro.databases.models.Task;
import com.example.quyet.podomoro.networks.NetContext;
import com.example.quyet.podomoro.networks.jsonmodel.DeleteResponseJSon;
import com.example.quyet.podomoro.networks.jsonmodel.TaskResponseJson;
import com.example.quyet.podomoro.networks.services.TaskService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * Created by quyetdv on 2/21/2017.
 */

public class TaskContext {
    public static  final String TAG = "TaskContext";
    private TaskService taskService = NetContext.instance.create(TaskService.class);

    public  static  final TaskContext instance = new TaskContext();

    private TaskContext() {
    }
    public  boolean getTaskFromServer() {
        // // TODO: 2/23/2017 new Solution (use static header )
//        TaskService taskService = NetContext.instance.create(TaskService.class);
        taskService.getTasks().enqueue(new Callback<List<TaskResponseJson>>() {
            @Override
            public void onResponse(Call<List<TaskResponseJson>> call, Response<List<TaskResponseJson>> response) {
                List<Task> tasks = new ArrayList<>();
                Log.d(TAG, "onResponse: get All Task"+ response.code());
                if (response.body() != null){
                    for (TaskResponseJson t :
                            response.body()) {
//                        if (t.getName()!= null)
                        tasks.add(new Task(
                                t.getName(),
                                t.getColor(),
                                t.getPayment_per_hour(),
                                t.isDone(),
                                t.getId(),
                                t.getLocal_id(),
                                t.getDue_date()
                        ));
                    }

                    DBContext.instance.setTasks(tasks);
                }
            }

            @Override
            public void onFailure(Call<List<TaskResponseJson>> call, Throwable t) {
                Log.d(TAG, String.format("onFailure: get all task %s", t.getCause().toString()) );
            }
        });
        return true;
    }

    public void addNewTask(Task newTask){
        TaskResponseJson newTaskResponseJson = new TaskResponseJson(
                newTask.getName(),
                newTask.getColor(),
                newTask.getPayment_per_hour(),
                newTask.isDone(),
                newTask.getId(),
                newTask.getLocal_id(),
                newTask.getDue_date()
        );
        Log.d(TAG, "addNewTask: " + newTask.isDone());
        // // TODO: 2/23/2017 new Solution (use static header )
        taskService.addNewTask(newTaskResponseJson).enqueue(new Callback<TaskResponseJson>() {
            @Override
            public void onResponse(Call<TaskResponseJson> call, Response<TaskResponseJson> response) {
                Log.d(TAG, String.format("addNewTask: %s %s",response.code(), response.body().toString()));
            }
            @Override
            public void onFailure(Call<TaskResponseJson> call, Throwable t) {
                Log.d(TAG, String.format("onFailure: add new task%s", t.getCause().toString()));

            }
        });

    }
    public void editTask(Task editedTask){
        String localId = editedTask.getLocal_id();
//        TaskService taskService = NetContext.instance.create(TaskService.class);
        TaskResponseJson editedTaskResponse = new TaskResponseJson(
                editedTask.getName(),
                editedTask.getColor(),
                editedTask.getPayment_per_hour(),
                editedTask.isDone(),
                editedTask.getId(),
                editedTask.getDue_date()
        );
//        Log.d(TAG, String.format("editTask: edit task localid %s ", localId));
        taskService.editTask(localId,editedTaskResponse).enqueue(new Callback<TaskResponseJson>() {
            @Override
            public void onResponse(Call<TaskResponseJson> call, Response<TaskResponseJson> response) {
                if (response.body() != null)
                Log.d(TAG, "onResponse : Edited task code" + response.code());
                else{
                    Log.d(TAG, "onResponse: edit fail ; code " + response.code());
                }
            }
            @Override
            public void onFailure(Call<TaskResponseJson> call, Throwable t) {
                Log.d(TAG, "edit fail ; " + t.getCause());
            }
        });

    }
    public void deleteTask(Task taskDelete){
        String localID = taskDelete.getLocal_id();
        Log.d(TAG, "deleteTask: in function");
        taskService.deleteTask(localID).enqueue(new Callback<DeleteResponseJSon>() {
            @Override
            public void onResponse(Call<DeleteResponseJSon> call, Response<DeleteResponseJSon> response) {

                Log.d(TAG, "onResponse: DeleteTask code "+ response.code()  );
            }
            @Override
            public void onFailure(Call<DeleteResponseJSon> call, Throwable t) {
                Log.d(TAG, String.format("onFailure: delete task%s", t.getCause()));
            }
        });
    }
}
