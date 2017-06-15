package com.hadly.jmx;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

/**
 * Created by hadly on 2017/6/15.
 * 用于远程访问Mbean
 */
public class Client {
    public static void main(String[] args) throws IOException, Exception, NullPointerException {
        JMXServiceURL url = new JMXServiceURL
                ("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        //ObjectName的名称与前面注册时候的保持一致
        ObjectName mbeanName = new ObjectName("jmxBean:name=hello");

        System.out.println("Domains ......");
        String[] domains = mbsc.getDomains();

        for (int i = 0; i < domains.length; i++) {
            System.out.println("doumain[" + i + "]=" + domains[i]);
        }

        System.out.println("\nMBean count = " + mbsc.getMBeanCount());
        //设置指定Mbean的特定属性值
        //这里的setAttribute、getAttribute操作只能针对bean的属性
        //例如对getName或者setName进行操作，只能使用Name，需要去除方法的前缀
        //TODO:为什么是大写？
        // 对属性进行赋值和取值，这里我们不能直接调用方法，
        // 而是通过setAttribute、getAttrubute方法来进行操作，则属性的首字母要大写
        // Note:虽然操作的是attribute，但是set方法会被调用
        mbsc.setAttribute(mbeanName, new Attribute("Name", "杭州"));
        mbsc.setAttribute(mbeanName, new Attribute("Age", "1990"));
        String age = (String) mbsc.getAttribute(mbeanName, "Age");
        String name = (String) mbsc.getAttribute(mbeanName, "Name");
        System.out.println("age=" + age + ";name=" + name);

        //对资源里面的方法进行操作有两种方式：一是通过代理直接调用方法；二是通过JAVA的反射注入的方式进行方法的调用
        HelloMBean proxy = MBeanServerInvocationHandler.
                newProxyInstance(mbsc, mbeanName, HelloMBean.class, false);
        proxy.helloWorld();
        proxy.helloWorld("migu");
        proxy.getTelephone();
        //invoke调用bean的方法，只针对非设置属性的方法
        //例如invoke不能对getName方法进行调用
        //TODO:是不是不能对有返回值的进行调用
        mbsc.invoke(mbeanName, "getTelephone", null, null);
        mbsc.invoke(mbeanName, "helloWorld",
                new String[]{"I'll connect to JMX Server via client2"}, new String[]{"java.lang.String"});
        mbsc.invoke(mbeanName, "helloWorld", null, null);
    }
}
