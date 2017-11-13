package com.hadly.pattern.chain;

/**
 * Created by lizhinian on 2017/11/2.
 */
public class ChainTest {
    public static void main(String[] args) {
        IHandler handler1 = new ConcreteHandler1();
        IHandler handler2 = new ConcreteHandler2();
        handler1.setSuccessor(handler2);

        int[] requests = {-1, 5, 12, 30};
        for (int request : requests) {
            handler1.handleRequest(request);
        }
    }
}
