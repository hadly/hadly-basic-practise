package com.hadly.validation.validator;

import com.hadly.validation.IUniValidator;
import com.hadly.validation.OrderQuery;
import com.hadly.validation.Product;
import com.hadly.validation.annotation.UniConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * Created by hadly on 2017/6/21.
 */
public class UniConstraintValidator implements ConstraintValidator<UniConstraint, IUniValidator> {
    public void initialize(UniConstraint constraintAnnotation) {

    }

    public boolean isValid(IUniValidator value, ConstraintValidatorContext context) {
        if (value instanceof Product) {
            String productName = ((Product) value).getProductName();
            System.out.println("productName=" + productName);
            return false;
        } else if (value instanceof OrderQuery) {
            Date from = ((OrderQuery) value).getFrom();
            System.out.println("from=" + from);
            return false;
        }
        return false;
    }
}
