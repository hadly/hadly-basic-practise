package com.hadly.pattern.strategy;

/**
 * Created by hadly on 2017/10/31.
 */
public class CashFactory {
    public static final String CASH_NORMAL = "正常收费";
    public static final String CASH_DEBATE = "满100减20";

    /**
     * 普通方式创建对象
     *
     * @param type
     * @return
     */
    public static ICash createCashAccept(String type) {
        switch (type) {
            case CASH_NORMAL:
                return new CashNormal();
            case CASH_DEBATE:
                return new CashRebate(0.8);
            default:
                return null;
        }
    }
}
