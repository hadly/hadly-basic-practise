package com.hadly.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

/**
 * Created by hadly on 2017/6/20.
 */
public class ValidationMain {
    public static void main(String[] args) {
        Order order = new Order();
        order.setAddress("");
        order.setCreateDate(null);
        order.setCustomer("");
        order.setEmail("hadly email");
        order.setOrderId("12345678987654321");

        Product product = new Product();
        product.setPrice(100);
        product.setProductName("");

        order.setProduct(product);
        order.setStatus("illegal");

        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setFrom(new Date(1498024791));
        orderQuery.setTo(new Date(1498024721));

        order.setOrderQuery(orderQuery);

        //=========================

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        if (violations.size() == 0) {
            System.out.println("validation success.");
            return;
        }

        StringBuffer sb = new StringBuffer();
        for (ConstraintViolation<Order> violation : violations) {
            sb.append(violation.getPropertyPath().toString()).append(" ");
            sb.append(violation.getMessage()).append(" | ");
        }
        System.out.println("validation result=" + sb.toString());
    }//main
}
