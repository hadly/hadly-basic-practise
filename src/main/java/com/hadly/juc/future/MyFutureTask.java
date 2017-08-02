package com.hadly.juc.future;

/**
 * Created by hadly on 2017/7/31.
 */
public class MyFutureTask implements Runnable {
    private MyCallable callable;
    private boolean isDone;
    private String callableResult;

    public MyFutureTask(MyCallable callable) {
        this.callable = callable;
    }

    /**
     * NOTE:存在的问题，run在运行的时候，get就会阻塞，获取不到锁
     */
    public void run() {
        System.out.println("call begin.");
        callableResult = callable.call();
        System.out.println("call finished.");
        synchronized (this) {
            isDone = true;
            notifyAll();
        }
//        new Thread(new Runnable() {
//            public void run() {
//                System.out.println("call begin.");
//                callableResult = callable.call();
//                System.out.println("call finished.");
//                isDone = true;
//                notifyAll();
//            }
//        }).start();
    }

    public synchronized String get() {
        while (!isDone) {
            try {
                System.out.println("get is stucked because call() not finished.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return callableResult;
    }
}
