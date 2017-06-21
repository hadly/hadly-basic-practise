//package com.hadly.validation.validator;
//
//import com.hadly.validation.annotation.Price;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
///**
// * Created by hadly on 2017/6/20.
// * 没有必要实现
// */
//public class PriceValidator implements ConstraintValidator<Price, String> {
//    public void initialize(Price constraintAnnotation) {
//
//    }
//
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        long price = Long.parseLong(value);
//        return price > 800 && price < 1000;
//    }
//}
