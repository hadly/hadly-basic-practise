package com.hadly.validation.annotation;

import com.hadly.validation.validator.OrderConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by hadly on 2017/6/21.
 * 和QueryConstraint类似，都是加载类上面的，用于校验某个类里面字段之间关系时候使用
 */
@Constraint(validatedBy = {OrderConstraintValidator.class})
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderConstraint {
    String message() default "order综合校验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
