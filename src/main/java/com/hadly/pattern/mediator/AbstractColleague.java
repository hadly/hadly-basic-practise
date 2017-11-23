package com.hadly.pattern.mediator;

/**
 * 抽象同事类
 * Created by lizhinian on 2017/11/23.
 */
public abstract class AbstractColleague {
    protected IMediator mediator;

    public AbstractColleague(IMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void send(String message);

    public abstract void notifyTo(String message);
}
