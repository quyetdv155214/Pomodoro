package com.example.quyet.podomoro.databases.models;

/**
 * Created by quyet on 2/11/2017.
 */

public class Color {
    private String color;
    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public Color( String color) {
        this.color = color;

    }
    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                '}';
    }
}