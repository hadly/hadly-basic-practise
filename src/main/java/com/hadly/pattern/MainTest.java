package com.hadly.pattern;

import com.hadly.pattern.strategy.*;

/**
 * Created by hadly on 2017/10/31.
 */
public class MainTest {
    public static void main(String[] args) {
        //1.策略模式
        testStrategySimple();
        testStrategyWithoutFactory();
        testStrategyCombinedWithFactory();

        //2.

        //3.
    }

    public static void testStrategySimple() {
        IStrategy strategy = new ConcreteStragetyA();
        Context context = new Context(strategy);
        context.contextInterface();

        strategy = new ConcreteStragetyB();
        context = new Context(strategy);
        context.contextInterface();
        System.out.println("\n");
    }

    /**
     * 用简单工厂模式时，客户端需要认识两个类，CashFactory和ICash
     */
    public static void testStrategyWithoutFactory() {
        ICash cash = CashFactory.createCashAccept(CashFactory.CASH_NORMAL);
        double total = cash.acceptCash(100.23);
        System.out.println("total=" + total);

        ICash cash1 = CashFactory.createCashAccept(CashFactory.CASH_DEBATE);
        double total1 = cash1.acceptCash(100.00);
        System.out.println("total1=" + total1);
    }

    /**
     * 这种场景，client只需要认识CashContext类即可
     */
    public static void testStrategyCombinedWithFactory() {
        System.out.println("\n");
        CashContext cashContext = new CashContext(CashFactory.CASH_NORMAL);
        double result = cashContext.getResult(100.23);
        System.out.println("result=" + result);

        cashContext = new CashContext(CashFactory.CASH_DEBATE);
        result = cashContext.getResult(100.00);
        System.out.println("result=" + result);
    }
}
