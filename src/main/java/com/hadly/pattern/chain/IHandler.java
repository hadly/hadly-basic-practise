package com.hadly.pattern.chain;

/**
 * Created by lizhinian on 2017/11/2.
 * 《责任链模式》
 * 接口里面只是约定，这个接口提供了什么功能
 */
public interface IHandler {
    void setSuccessor(IHandler successor);

    /**
     * @param request 是具体要处理的内容
     */
    void handleRequest(int request);
}
