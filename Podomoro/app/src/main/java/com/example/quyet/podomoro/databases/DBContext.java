package com.example.quyet.podomoro.databases;

import com.example.quyet.podomoro.databases.models.Color;
import com.example.quyet.podomoro.databases.models.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quyet on 2/8/2017.
 */

public class DBContext {

    public static final DBContext instance = new DBContext();

    private DBContext() {
    }

    public List<Task> allTask(){
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1","#4A148C"));
        tasks.add(new Task("Task 2","#E040FB"));
        tasks.add(new Task("Task 3","#D500F9"));
        tasks.add(new Task("Task 4","#2196F3"));
        tasks.add(new Task("Task 5","#3F51B5"));
        tasks.add(new Task("Task 6","#009688"));
        tasks.add(new Task("Task 7","#0277BD"));
        tasks.add(new Task("Task 8","#00897B"));
        tasks.add(new Task("Task 9","#1DE9B6"));
        tasks.add(new Task("Task 10","#D4E157"));
        tasks.add(new Task("Task 11","#76FF03"));
        tasks.add(new Task("Task 12","#69F0AE"));
        tasks.add(new Task("Task 13","#F9A825"));
        tasks.add(new Task("Task 14","#616161"));
        return tasks;
    }  public List<Color> allColor(){
        List<Color> colors = new ArrayList<>();
        colors.add(new Color("#4A148C"));
        colors.add(new Color("#E040FB"));
        colors.add(new Color("#D500F9"));
        colors.add(new Color("#2196F3"));
        colors.add(new Color("#3F51B5"));
        colors.add(new Color("#009688"));
        colors.add(new Color("#0277BD"));
        colors.add(new Color("#00897B"));
        colors.add(new Color("#1DE9B6"));
        colors.add(new Color("#D4E157"));
        colors.add(new Color("#76FF03"));
        colors.add(new Color("#69F0AE"));
        colors.add(new Color("#F9A825"));
        colors.add(new Color("#616161"));
        return colors;
    }
}
