package com.hadly.gc;

/**
 * Created by hadly on 2017/8/4.
 * 1.推荐使用的GC参数配置
 * -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC -Xloggc:/data/dataLogs/gc/barcode-gc.log
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/dataLogs/dump/barcode.dump
 * 2.
 */
public class PrintGCTest {
    public static void main(String[] args) {
        testPrintGC();
    }

    private static void testPrintGC() {
        for (int i = 0; i <= 100; i++) {
            long oneG = 1024 * 1024 * 1024 * 1024;
            byte[] bytes = new byte[1024 * 1024 * 1024 * 1024];
            String str = new Object().toString() + "  " + i;
            System.out.println(str);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }//testPrintGC
}
