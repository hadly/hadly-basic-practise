package com.hadly.pattern.state.trans;

/**
 * 交易状态
 * --特定状态相关的行为都放在该接口，子类实现
 * Created by lizhinian on 2017/11/14.
 */
public interface TransStatus {

    /**
     * 在该状态下，失败的处理
     * --特定交易状态，失败时，会根据上下文中状态的不同做不同处理
     *   比如，validate状态失败，直接返回即可；accept状态失败，可能还要有些清理工作
     */
    void onFail(Context context);

    /**
     * 在该状态下，异常的处理
     */
    void onException(Context context);

    /**
     * 成功的处理
     */
    void onSuccess(Context context);
}
