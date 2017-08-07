package com.hadly.juc.threadlocal;

/**
 * Created by hadly on 2017/7/24.
 */
public class ThreadLocalTest {
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<Object> sharedLocal = new ThreadLocal<Object>();
    //将这个对象设置到每个线程中，就相当于多线程之间共享，依旧会有线程安全问题
    static Object sharedObject = new Object();

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();

        test.set();
        test.sharedLocal.set(sharedObject);
        printLAndS(test.getLong(), test.getString());
        System.out.println("main shared local=" + test.sharedLocal.get());

        Thread thread1 = new Thread() {
            public void run() {
                test.set();
                test.sharedLocal.set(sharedObject);
                printLAndS(test.getLong(), test.getString());
                System.out.println("thread1 shared local=" + test.sharedLocal.get());
            }
        };
        thread1.start();
        thread1.join();

        printLAndS(test.getLong(), test.getString());
    }

    private static void printLAndS(Long l, String s) {
        System.out.println("longLocal=" + l + ", stringLocal=" + s);
    }

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public Long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }
}
