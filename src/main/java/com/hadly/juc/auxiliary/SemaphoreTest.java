package com.hadly.juc.auxiliary;

import java.util.concurrent.Semaphore;

/**
 * Created by hadly on 2017/8/4.
 * 1.Semaphore可以控制同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可
 */
public class SemaphoreTest {
    /**
     * 1.假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，只有使用完了，
     * 其他工人才能继续使用。那么我们就可以通过Semaphore来实现
     * 2.构造函数
     * -参数permits表示许可数目，即同时可以允许多少线程进行访问
     * -这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = 8;//工人数
        Semaphore semaphore = new Semaphore(5); //机器数目
        for (int i = 0; i < N; i++)
            new Worker(i, semaphore).start();
    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        /**
         * 1.如果想立即得到执行结果，可以使用下面几个方法
         * public boolean tryAcquire() { };    //尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
         * public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };  //尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
         * public boolean tryAcquire(int permits) { }; //尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
         * public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { }; //尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
         */

        @Override
        public void run() {
            try {
                //acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可
                semaphore.acquire();
                System.out.println("工人" + this.num + "占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人" + this.num + "释放出机器");
                //release()用来释放许可。注意，在释放许可之前，必须先获获得许可
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
