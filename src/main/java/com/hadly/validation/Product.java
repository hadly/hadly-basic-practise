package com.hadly.validation;

import com.hadly.validation.annotation.Price;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by hadly on 2017/6/20.
 */
public class Product implements IUniValidator{
    @NotEmpty
    private String productName;
    /**
     * 必须在 8000 至 10000 的范围内
     *
     * @Price 是一个定制化的 constraint
     */
    @Price
    private float price;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
