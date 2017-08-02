package com.hadly.juc.threadlocal;

/**
 * Created by hadly on 2017/7/24.
 * 在初始化ThreadLocal变量的时候，就将值用initialValue()方法返回了，不用先set，后get了
 * 见http://www.importnew.com/17849.html
 */
public class ThreadLocalInitValueTest {
    ThreadLocal<String> stringLocal = new ThreadLocal<String>() {
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalInitValueTest test = new ThreadLocalInitValueTest();

        printLAndS(test.getLong(), test.getString());

        Thread thread1 = new Thread() {
            public void run() {
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

    public Long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }
}
