package com.example.quyet.podomoro.settings;

/**
 * Created by quyet on 1/16/2017.
 */

public class Setting {
    public static final int DEFAULT_BREAKTIME = 5;
    public static final int DEFAUL_WORKTIME = 30;
    public static final int DEFAULT_LONG_BREAKTIME = 10;
    public static final int DEFAULT_COUT_TO_LONG_BREAK = 3;
    private int workTime;
    private int breakTime;
    private int longBreakTime;
    private int longBreakAffter;


    public Setting(int workTime, int breakTime, int longBreakTime, int longBreakAffter) {
        this.workTime = workTime;
        this.breakTime = breakTime;
        this.longBreakTime = longBreakTime;
        this.longBreakAffter = longBreakAffter;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public int getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(int breakTime) {
        this.breakTime = breakTime;
    }

    public int getLongBreakTime() {
        return longBreakTime;
    }

    public void setLongBreakTime(int longBreakTime) {
        this.longBreakTime = longBreakTime;
    }

    public int getLongBreakAffter() {
        return longBreakAffter;
    }

    public void setLongBreakAffter(int longBreakAffter) {
        this.longBreakAffter = longBreakAffter;
    }
}
