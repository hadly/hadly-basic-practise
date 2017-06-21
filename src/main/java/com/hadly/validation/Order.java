package com.hadly.validation;

import com.hadly.validation.annotation.OrderConstraint;
import com.hadly.validation.annotation.Status;
import com.hadly.validation.annotation.UniConstraint;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by hadly on 2017/6/20.
 */
@OrderConstraint(message = "覆盖版：校验order综合信息失败，校验address")
public class Order {
    /**
     * 错误信息会默认使用这里的message，如果没有，才从注解的default中获取
     * 1.${validatedValue}表示orderId的值，比如12345678987654321。validatedValue这个名字
     *   是规定的，不能随意改动
     */
    @NotNull
    @Size(min = 10, max = 10, message = "${validatedValue} 长度在{min} {max}之间")
    private String orderId;
    @NotEmpty
    private String customer;
    @Email
    private String email;
    @NotEmpty
    private String address;
    /**
     * 必须不为 null, 必须是下面四个字符串'created', 'paid', 'shipped', 'closed'其中之一
     *
     * @Status 是一个定制化的 contraint
     */
    @NotNull
    @Status
    private String status;
    @NotNull
    private Date createDate;
    /**
     * TODO
     * 1.嵌套验证
     * 2.如果有UniConstraint和Valid标签，每个校验都会执行
     */
    @UniConstraint(uniValidator = Product.class, message = "{com.hadly.validation.annotation.UniConstraint.message}")
    @Valid
    private Product product;
    @UniConstraint(uniValidator = OrderQuery.class, message = "UniConstraint校验orderQuery失败")
    @Valid
    private OrderQuery orderQuery;

    public OrderQuery getOrderQuery() {
        return orderQuery;
    }

    public void setOrderQuery(OrderQuery orderQuery) {
        this.orderQuery = orderQuery;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
