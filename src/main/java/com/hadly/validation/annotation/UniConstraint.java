package com.hadly.validation.annotation;

import com.hadly.validation.IUniValidator;
import com.hadly.validation.validator.UniConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by hadly on 2017/6/21.
 */
@Constraint(validatedBy = {UniConstraintValidator.class})
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniConstraint {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //TODO 这里不能直接写成接口，会出错
    Class<? extends IUniValidator> uniValidator();

}
