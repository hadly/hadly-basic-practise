package com.hadly;

public class MainTest {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        //1.
//        new GenericTest().mapWithOutGeneric();

        //2.
//        List<String> list = new ArrayList<>();
//        list.add("nihao123");
////        new GenericTest().printList1(list);//这种没问题，但是可能会有警告
////        new GenericTest().printList2(list);//这种有问题，因为编译器没法将String转成Object
//        new GenericTest().printList3(list);

        //3.
//        new GenericTest().addToList1();
//        new GenericTest().addToList2();

        //4.
        test02(1, 2, 3, 4);

        //5.
        printClasspath();

    }

    private static void test02(int... input) {
        for (int in : input) {
            System.out.println(in);
        }
    }

    private static void printClasspath() {
        System.out.println("env" + System.getenv());
    }
}
