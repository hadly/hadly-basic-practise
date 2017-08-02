package com.hadly.juc.future;

import java.util.concurrent.*;

/**
 * Created by hadly on 2017/7/31.
 * 参考地址：
 * http://www.2cto.com/kf/201411/351903.html
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testJDKFuture();
        testMyFuture();
    }

    /**
     * 自己实现的future测试
     */
    private static void testMyFuture() {
        MyFutureTask futureTask = new MyFutureTask(new MyCallable() {
            public String call() {
                System.out.println("sleep 3 seconds before call finished.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "FUTURE_RESULT";
            }
        });

        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(futureTask);

        System.out.println("do something before call future.get()");
        String futureResult = futureTask.get();
        System.out.println("future result=" + futureResult);
    }

    /**
     * 使用JDK提供的接口实现future
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testJDKFuture() throws ExecutionException, InterruptedException {
        final FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                System.out.println("sleep 3 seconds before call finished.");
                Thread.sleep(3000);
                return "FUTURE_RESULT";
            }
        });

        Thread thread = new Thread(new Runnable() {
            public void run() {
                futureTask.run();
            }
        });
        thread.start();

        System.out.println("do something before call future.get()");
        String futureResult = futureTask.get();
        System.out.println("future result=" + futureResult);
    }
}
