package com.hadly.classloader;

import java.lang.reflect.Method;
import java.net.URL;

/**
 * Created by hadly on 2017/2/15.
 * 示例见：http://www.cnblogs.com/szlbm/p/5504631.html
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
//        test01();
        test02();
//        test03();
//        test04();
        test05();
    }

    private static void test04() {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        while (loader != null) {
            print(loader);
            loader = loader.getParent();
        }
        print("finally=" + loader);
        //1.正常加载时，加载器如下：
        //sun.misc.Launcher$AppClassLoader@15b94ed3
        //sun.misc.Launcher$ExtClassLoader@38e609c9
        //finally=null

        //2.将工程打包后放到jre/lib/ext目录下面，hadly-test.jar，再次运行时，打印如下：
        //即，会由ExtClassLoader加载
        //sun.misc.Launcher$ExtClassLoader@38e609c9
        //finally=null

        //3.将class文件，放到下面目录下
        //D:\Program Files\Java\jdk1.7.0_15\jre\classes\com\hadly\classloader
        //就会直接用BootStrapClassloader加载，加载日志如下：
        //finally=null
    }

    private static void test03() throws Exception {
        MyClassLoader classLoader1 = new MyClassLoader();
        Class<?> clazz1 = Class.forName("com.hadly.classloader.Person", true, classLoader1);

        {
            ClassLoader loader = clazz1.getClassLoader();
            while (loader != null) {
                print(loader);
                loader = loader.getParent();
            }
            print("finally=" + loader);
            //打印如下：
//            com.hadly.classloader.MyClassLoader@4bcc946b
//            sun.misc.Launcher$AppClassLoader@38e609c9
//            sun.misc.Launcher$ExtClassLoader@68c884e
//            finally=null
        }

        MyClassLoader classLoader2 = new MyClassLoader();
        //如果下面的classLoader2改成classLoader1，那么 print(clazz1 == clazz2); 就会返回true
        Class<?> clazz2 = Class.forName("com.hadly.classloader.Person", true, classLoader2);

        print(clazz1 == clazz2);//因为是两个不同的实例，所以返回 false
        Method method = clazz1.getMethod("getName", String.class);
        method.invoke(clazz1.newInstance(), "hadly");//这个是没问题的，因为clazz1是一样的

        //NOTE：下面的不行，因为clazz1和clazz2虽然是一个字节码，但不是同一个ClassLoader加载的，所以不是同一个类
        //Exception in thread "main" java.lang.IllegalArgumentException: object is not an instance of declaring class
//        method.invoke(clazz2.newInstance(), "hadly");

    }

    /**
     * TODO:注意，在运行之前，需要将E:\workspace\eclipse\hadly-test\bin\com\hadly\classloader目录
     * 下的Person.class删除掉，否则会被sun.misc.Launcher$AppClassLoader加载进来
     * TODO: 同时需要将Person.class放到d:\\下面，因为MyClassloader会默认从那儿查找
     * @throws Exception
     */
    private static void test02() throws Exception {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> clazz = Class.forName("com.hadly.classloader.Person", true, classLoader);
//        Class<?> clazz = Class.forName("com.hadly.classloader.Person");//这个会报classLoader=sun.misc.Launcher$AppClassLoader@15b94ed3

        print("generated clazz=" + clazz);
        Object obj = clazz.newInstance();
        print("classLoader=" + obj.getClass().getClassLoader());//
        //上面的语句可以得到 - classLoader=com.hadly.classloader.MyClassLoader@5ab04589
    }

    private static void test01() {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            print(url);
        }

        print(System.getProperty("sun.boot.class.path"));
    }

    private static void print(Object o) {
        if (null != o) {
            System.out.println(o);
        }
    }

    private static void test05(){
        //虚拟机启动时加载，加载的是JAVA_HOME/lib/下的rt.jar下的.class文件,
        //设置虚拟机参数为"-XX:+TraceClassLoading"来获取类加载信息
        System.out.println("test05");
    }
}
