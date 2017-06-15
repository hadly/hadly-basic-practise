package com.hadly.commontest;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by hadly on 2017/6/14.
 */
public class CommonTest {
    public static void main(String[] args){
        getLocalIp();
    }

    private static void getLocalIp(){
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            System.out.println("ip=" + host);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }//IP
}
