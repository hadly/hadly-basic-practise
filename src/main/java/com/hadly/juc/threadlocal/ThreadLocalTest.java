package com.hadly.juc.threadlocal;

/**
 * Created by hadly on 2017/7/24.
 */
public class ThreadLocalTest {
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();

        test.set();
        printLAndS(test.getLong(), test.getString());

        Thread thread1 = new Thread() {
            public void run() {
                test.set();
                printLAndS(test.getLong(), test.getString());
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
