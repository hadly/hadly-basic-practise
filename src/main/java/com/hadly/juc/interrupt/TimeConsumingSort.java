package com.hadly.juc.interrupt;

import java.util.Random;

/**
 * Created by hadly on 2017/8/8.
 * 1.非常耗时的排序操作
 */
public class TimeConsumingSort {
    private static final int[] array = new int[80000];

    static {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(i + 1);
        }
    }

    /**
     * 非常耗时的排序操作
     *
     * @return
     */
    public static int sort() {
        long start = System.currentTimeMillis();
        System.out.println("sort begin");
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println("sort end. cost time=" + (System.currentTimeMillis() - start));
        return array[0];
    }
}
