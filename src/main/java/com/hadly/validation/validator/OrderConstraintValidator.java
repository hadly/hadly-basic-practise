package com.hadly.validation.validator;

import com.hadly.validation.Order;
import com.hadly.validation.annotation.OrderConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by hadly on 2017/6/21.
 */
public class OrderConstraintValidator implements ConstraintValidator<OrderConstraint, Order> {
    public void initialize(OrderConstraint constraintAnnotation) {

    }

    public boolean isValid(Order value, ConstraintValidatorContext context) {
        String address = value.getAddress();
        System.out.println("address=" + address);
        if (address != "testAddress") {
            return false;
        }
        return true;
    }
}
