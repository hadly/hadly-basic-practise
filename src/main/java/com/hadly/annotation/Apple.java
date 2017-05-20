package com.hadly.annotation;

/**
 * Created by hadly on 2017/2/14.
 */
public class Apple {
    @FruitName("苹果")
    private String appleName;

    //这里的返回值为什么不是enum，而是string呢？？？
    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String appleColor;

    @FruitProvider(id = 1, name = "红富士集团", address = "陕西省西安市")
    private String appleProvider;

    //下面的getter和setter不管是否生成，都能正常运行和使用
//    public String getAppleName() {
//        return appleName;
//    }
//
//    public void setAppleName(String appleName) {
//        this.appleName = appleName;
//    }
//
//    public String getAppleColor() {
//        return appleColor;
//    }
//
//    public void setAppleColor(String appleColor) {
//        this.appleColor = appleColor;
//    }
//
//    public String getAppleProvider() {
//        return appleProvider;
//    }
//
//    public void setAppleProvider(String appleProvider) {
//        this.appleProvider = appleProvider;
//    }
}
