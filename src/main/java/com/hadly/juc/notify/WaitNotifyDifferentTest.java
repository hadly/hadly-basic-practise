package com.hadly.juc.notify;

import java.util.Date;

/**
 * Created by hadly on 2017/8/5.
 * 验证notify和notifyAll的区别：
 * 1.notify只会通知wait的其中一个线程
 * 2.notifyAll会通知wait的所有线程，这些线程会争用CPU，但是最终会得到执行
 */
public class WaitNotifyDifferentTest {
    private static final Object obj = new Object();

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new MyTask(i));
        }
        for (Thread thread : threads) {
            thread.start();
        }

        Thread.sleep(2000);
//        notifyTest();
        notifyAllTest();
    }

    /**
     * 阻塞的线程中只有一个会运行;其他线程都会被饿死
     */
    private static void notifyTest() {
        synchronized (obj) {
            obj.notify();
        }
    }

    /**
     * 阻塞的线程最终都会被执行
     */
    private static void notifyAllTest() {
        synchronized (obj) {
            obj.notifyAll();
        }
    }

    static class MyTask implements Runnable {
        int i;

        MyTask(int i) {
            this.i = i;
        }

        public void run() {
            try {
                synchronized (obj) {
                    System.out.println("线程->  " + i + " 等待中");
                    obj.wait();
                    System.out.println("线程->  " + i + " 在运行了 " + new Date());
                    Thread.sleep(5000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
