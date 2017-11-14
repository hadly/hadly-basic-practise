package com.hadly.juc.auxiliary;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hadly on 2017/7/24.
 * 1.Java并发编程：CountDownLatch、CyclicBarrier和 Semaphore
 * 见http://www.importnew.com/21889.html
 * 2.有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了
 * 3.简化了传统的用计数器+wait/notifyAll来实现该功能的方式
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

    /**
     * 1）CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
     * <p>
     * CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
     * <p>
     * 而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
     * <p>
     * 另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
     * <p>
     * 2）Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。
     */
    private static void readme() {

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
