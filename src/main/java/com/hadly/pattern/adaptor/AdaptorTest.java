package com.hadly.pattern.adaptor;

/**
 * Created by lizhinian on 2017/11/17.
 */
public class AdaptorTest {
    public static void main(String[] args) {
        Target target = new Adaptor();
        target.request();
        // 表面上看起来调用了客户希望的 request 方法，实际调用了经过适配的specialRequest方法
    }
}
