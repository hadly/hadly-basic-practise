package com.hadly.pattern.mediator;

/**
 * --定义了同事对象到中介者对象的接口
 * Created by lizhinian on 2017/11/23.
 */
public interface IMediator {

    /**
     * 同事之间互相交流的接口
     * @param message 要发送的消息
     * @param colleague 要发送消息的同事对象
     */
    void send(String message, AbstractColleague colleague);
}
