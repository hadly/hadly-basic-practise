package com.hadly.juc.consumerproducer;

/**
 * Created by hadly on 2017/7/24.
 * 店员
 * 1.生产者消费者的几个示例
 * https://zhuanlan.zhihu.com/p/20300609 3种实现方法
 * http://www.infoq.com/cn/articles/producers-and-consumers-mode/  方鹏飞
 * http://www.cnblogs.com/myhomepages/archive/2016/11/03/6028663.html 不断改进
 * http://blog.csdn.net/monkey_d_meng/article/details/6251879/ 3种实现方法实践
 */
public class ClerkComplicated implements IClerk {
    //最大产品数
    private static final int MAX_PRODUCT = 20;
    //最小产品数
    private static final int MIN_PRODUCT = 0;
    //默认为0个产品
    private int product = 0;

    public static void main(String[] args){
        IClerk clerk = new ClerkComplicated();
        new Thread(new Producer(clerk)).start();
        new Thread(new Consumer(clerk)).start();
    }

    /**
     * 生产者生产出来的产品交给店员
     */
    public synchronized void addProduct() {
        System.out.println("begin to addProduct.");
        if (this.product >= MAX_PRODUCT) {
            try {
                System.out.println("产品已满,请稍候再生产");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        this.product++;
        System.out.println("生产者生产第" + this.product + "个产品.");
        //通知等待区的消费者可以取出产品了
        notifyAll();
    }

    /**
     * 消费者从店员取产品
     */
    public synchronized void getProduct() {
        System.out.println("begin to getProduct.");
        if (this.product <= MIN_PRODUCT) {
            try {
                System.out.println("缺货,稍候再取");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("消费者取走了第" + this.product + "个产品.");
        this.product--;
        notifyAll();   //通知等待区的生产者可以生产产品了
    }
}
