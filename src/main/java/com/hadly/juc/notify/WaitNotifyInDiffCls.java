package com.hadly.juc.notify;

/**
 * Created by hadly on 2017/8/8.
 * 1.Wait和notify用在不同的类里面实现交互
 * 2.b.wait()的解释：
 * - http://blog.sina.com.cn/s/blog_4eaff65601000bvf.html
 * synchronized(b){...}；的意思是定义一个同步块,使用b作为资源锁。b.wait();
 * 的意思是临时释放锁，并阻塞当前线程,好让其他使用同一把锁的线程有机会执行,
 * 在这里要用同一把锁的就是b线程本身.这个线程在执行到一定地方后用notify()通知wait的线程,
 * 锁已经用完,待notify()所在的同步块运行完之后,wait所在的线程就可以继续执行.
 */
public class WaitNotifyInDiffCls {
    public static void main(String[] args) {
        ThreadB b = new ThreadB();
        b.start(); //b线程运行
        System.out.println("b is start....");
        synchronized (b)//获取对象b的锁
        {
            try {
                System.out.println("Waitingfor b to complete...");
                b.wait();//释放b上的锁，等待其完成
                System.out.println("Completed.Now back to main thread");
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Total is :" + b.total);
    }

    private static class ThreadB extends Thread {
        int total;

        public void run() {
            synchronized (this) {
                System.out.println("ThreadB is running..");
                for (int i = 0; i < 10; i++) {
                    total += i;
                    System.out.println("total is " + total);
                }
                notify(); //通知等待对象b上的锁的线程继续运行
            }
        }
    }//ThreadB
}
