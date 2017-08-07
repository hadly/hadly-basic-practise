package com.hadly.juc.deadlock;

/**
 * Created by hadly on 2017/8/5.
 * 死锁的一个例子
 */
public class DeadLockTest {
    /**
     * 死锁测试：
     * Found one Java-level deadlock:
     * =============================
     * "Thread-1":
     * waiting to lock monitor 0x0000000002dbc938 (object 0x00000000d629bf90, a java.lang.Object),
     * which is held by "Thread-0"
     * "Thread-0":
     * waiting to lock monitor 0x0000000002db9f48 (object 0x00000000d629bfa0, a java.lang.Object),
     * which is held by "Thread-1"
     * <p>
     * Java stack information for the threads listed above:
     * ===================================================
     * "Thread-1":
     * at com.hadly.juc.deadlock.Consumer.consumeInReversedOrder(Consumer.java:34)
     * - waiting to lock <0x00000000d629bf90> (a java.lang.Object)
     * - locked <0x00000000d629bfa0> (a java.lang.Object)
     * at com.hadly.juc.deadlock.DeadLockTest$2.run(DeadLockTest.java:30)
     * at java.lang.Thread.run(Thread.java:745)
     * "Thread-0":
     * at com.hadly.juc.deadlock.Consumer.consumeInOrder(Consumer.java:19)
     * - waiting to lock <0x00000000d629bfa0> (a java.lang.Object)
     * - locked <0x00000000d629bf90> (a java.lang.Object)
     * at com.hadly.juc.deadlock.DeadLockTest$1.run(DeadLockTest.java:24)
     * at java.lang.Thread.run(Thread.java:745)
     * <p>
     * Found 1 deadlock.
     *
     * @param argv
     */
    public static void main(String[] argv) {
        final Consumer consumer = new Consumer();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                consumer.consumeInOrder();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                consumer.consumeInReversedOrder();
            }
        });

        thread1.start();
        thread2.start();
    }
}
