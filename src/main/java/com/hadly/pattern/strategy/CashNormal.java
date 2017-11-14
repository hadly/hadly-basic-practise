package com.hadly.pattern.strategy;

/**
 * Created by hadly on 2017/10/31.
 */
public class CashNormal implements ICash{
    @Override
    public double acceptCash(double money) {
        System.out.println("现金收费 " + money);
        return money;
    }
}
