package com.hadly.pattern.state.trans;

/**
 * 面向对象设计其实就是希望做到代码的责任分解
 *
 * Context 表示当前的状态，里面还会有一些另外的对象辅助判断状态的变迁
 *
 * Created by lizhinian on 2017/11/14.
 */
public class Context {
    private TransStatus transStatus;

    public void setNextStatus(TransStatus transStatus){
        this.transStatus = transStatus;
    }

    public void onSuccess(){
        transStatus.onSuccess(this);
    }
}
