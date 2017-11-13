package com.hadly.pattern.state;

/**
 * 工作
 * Created by lizhinian on 2017/11/13.
 */
public class Work {
    private State current;
    private int hour;
    private boolean taskFinished;

    {
        current = new ForenoonState();
    }

    public boolean isTaskFinished() {
        return taskFinished;
    }

    public void setTaskFinished(boolean taskFinished) {
        this.taskFinished = taskFinished;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void coding() {
        current.coding(this);
    }

    public void setState(State state) {
        current = state;
    }
}
