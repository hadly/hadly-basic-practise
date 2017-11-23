package com.hadly.pattern.mediator;

/**
 * --每个具体同事，只知道自己的行为，不了解其他同事的情况，但都认识中介者
 * Created by lizhinian on 2017/11/23.
 */
public class Colleague1 extends AbstractColleague{
    public Colleague1(IMediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String message){
        // 发送消息的对象是自己
        mediator.send(message, this);
    }

    @Override
    public void notifyTo(String message){
        System.out.println("同事2发过来的消息:" + message);
    }
}
