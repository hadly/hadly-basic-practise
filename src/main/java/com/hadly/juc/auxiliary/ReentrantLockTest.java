package com.hadly.juc.auxiliary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hadly on 2017/8/8.
 */
public class ReentrantLockTest extends Thread {
    LockHolder lockHolder;
    private int id;

    public ReentrantLockTest(int i, LockHolder lockHolder) {
        this.id = i;
        this.lockHolder = lockHolder;
    }

    public static void main(String args[]) {
        ExecutorService service = Executors.newCachedThreadPool();
        //NOTE:给几个线程传进去的是同一个lock
        LockHolder lockHolder = new LockHolder();
        for (int i = 0; i < 10; i++) {
            service.submit(new ReentrantLockTest(i, lockHolder));
        }
        service.shutdown();
    }

    @Override
    public void run() {
        lockHolder.print(id);
    }

    private static class LockHolder {
        private ReentrantLock lock = new ReentrantLock();

        public void print(int str) {
            try {
                lock.lock();
                System.out.println(str + "获得" + lock);
                Thread.sleep((int) (Math.random() * 1000));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(str + "释放");
                lock.unlock();
            }
        }
    }//LockHolder
}