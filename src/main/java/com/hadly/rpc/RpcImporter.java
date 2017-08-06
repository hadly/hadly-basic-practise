package com.hadly.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by mi-123 on 2017/8/6.
 * 客户端本地服务代理
 */
public class RpcImporter<T> {
    public T importer(final Class<T> serviceClazz, final InetSocketAddress address) {
        InvocationHandler invocationHandler = new ServiceInvocationHandler(address, serviceClazz);
        T proxyedClass = (T) Proxy.newProxyInstance(serviceClazz.getClassLoader(),
                serviceClazz.getInterfaces(), invocationHandler);
        //TODO 为何原文写的是new Class<?>[] {serviceClazz.getInterfaces()[0]}，为何只有一个接口？
        return proxyedClass;
    }

    private class ServiceInvocationHandler implements InvocationHandler {
        InetSocketAddress address = null;
        Class clazz = null;

        public ServiceInvocationHandler(InetSocketAddress address, Class clazz) {
            this.address = address;
            this.clazz = clazz;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Socket socket = null;
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;
            try {
                socket = new Socket();
                socket.connect(address);
                outputStream = new ObjectOutputStream(socket.getOutputStream());

                outputStream.writeUTF(clazz.getName());
                outputStream.writeUTF(method.getName());
                outputStream.writeObject(method.getParameterTypes());
                outputStream.writeObject(args);

                inputStream = new ObjectInputStream(socket.getInputStream());
                return inputStream.readObject();
            } finally {
                if (null != socket) {
                    socket.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            }//finally
        }//invoke
    }//inner class
}
