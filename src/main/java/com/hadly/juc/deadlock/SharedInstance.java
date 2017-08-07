package com.hadly.juc.deadlock;

/**
 * Created by hadly on 2017/8/5.
 * 这里面有两个锁，Consumer会获取里面的锁以制造死锁
 */
public class SharedInstance {
    static Object lock1 = new Object();
    static Object lock2 = new Object();
}
