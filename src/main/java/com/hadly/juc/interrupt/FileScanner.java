package com.hadly.juc.interrupt;

import java.io.File;

/**
 * Created by hadly on 2017/8/8.
 * 1.描述如何中断一个文件扫描线程
 */
public class FileScanner {
    private static long startTime = System.currentTimeMillis();

    private static void listFile(File file) throws InterruptedException {
        if (file == null) {
            throw new IllegalArgumentException();
        }
        if (file.isFile()) {
            System.out.println("file=" + file);
            return;
        }

        System.out.println("directory=" + file);
        File[] allFiles = file.listFiles();
        //判断是否被中断
        if (Thread.currentThread().isInterrupted()) {
            //判断是否被中断，并clean中断状态
            System.out.println("----1st, " + Thread.interrupted());
            System.out.println("----2nd, " + Thread.interrupted());
            //恢复中断
            Thread.currentThread().interrupt();
            System.out.println("----3rd, " + Thread.interrupted());
            //如果不打算响应中断，这里可以什么都不做
            throw new InterruptedException("文件扫描任务被中断");
        }

        if (null == allFiles) {
            System.out.println("文件夹为null");
            return;
        }
        for (File file1 : allFiles) {
            //还可以将中断检测放到这里
            listFile(file1);
        }
    }

    /**
     * 是否退出扫描
     *
     * @return
     */
    public static boolean exitScan() {
        long exitTime = startTime + 5;
        if (System.currentTimeMillis() > exitTime) {
            System.out.println("startTime=" + startTime + ", exitTime=" + exitTime);
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(System.currentTimeMillis());
        final Thread fileIteratorThread = new Thread() {
            public void run() {
                try {
                    listFile(new File("d:\\"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //扫描线程执行完
            }
        };
        fileIteratorThread.start();

        readCommand(fileIteratorThread);
    }

    private static void readCommand(final Thread fileIteratorThread) {
        new Thread() {
            public void run() {
                while (true) {
                    if (exitScan()) {
                        if (fileIteratorThread.isAlive()) {
                            fileIteratorThread.interrupt();
                            return;//该线程退出
                        }
                    }
                }//while
            }
        }.start();
    }//readCommand
}

