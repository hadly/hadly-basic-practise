package com.hadly.pattern.chain;

/**
 * Created by lizhinian on 2017/11/2.
 */
public class ConcreteHandler1 extends AbstractHandler {

    public void handleRequest(int request) {
        if (request > 0 & request < 10) {
            System.out.println("处理了" + this.getClass().getName() + ", request=" + request);
        } else if (null != successor) {
            //交给下一个进行处理
            successor.handleRequest(request);
        } else {
            //如果successor为空，处理不了，就打出来
            System.out.println("successor为空，处理不了" + this.getClass().getName() + ", request=" + request);
        }
    }
}
