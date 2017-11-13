package com.hadly.pattern.state;

/**
 * 《状态模式》
 * Created by lizhinian on 2017/11/13.
 */
public interface State {

    /**
     * 同样是coding这个事情，在不同的Work中（比如不同时间段），做的事情不一样
     *
     * @param work
     */
    void coding(Work work);
}
