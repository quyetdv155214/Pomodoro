package com.example.quyet.podomoro.networks.jsonmodel;

import com.example.quyet.podomoro.ultil.Utils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Until;

/**
 * Created by quyetdv on 2/21/2017.
 */

public class TaskResponseJson {

    @SerializedName("name")
    private String name;
    @SerializedName("color")
    private String color;
    @SerializedName("payment_per_hour")
    private double payment_per_hour;
    @SerializedName("done")
    private boolean isDone;
    @SerializedName("id")
    private String id;
    @SerializedName("local_id")
    private String local_id;
    @SerializedName("due_date")
    private String due_date;
    //

    public TaskResponseJson(String name, String color, double payment_per_hour, boolean isDone, String id, String local_id, String due_date) {
        this.name = name;
        this.color = color;
        this.payment_per_hour = payment_per_hour;
        this.isDone = isDone;
        this.id = id;
        this.local_id = local_id;
        this.due_date = due_date;
    }

    public TaskResponseJson(String name, String color, double payment_per_hour, boolean isDone, String id, String due_date) {
        this.name = name;
        this.color = color;
        this.payment_per_hour = payment_per_hour;
        this.isDone = isDone;
        this.id = id;
        this.due_date = due_date;
        local_id = Utils.instance.getUUID();
    }

    @Override
    public String toString() {
        return "TaskResponseJson{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", payment_per_hour=" + payment_per_hour +
                ", isDone=" + isDone +
                ", id='" + id + '\'' +
                ", local_id='" + local_id + '\'' +
                ", due_date='" + due_date + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPayment_per_hour() {
        return payment_per_hour;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
