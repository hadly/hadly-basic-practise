package com.hadly.jmx;

/**
 * Created by hadly on 2017/6/22.
 * 1.如果要远程连接，需要做如下配置
 * -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false
 * 2.只是本地连接的话，不需要上述配置
 **/
public class JmxTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start jmx test");
        Thread.sleep(60 * 1000 * 1000);
    }
}
