package com.hadly.rpc;

import java.net.InetSocketAddress;

/**
 * Created by mi-123 on 2017/8/6.
 */
public class RpcTest {
    public static void main(String[] args) throws Exception {
        //1.provider暴露服务
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    RpcExporter.exporter("localhost", 8081);
                } catch (Exception e) {
                    e.printStackTrace();
                }//catch
            }
        });
        thread.start();

        //2.consumer调用provider提供的服务
        RpcImporter<EchoServiceImpl> importer = new RpcImporter<EchoServiceImpl>();
        InetSocketAddress serverAddr = new InetSocketAddress("localhost", 8081);
        //TODO 用EchoServiceImpl有点问题，Consumer不应该指定provider的具体实现类？？？
        //TODO 想想Dubbo是怎么实现的？？？
        IEchoService service = importer.importer(EchoServiceImpl.class, serverAddr);
        String result = service.echo("are you ok?");
        System.out.println("RpcTest result=" + result);
    }
}
