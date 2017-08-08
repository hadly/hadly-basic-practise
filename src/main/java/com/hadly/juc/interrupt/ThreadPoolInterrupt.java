package com.hadly.juc.interrupt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hadly on 2017/8/8.
 * 1.调用pool.shutdownNow()时，线程池会interrupt其池中的所有线程
 */
public class ThreadPoolInterrupt {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("task1"));
        tasks.add(new Task("task2"));
        tasks.add(new Task("task3"));
        tasks.add(new Task("task4"));
        tasks.add(new Task("task5"));

        for (Task task : tasks) {
            pool.submit(task);
        }

        // 遍历线程池中的工作线程并调用线程的interrupt方法来中断线程，
        // 所以如果工作线程中正在执行的任务 没有对中断做出响应 ，任务将一直执行直到正常结束
        pool.shutdownNow();
    }

    private static class Task extends Thread {
        private String name;

        public Task(String name) {
            this.name = name;
        }

        public void run() {
            while (true) {
                System.out.println("task name=" + name + ", time=" + System.currentTimeMillis());
                TimeConsumingSort.sort();

                // 如果工作线程中正在执行的任务 没有对中断做出响应 ，任务将一直执行直到正常结束
                // 如果做了响应，那么下面if之后的操作都不会执行了
                if (Thread.interrupted()) {
                    System.out.println("运行完成 " + name);
                    return;
                }
            }//while
        }
    }//task
}
