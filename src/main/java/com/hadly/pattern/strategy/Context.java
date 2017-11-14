package com.hadly.pattern.strategy;

/**
 * Created by hadly on 2017/10/31.
 */
public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 根据具体的策略对象，调用其算法的方法
     */
    public void contextInterface() {
        strategy.algorithmInterface();
    }
}
