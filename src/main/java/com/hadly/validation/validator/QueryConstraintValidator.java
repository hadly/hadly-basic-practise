package com.hadly.validation.validator;

import com.hadly.validation.OrderQuery;
import com.hadly.validation.annotation.QueryConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by hadly on 2017/6/21.
 */
public class QueryConstraintValidator implements ConstraintValidator<QueryConstraint, OrderQuery> {
    public void initialize(QueryConstraint constraintAnnotation) {

    }

    public boolean isValid(OrderQuery orderQuery, ConstraintValidatorContext context) {
        if (null == orderQuery) {
            return false;
        }

        long from = orderQuery.getFrom().getTime();
        long to = orderQuery.getTo().getTime();
        System.out.println("from=" + from + ", to=" + to);
        return from < to;
    }
}
