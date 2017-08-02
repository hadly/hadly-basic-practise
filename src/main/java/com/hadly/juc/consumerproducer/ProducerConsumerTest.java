package com.hadly.juc.consumerproducer;

/**
 * Created by hadly on 2017/7/24.
 * 1.wait()、notify()、notifyAll()方法
 * 2.这些方法只有在synchronized或synchronized代码块中才能使用，
 * 否则会报java.lang.IllegalMonitorStateException异常
 * 3.另外线程执行了某对象的notify方法之后，会唤醒在此对象等待池中的某个线程，使之成为可运行线程。
 * notifyAll是唤醒在此对象等待池中所有的等待线程
 */
public class ProducerConsumerTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        IClerk clerk = getClerk();

        beginTest(clerk);
    }

    /**
     * 获取不同的clerk进行测试
     * @return
     */
    private static IClerk getClerk(){
        return new Clerk1();
        //TODO：添加新的Clerk实现
    }

    private static void beginTest(IClerk clerk){
        Thread producerThread = new Thread(new Producer(clerk));
        Thread consumerThread = new Thread(new Consumer(clerk));

        producerThread.start();
        consumerThread.start();
    }
}
