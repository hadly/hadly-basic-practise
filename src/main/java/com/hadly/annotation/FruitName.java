package com.hadly.annotation;

import java.lang.annotation.*;

/**
 * Created by hadly on 2017/2/14.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
