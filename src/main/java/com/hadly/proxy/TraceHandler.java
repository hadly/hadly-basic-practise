package com.hadly.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by mi-123 on 2017/5/16.
 * 可以将InvocationHandler接口的子类想象成一个代理的最终操作类，替换掉ProxySubject
 */
public class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    /**
     * @param proxy  被代理的对象
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //TODO:这里打印proxy会报错，不知道为什么
        System.out.println("proxy object=" + ", method=" + method.getName());
        if (null != args) {
            for (Object object : args) {
                System.out.println("arg=" + object);
            }
        }

        //1.可以在这里调用实际的target的方法，也可以不调用
        Object result = null;
        result = method.invoke(target, args);
        System.out.println("target result=" + result);

        //2.即便调用了target的方法，仍然可以返回别的值
        String methodName = method.getName();
        if (methodName.equals("hashCode")) {
            return 12345;
        } else if (methodName.equals("toString")) {
            return "toString-method";
        } else if (methodName.equals("getClass")) {
            return "getClass-method";
        } else if (methodName.equals("compareTo")) {
            return new Integer(1100);
        } else {
            return result;
        }
    }//invoke
}
