package com.hadly.validation.annotation;

import com.hadly.validation.validator.QueryConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by hadly on 2017/6/21.
 * 这个注解是加载class的，所以，@Target里面是ElementType.TYPE
 */
@Constraint(validatedBy = {QueryConstraintValidator.class})
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryConstraint {
    String message() default "from 必须小于等于 to";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
