package com.hadly.introspector;

import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hadly on 2017/6/15.
 * 见
 * http://blog.csdn.net/changqianger/article/details/45341651
 * http://blog.csdn.net/x605940745/article/details/20240959
 */
public class IntrospectorTest {
    public static void main(String[] args) throws Exception {
//        testSetValue();
//        copyProperties();
//        copyPropertiesUsingBeanUtils();
//        anotherWayToIntrospect();
//        cascadeExample();
        normalBeanCopy();
    }

    private static void normalBeanCopy(){
        Student student = new Student();
        student.setName("name1");
        student.setAge(1);

        //NOTE: Student1里面age即便叫age111，也可以复制成功，因为Java的Introspect默认用getter setter来拷贝的
        Student1 student1 = new Student1();
        System.out.println("student=" + student + ", student1=" + student1);

        try {
            BeanUtils.copyProperties(student1, student);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("student=" + student + ", student1=" + student1);
    }

    /**
     * 级联设值的一个例子
     * http://blog.csdn.net/hahalzb/article/details/5972421
     */
    private static void cascadeExample() throws Exception{
        Student student = new Student();
        student.setName("name1");
        student.setAge(10);

        String proName = "address";
        Address address = new Address();
        address.setAdd("address1");
        BeanUtils.setProperty(student, proName, address);
        System.out.println("address=" + BeanUtils.getProperty(student, proName));

        BeanUtils.setProperty(student, "address.add", "address2");
        System.out.println("address obj=" + BeanUtils.getProperty(student, "address"));//TODO 这里获取的是类实例
        System.out.println("address=" + BeanUtils.getProperty(student, "address.add"));

        // TODO 之所以可以 BeanUtils.setProperty(student, "address.add", "address2");这样写，
        // 那是因为Address类中有getAdd()和setAdd()方法，即Address类中相当于有add这个属性。
    }

    /**
     * 另外一种方式的内省.
     * </p>
     * 通过类 Introspector 来获取某个对象的 BeanInfo 信息，然后通过 BeanInfo 来
     * 获取属性的描述器（ PropertyDescriptor ），通过这个属性描述器就可以获取某个
     * 属性对应的 getter/setter 方法，然后我们就可以通过反射机制来调用这些方法
     *
     * @throws Exception
     */
    private static void anotherWayToIntrospect() throws Exception {
        Class<?> cl = Class.forName("com.hadly.introspector.Student");
        // 在bean上进行内省
        BeanInfo beaninfo = Introspector.getBeanInfo(cl, Object.class);
        PropertyDescriptor[] pro = beaninfo.getPropertyDescriptors();
        Student p = new Student();
        System.out.print("Student的属性有:");
        for (PropertyDescriptor pr : pro) {
            System.out.println(pr.getName() + " ");
        }
        System.out.println("");
        for (PropertyDescriptor pr : pro) {
            // TODO 获取的就是 set 方法
            Method writeme = pr.getWriteMethod();
            if (pr.getName().equals("name")) {
                // 执行方法
                writeme.invoke(p, "设置的名字");
            }
            if (pr.getName().equals("age")) {
                writeme.invoke(p, 23);
            }
            // 获取beal的get方法
            Method method = pr.getReadMethod();
            System.out.println("get方法=" + method.getName() + ", value=" + method.invoke(p) + " ");
        }
    }

    /**
     * BeanUtils;是apache给出的，用来解决使用内省机制繁琐的问题。
     * 使用时要导入两个包：commons-beanutils-1.9.2.jar和commons-logging-1.1.1.jar
     * <p>
     * 使用内省机制要比反射简单一点，使用工具后这种差异更明显了
     */
    private static void copyPropertiesUsingBeanUtils() {
        Map map = getParameterMap();
        Student student = new Student();
        try {
            BeanUtils.populate(student, map);//使用BeanUtils的populate方法，将Map中的键值对赋值到Bean中
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("after bean copy, student, " + student);
        System.out.println("after bean copy, address, " + student.getAddress().getAdd());
    }

    private static Map<String, Object> getParameterMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "zhangsan");
        map.put("age", "34");
        Address address = new Address();
        address.setAdd("beijing");
        map.put("address", address);
        return map;
    }

    /**
     * 对Bean所有属性进行赋值的例子
     */
    private static void copyProperties() throws Exception {
        Map<String, Object> map = getParameterMap();

        Student student = new Student();
        //得到参数Map
        for (Map.Entry<String, Object> me : map.entrySet()) {
            String name = me.getKey();
            Object value = me.getValue();
            System.out.println("name=" + name + ", value=" + value);
            //分别得到每个每个属性的描述对象
            PropertyDescriptor descriptor = new PropertyDescriptor(name, Student.class);
            Method method = descriptor.getWriteMethod();
            if (name.equals("age")) {
                method.invoke(student, Integer.parseInt(value.toString()));
            } else {
                method.invoke(student, (Object) value);
            }//else
        }//for
        System.out.println("student, " + student);
    }

    private static void testSetValue() {
        Student student = new Student();
        //得到对属性的描述
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor("name", Student.class);
            //得到写方法
            Method method = descriptor.getWriteMethod();
            System.out.println("before, " + student);
            method.invoke(student, "name参数");
            System.out.println("after, " + student);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
