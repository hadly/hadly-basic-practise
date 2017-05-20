package com.hadly.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hadly on 2017/2/14.
 */
public class GenericTest {
    public void mapWithOutGeneric() {
        Map map = new HashMap();
        //不能既放置 string又放置int；第一次放了String后，就会认为该map是存放string的map
//        map.put("key", "value");
        map.put("key1", 123);
        String s = (String) map.get("key");
        Integer s1 = (Integer) map.get("key1");
        System.out.println("value=" + s + ", value1=" + s1);
    }

    public void printList1(List list) {
        for (Object o : list) {
            System.out.println("o=" + o);
        }
    }

    public void printList2(List<Object> list) {
        for (Object o : list) {
            System.out.println("o=" + o);
        }
    }

    //通配符
    public void printList3(List<?> list) {
        for (Object o : list) {
            System.out.println("o=" + o);
        }
    }

    public void addToList1() {
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(42));
        List<?> lu = li;
        System.out.println(lu.get(0));
    }

    public void addToList2() {
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(42));
        List<?> lu = li;
        //有问题，因为编译器不能知道lu中到底是什么类型的参数，所以没法添加
//        lu.add(new Integer(43));

        //这个是可以的，因为不需要知道里面的参数类型
        lu.clear();
//        System.out.println(lu.get(0));
    }

    //方法泛型
    private <T> T ifThenElse(boolean b, T first, T second) {
        return b ? first : second;
    }

    //用comparable进行比较
    private static <T extends Comparable<T>> T max(T o1, T o2) {
        if (o1.compareTo(o2) > 0) {
            return o1;
        }
        return o2;
    }

    private interface MapTest<T1, T2, T3> {
        public T1 getT1();

        public T2 getT2();

        public T3 getT3();

        public void setValue(T1 t1, T2 t2, T3 t3);
    }

    //    private class Test<? extends Integer>{} // ？不能出现在类参数中
    private class Test<T extends Integer> {
    }

    //private class Hlist<V>{
    private class Hlist<V extends Integer> {
        private V[] array;

        public Hlist(int capacity) {
//            array = new V[capacity]; // 这样是错误的，没法做
            array = (V[]) new Object[capacity]; //可以这样，或者用反射的方式实例化
        }

        public Hlist(int capacity, Class<?> clazz) {
//            Class X = clazz.newInstance();
//            array = X[capacity];
            //TODO:不知道怎么用反射生成？？？
        }
    }

    private interface Lcollection<V> {
        boolean addAll(Lcollection<? extends V> input);

        boolean removeAll(Lcollection<?> input);
    }

    private void classTest() {
        Class<?> test = null;
        test = String.class;
        test = Integer.class;//这样应该可以，因为？就标示所有类型的都可以用这个来代替
    }


}
