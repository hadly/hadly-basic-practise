package com.hadly.pattern.state;

/**
 * 《状态模式》
 * --状态接口，一个对象的内在状态改变时允许改变其行为
 * --把状态的判断逻辑转移到表示不同状态的一系列类中
 *
 * --将特定的状态相关的行为都放在一个对象中，由于所有与状态相关的代码都存在于某个concreteState中，
 *   所以，通过定义新子类，可很容易的增加新的状态和转换。如onFail等方法放在TransStaus状态中
 *
 * --State表示抽象状态类，定义一个接口，封装与Work的特定状态相关的行为
 *   即，work表示一个状态，状态不同时，状态类state的coding()方法表现出的行为不同
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
