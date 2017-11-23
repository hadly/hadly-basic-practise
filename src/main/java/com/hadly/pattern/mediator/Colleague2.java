package com.hadly.pattern.mediator;

/**
 * Created by lizhinian on 2017/11/23.
 */
public class Colleague2 extends AbstractColleague{
    public Colleague2(IMediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String message) {
        mediator.send(message, this);
    }

    @Override
    public void notifyTo(String message) {
        System.out.println("同事1发过来的消息:" + message);
    }


}
