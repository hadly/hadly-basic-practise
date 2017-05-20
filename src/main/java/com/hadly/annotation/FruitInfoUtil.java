package com.hadly.annotation;

import java.lang.reflect.Field;

/**
 * Created by hadly on 2017/2/15.
 */
public class FruitInfoUtil {

    public static void getFruitInfo(Class<?> clazz) {
        String strFruitName = "shuiguo name:";
        String strFruitColor = "shuiguo color:";
        String strFruitProvider = "shuiguo provider:";

        //获取类的所有字段；用declaredFields可以获取包含私有字段在内的所有字段
        Field[] fields = clazz.getDeclaredFields();
//        System.out.println("fields=" + fields);
        for (Field field : fields) {
            System.out.println("field=" + field);

            //判断是否有某个注解
            if (field.isAnnotationPresent(FruitName.class)) {
                //获取相应的注解
                FruitName fruitName = field.getAnnotation(FruitName.class);
                System.out.println(strFruitName + fruitName.value());
            } else if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = (FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor = strFruitColor + fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            } else if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider fruitProvider = (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvider = " 供应商编号：" + fruitProvider.id() + " 供应商名称：" + fruitProvider.name() + " 供应商地址：" + fruitProvider.address();
                System.out.println(strFruitProvider);
            }
        }


    }
}
