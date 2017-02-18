package com.example.quyet.podomoro.databases.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by quyet on 2/8/2017.
 */

public class Task {
    private String name;
    private String color;
    private double payment_per_hour;
    private boolean isDone;
    private long id;

    public void setPayment_per_hour(double payment_per_hour) {
        this.payment_per_hour = payment_per_hour;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

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

    public Task(String name, String color, double payment_per_hour, boolean isDone) {
        this.name = name;
        this.color = color;
        this.payment_per_hour = payment_per_hour;
        this.isDone = isDone;
       this.id = (new Date().getTime()/1000);
    }




    public double getPayment_per_hour() {
        return payment_per_hour;
    }

    public void setPayment_per_hour(float payment_per_hour) {
        this.payment_per_hour = payment_per_hour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", payment_per_hour=" + payment_per_hour +
                ", isDone=" + isDone +
                ", id=" + id +
                '}';
    }
}
