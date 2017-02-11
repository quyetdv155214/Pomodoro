package com.example.quyet.podomoro.databases.models;

/**
 * Created by quyet on 2/8/2017.
 */

public class Task {
    private String name;
    private String color;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public Task(String name, String color) {
        this.name = name;
        this.color = color;

    }

    @Override
    public String toString() {
        return "Task{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
