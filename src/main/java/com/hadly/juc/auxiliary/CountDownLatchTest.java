package com.hadly.juc.auxiliary;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hadly on 2017/7/24.
 * 1.Java并发编程：CountDownLatch、CyclicBarrier和 Semaphore
 * 见http://www.importnew.com/21889.html
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        new Worker(latch).start();
        new Worker(latch).start();

        try {
            System.out.println("等待2个子线程执行完毕... \n");
            //Causes the current thread to wait until the latch has counted down to
            //zero, unless the thread is interrupted.
            latch.await();
            System.out.println("\n 2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Worker extends Thread {
        CountDownLatch latch;

        public Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run() {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(1000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }//end of Worker
}
