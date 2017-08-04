package com.hadly.juc.auxiliary;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by hadly on 2017/7/24.
 * 1.让一组线程等待至某个状态之后再全部同时执行。
 * -叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用
 * -暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 */
public class CyclicBarrierTest {
    final static long DEFAULT_AWAIT_TIMEOUT = 0;

    public static void main(String[] args) {
//        awaitToExecAll();
//        awaitToExecSth();
//        awaitWithTimeout();
        reuseableBarrier();
    }

    /**
     * 可重用的barrier：
     * 在初次的4个线程越过barrier状态后，又可以用来进行新一轮的使用。而CountDownLatch无法进行重复使用
     */
    private static void reuseableBarrier() {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);

        for (int i = 0; i < N; i++) {
            new Writer(barrier, DEFAULT_AWAIT_TIMEOUT).start();
        }

        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");

        for (int i = 0; i < N; i++) {
            new Writer(barrier, DEFAULT_AWAIT_TIMEOUT).start();
        }
    }

    /**
     * 为await指定等待时间
     */
    private static void awaitWithTimeout() {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);

        for (int i = 0; i < N; i++) {
            if (i < N - 1)
                new Writer(barrier, 2000).start();
            else {
                //NOTE：故意让最后一个线程启动延迟，因为在前面三个线程都达到barrier之后，等待了指定的时间发现
                //      第四个线程还没有达到barrier，就抛出异常并继续执行后面的任务
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Writer(barrier, 2000).start();
            }
        }
    }

    /**
     * 等待所有线程集齐 后 执行指定的操作
     */
    private static void awaitToExecSth() {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
            public void run() {
                System.out.println("==所有线程写入操作完之后，进行的额外操作。当前线程" + Thread.currentThread().getName());
            }
        });

        for (int i = 0; i < N; i++) {
            new Writer(barrier, DEFAULT_AWAIT_TIMEOUT).start();
        }
    }

    /**
     * 等待所有线程都执行完成操作后继续
     */
    private static void awaitToExecAll() {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++)
            new Writer(barrier, DEFAULT_AWAIT_TIMEOUT).start();
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;
        private long awaitTimeout;

        public Writer(CyclicBarrier cyclicBarrier, long awaitTimeout) {
            this.cyclicBarrier = cyclicBarrier;
            this.awaitTimeout = awaitTimeout;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await(awaitTimeout, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("所有其他线程写入完毕，继续处理其他任务...");
        }
    }
}
