package com.hadly.annotation;

import java.lang.annotation.*;

/**
 * Created by hadly on 2017/2/14.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    Color fruitColor() default Color.GREEN;
    //和上面的定义形式虽然不一样的，但是作用是一样的
    //    Color value() default Color.GREEN;

    //定义枚举类
    public enum Color {
        BLUE, RED, GREEN
    }

    ;

}
