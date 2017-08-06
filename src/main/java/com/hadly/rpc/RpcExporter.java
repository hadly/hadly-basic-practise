package com.hadly.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by mi-123 on 2017/8/6.
 */
public class RpcExporter {
    private static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void exporter(String hostname, int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(hostname, port));
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(new ExporterTask(socket));
            }
        } finally {
            //TODO 这里close之前不需要判断是否为null吗？
            serverSocket.close();
        }
    }

    private static class ExporterTask implements Runnable {
        Socket client = null;

        public ExporterTask(Socket client) {
            this.client = client;
        }

        public void run() {
            ObjectInputStream inputStream = null;
            ObjectOutputStream outputStream = null;
            try {
                inputStream = new ObjectInputStream(client.getInputStream());

                String interfaceName = inputStream.readUTF();
                System.out.println("provider received interfaceName=" + interfaceName);
                Class<?> service = Class.forName(interfaceName);
                String methodName = inputStream.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                Object[] args = (Object[]) inputStream.readObject();
                Method method = service.getMethod(methodName, parameterTypes);
                Object result = method.invoke(service.newInstance(), args);
                System.out.println("provider result=" + result);

                outputStream = new ObjectOutputStream(client.getOutputStream());
                outputStream.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != outputStream) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != inputStream) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }//finally

        }//run
    }


}
