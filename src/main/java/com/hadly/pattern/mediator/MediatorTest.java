package com.hadly.pattern.mediator;

/**
 * 《中介者模式》
 * --用中介者对象来封装一系列的对象交互
 * --中介者让各个对象不需要显示的互相引用，解耦，且可以独立的改变他们的交互方式
 * --将对象如何协作进行了抽象
 * --计算机程序，菜单、文本控件、按钮等，每个控件不是相互耦合的，而是通过Form窗体
 *   事件机制，事件都是在Form窗体的代码中执行的，所有控件交互都是由Form完成的
 * --中介者模式，用于定制一个分布在多个了类中的行为，而又不想生成太多子类的场合
 * Created by lizhinian on 2017/11/21.
 */
public class MediatorTest {
    public static void main(String[] args) {
        ConcreteMediator m = new ConcreteMediator();

        // 让两个具体同事类，认识中介者；互相持有
        AbstractColleague c1 = new Colleague1(m);
        AbstractColleague c2 = new Colleague2(m);

        // 让中介者认识各个具体的同事类对象
        m.setColleague1(c1);
        m.setColleague2(c2);

        // 具体同事类之间发消息，都通过中介者对象
        c1.send("from c1 - 吃过饭了吗");
        c2.send("from c2 - 吃过了");
    }
}
