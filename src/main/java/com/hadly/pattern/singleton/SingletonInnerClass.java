/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadly.pattern.singleton;

/**
 * 这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，它跟第三种和第四种方式不同的是
 * （很细微的差别）：第三种和第四种方式是只要Singleton类被装载了，那么instance就会被实例化
 * （没有达到lazy loading效果），而这种方式是Singleton类被装载了，instance不一定被初始化。
 * 因为SingletonHolder类没有被主动使用，只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，
 * 从而实例化instance。想象一下，如果实例化instance很消耗资源，我想让他延迟加载，另外一方面，
 * 我不希望在Singleton类加载时就实例化，因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载
 * ，那么这个时候实例化instance显然是不合适的。这个时候，这种方式相比第三和第四种方式就显得很合理。
 *
 * @author lizhinian
 */
public class SingletonInnerClass {

    private SingletonInnerClass() {
        System.out.println("i'm constructor of SingletonInnerClass");
    }

    public static SingletonInnerClass getInstance() {
        return SingletonHolder.instance;
    }

    public static void testSingleton() {
        System.out.println("call SingletonInnerClass.testSingleton(xxx)");
    }

    public void hello() {
        System.out.println("hello, i'm SingletonInnerClass");
    }

    /**
     * 在这个单例中，我们通过静态内部类来托管单例，当这个单例被加载时，不会初始化单例类，只有当getInstance方法被调用的时候，
     * 才会去加载SingletonHolder，从而才会去初始化instance。并且，单例的加载是在内部类的加载的时候完成的，
     * 所以天生对线程友好，而且也不需要synchnoized关键字，可以说是兼具了以上的两个优点
     */
    private static class SingletonHolder {

        //静态初始化器，由JVM来保证线程安全
        private static final SingletonInnerClass instance = new SingletonInnerClass();
        //上面可以是private也可以是public
    }
}
