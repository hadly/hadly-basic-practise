package com.hadly.reflect;

/**
 * Created by hadly on 2017/2/15.
 */
public class Demo {
    public String publicField;
    private String privateField;

    public void publicMethod() {
        System.out.println("call public method");
    }

    public void publicMethod1(int param1, String param2) {
        System.out.println("call private method, param1=" + param1 + ", param2=" + param2);
    }

    private void privateMethod(int param1, String param2) {
        System.out.println("call private method, param1=" + param1 + ", param2=" + param2);
    }

    public Demo() {
    }//必须有有这个，才能Demo demo = (Demo) clazz.newInstance();创建对象

    public Demo(String publicField, String privateField) {
        this.publicField = publicField;
        this.privateField = privateField;
    }

    @Override
    public String toString() {
        return "filed," + publicField;
    }
}
