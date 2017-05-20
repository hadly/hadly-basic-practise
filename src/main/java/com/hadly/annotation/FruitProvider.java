package com.hadly.annotation;

import java.lang.annotation.*;

/**
 * Created by hadly on 2017/2/14.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
    //供应商编号
    public int id() default -1;

    //供应商名称
    public String name() default "";

    //供应商地址
    public String address() default "";
}
