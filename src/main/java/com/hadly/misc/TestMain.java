package com.hadly.misc;

/**
 * Created by hadly on 2017/3/27.
 */
public class TestMain {
    public static void main(String[] args) {
        String barcode = "nucc001test";
        String nuccId = barcode.substring(0, 4);
        String instBcsId = barcode.substring(4, 7);
        System.out.println("nuccId=" + nuccId + ", instId=" + instBcsId);
    }
}
