package com.example.quyet.podomoro.databases.models;

/**
 * Created by quyet on 2/8/2017.
 */

public class Task {
    private String name;
    private String color;
    private double payment_per_hour;

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

    public Task(String name, String color, double payment_per_hour) {
        this.name = name;
        this.color = color;
        this.payment_per_hour = payment_per_hour;
    }

    @Override
    public String toString() {
        return "Task{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public double getPayment_per_hour() {
        return payment_per_hour;
    }

    public void setPayment_per_hour(float payment_per_hour) {
        this.payment_per_hour = payment_per_hour;
    }
}
