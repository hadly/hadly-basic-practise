package com.hadly.pattern.chain;

/**
 * Created by lizhinian on 2017/11/2.
 * 《责任链模式》
 * --使得多个对象都有机会处理请求，避免请求的发送者和接收者耦合
 * --接口里面只是约定，这个接口提供了什么功能
 *
 * --IHandler表示处理某个请求的接口对象；该接口对象可以设置其后继，可以处理对象
 */
public interface IHandler {
    /**
     * 设置下一个要处理请求的对象
     * @param successor
     */
    void setSuccessor(IHandler successor);

    /**
     * @param request 是具体要处理的内容
     */
    void handleRequest(int request);
}
