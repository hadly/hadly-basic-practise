package com.hadly.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by mi-123 on 2017/5/16.
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        objectWithInteface();
        objectWithoutInterface();
    }

    private static void objectWithInteface() {
        Integer value = 100;
        TraceHandler handler = new TraceHandler(value);
        Class[] interfaces = value.getClass().getInterfaces();
        //1.Integer实现了comparable接口(被代理的类，必须实现某个接口)
        Object proxy = Proxy.newProxyInstance(null, interfaces, handler);

        //2.即便不属于comparable接口，hashcode\equals\toString三个方法仍然会被代理
        System.out.println("hashcode: " + proxy.hashCode() + "\n");
        System.out.println("toString: " + proxy.toString() + "\n");
        //3.getClass不会被代理
        System.out.println("getClass: " + proxy.getClass() + "\n");
        System.out.println("compareTo: " + ((Comparable) proxy).compareTo(12) + "\n");

        //4.因为proxy是Integer的代理类，所以可以调Integer
        System.out.println("compareTo 123: " + ((Integer)proxy).toString() + "\n");
    }

    private static void objectWithoutInterface(){
        ClassWithoutInterface source = new ClassWithoutInterface();
        source.setName("name");
        source.setAddress("address");

        //1.即便没有接口，Object中的hashcode\equals\toString三个方法仍然会被代理
        TraceHandler handler = new TraceHandler(source);
        Object proxy = Proxy.newProxyInstance(null, source.getClass().getInterfaces(), handler);
        System.out.println("toString: " + proxy.toString());

        //2.下面就会报错，因为无接口，没法代理出来
        ClassWithoutInterface classWithoutInterface = (ClassWithoutInterface) proxy;
        System.out.println("name: "+ classWithoutInterface.getName());
    }
}
