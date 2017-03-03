package com.example.quyet.podomoro.databases.models;

/**
 * Created by quyet on 2/11/2017.
 */

public class Color {
    private String color;
    boolean checked;
    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }


    public Color(String color, boolean checked){
        this.color = color;
        this.checked =checked;
    }
    public Color( String color) {
        this.color = color;
        this.checked = false;

    }

    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                ", checked=" + checked +
                '}';
    }
    public static String[] COLORS = new String[]{
            "#4A148C",
            "#E040FB",
            "#D500F9",
            "#00897B",
            "#1DE9B6",
            "#D4E157",
            "#76FF03",
            "#69F0AE",
            "#F9A825",
            "#616161"

    };
}
