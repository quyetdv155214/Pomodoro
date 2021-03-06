package com.example.quyet.podomoro.databases.models;

import com.example.quyet.podomoro.ultil.Utils;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by quyet on 2/8/2017.
 */

public class Task  extends RealmObject{
    @PrimaryKey
    private String local_id;
    private String name;
    private String color;
    private double payment_per_hour;
    private boolean isDone;
    private String id;

    private String due_date;

    public Task() {
    }

    public Task(String name, String color, double payment_per_hour, boolean isDone, String id, String local_id, String due_date) {
        this.name = name;
        this.color = color;
        this.payment_per_hour = payment_per_hour;
        this.isDone = isDone;
        this.id = id;
        this.local_id = local_id;
        this.due_date = due_date;
    }

    public Task(String name, String color, double payment_per_hour, boolean isDone, String id, String due_date) {
        this.name = name;
        this.color = color;
        this.payment_per_hour = payment_per_hour;
        this.isDone = isDone;
        this.id = id;
        this.due_date = due_date;
        this.local_id = Utils.instance.getUUID();
    }

    public String getLocal_id() {
        return local_id;
    }

    public void setLocal_id(String local_id) {
        this.local_id = local_id;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

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




    public double getPayment_per_hour() {
        return payment_per_hour;
    }

    public void setPayment_per_hour(float payment_per_hour) {
        this.payment_per_hour = payment_per_hour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", payment_per_hour=" + payment_per_hour +
                ", isDone=" + isDone +
                ", id='" + id + '\'' +
                ", local_id='" + local_id + '\'' +
                ", due_date='" + due_date + '\'' +
                '}';
    }
}
