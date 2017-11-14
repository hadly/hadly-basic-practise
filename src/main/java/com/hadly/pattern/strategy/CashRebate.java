package com.hadly.pattern.strategy;

/**
 * Created by hadly on 2017/10/31.
 */
public class CashRebate implements ICash {
    private double moneyRebate = 1d;

    public CashRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public double acceptCash(double money) {
        System.out.println("折扣收费 " + money);
        return money * moneyRebate;
    }
}
