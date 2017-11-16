package com.hadly.pattern.state.trans;

/**
 * Created by lizhinian on 2017/11/14.
 */
public class TransAccept implements TransStatus{
    @Override
    public void onFail(Context context) {
        // accept失败
        // 会从context中读取一些上下文对象信息，用于辅助判断是否要进行状态变迁
        // 如果要进行状态变迁，就调用context的setNextStatus来进行状态变迁
    }

    @Override
    public void onException(Context context) {

    }

    @Override
    public void onSuccess(Context context) {

    }
}
