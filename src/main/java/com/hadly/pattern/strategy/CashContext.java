package com.hadly.pattern.strategy;

/**
 * Created by hadly on 2017/10/31.
 */
public class CashContext {
    private ICash cash;

    /**
     * 输入是收费类型，而不是具体的收费策略对象
     * 将实例化具体策略的过程由客户端转移到Context类中，简单工厂的应用
     *
     * @param cashType
     */
    public CashContext(String cashType) {
        switch (cashType){
            case CashFactory.CASH_NORMAL:
                cash = new CashNormal();
                break;
            case CashFactory.CASH_DEBATE:
                cash = new CashRebate(0.8);
                break;
            default:
                break;
        }
    }

    public double getResult(double money) {
        return cash.acceptCash(money);
    }
}
