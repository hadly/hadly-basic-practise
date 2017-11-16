package com.hadly.pattern.state;

/**
 * 工作 - 定义当前状态，可以设置这个状态的下一个状态
 * Created by lizhinian on 2017/11/13.
 */
public class Work {
    // 定义当前状态
    private State current;
    // 定义另外一些上下文，辅助判断状态是否变迁
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

    /**
     * 设置下一个状态
     * @param state
     */
    public void setState(State state) {
        current = state;
    }
}
