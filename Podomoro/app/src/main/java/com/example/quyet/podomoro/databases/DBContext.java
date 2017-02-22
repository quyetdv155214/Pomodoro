package com.example.quyet.podomoro.databases;

import android.util.Log;

import com.example.quyet.podomoro.databases.models.Color;
import com.example.quyet.podomoro.databases.models.Task;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by quyet on 2/8/2017.
 */

public class DBContext {

    public static final DBContext instance = new DBContext();
    private List<Task> tasks;

    private DBContext() {
        tasks = new ArrayList<>();
    }

    public List<Task> allTask() {

        return tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Color> allColor() {
        List<Color> colors = new ArrayList<>();
        colors.add(new Color("#4A148C"));
        colors.add(new Color("#E040FB"));
        colors.add(new Color("#D500F9"));
        colors.add(new Color("#00897B"));
        colors.add(new Color("#1DE9B6"));
        colors.add(new Color("#D4E157"));
        colors.add(new Color("#76FF03"));
        colors.add(new Color("#69F0AE"));
        colors.add(new Color("#F9A825"));
        colors.add(new Color("#616161"));
        return colors;
    }

    public void editTask(Task newTask){
        String id = newTask.getId();
        for (Task t: tasks
             ) {
            if (t.getId() == id)
            {
                t.setName(newTask.getName());
                t.setColor(newTask.getColor());
                t.setDone(newTask.isDone());
                t.setPayment_per_hour(newTask.getPayment_per_hour());
                Log.d(TAG, String.format("onOptionsItemSelected: %s ", t.toString()));
                break;
            }

        }
    }


    public void addTask(Task newTask) {
        tasks.add(newTask);
        TaskContext.instance.addNewTask(newTask);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
