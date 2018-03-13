//package com.hadly.jmx;
//
//import com.sun.jdmk.comm.HtmlAdaptorServer;
//
//import javax.management.*;
//import javax.management.remote.JMXConnectorServer;
//import javax.management.remote.JMXConnectorServerFactory;
//import javax.management.remote.JMXServiceURL;
//import java.lang.management.ManagementFactory;
//import java.rmi.registry.LocateRegistry;
//
///**
// * Created by hadly on 2017/6/15.
// * 见 http://www.cnblogs.com/dongguacai/p/5900507.html
// */
//public class HelloAgent {
//    public static void main(String[] args) throws MalformedObjectNameException,
//            NotCompliantMBeanException, InstanceAlreadyExistsException,
//            MBeanRegistrationException, InterruptedException {
//
//        //1.Jconsole监控
//        MBeanServer server = ManagementFactory.getPlatformMBeanServer();//通过工厂类获取MBeanServer，用来做MBean的容器
//        // ObjectName中的取名是有一定规范的，格式为：“域名：name=MBean名称”，其中域名和MBean的名称可以任意取。
//        // 这样定义后，就可以唯一标识我们定义的这个MBean的实现类了
//        ObjectName helloName = new ObjectName("jmxBean:name=hello");
//        //create mbean and register mbean(将Hello这个类注入到MBeanServer中，注入需要创建一个ObjectName类)
//        server.registerMBean(new Hello(), helloName);
//        //Thread.sleep(60 * 60 * 1000);//如果单独运行程序想要被Mbean连接，必须要做这个配置，避免线程退出
//
//        {
//            //2.HTTP监控
//            //TODO HTTP部分不工作，打开页面没有反应
//            //TODO HtmlAdaptorServer来自包jmxtools.jar，因为maven找不到，所以只能加入到lib中手动引入
//            ObjectName adapaterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
//            HtmlAdaptorServer adapter = new HtmlAdaptorServer();
//            server.registerMBean(adapter, adapaterName);
//            adapter.start();
//        }
//
//        //3.允许远程访问的设定
//        accessibleByRemote(server);
//    }
//
//    /**
//     * 允许远程访问的设定。用Jconsole远程访问时，输入“localhost:9999”
//     * 增加ip和port绑定部分的逻辑
//     *
//     * @param server
//     */
//    private static void accessibleByRemote(MBeanServer server) {
//        try {
//            //这个步骤很重要，注册一个端口，绑定url后用于客户端通过rmi方式连接JMXConnectorServer
//            LocateRegistry.createRegistry(9999);
//            //URL路径的结尾可以随意指定，但如果需要用Jconsole来进行连接，则必须使用jmxrmi
//            JMXServiceURL url = new JMXServiceURL
//                    ("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
//            JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
//            System.out.println("begin rmi start");
//            jcs.start();
//            System.out.println("rmi start");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
