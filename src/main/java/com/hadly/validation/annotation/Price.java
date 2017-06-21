package com.hadly.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.*;

/**
 * Created by hadly on 2017/6/20.
 *
 * @Price 注解结合了默认的几个Constraint，所以没有必要自定义PriceValidator
 */
@Max(10000)
@Min(8000)
@Constraint(validatedBy = {})
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {
    String message() default "错误的价格";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
