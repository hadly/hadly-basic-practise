package com.hadly.pattern.mediator;

/**
 * 具体中介者，他要知道所有具体同事类，并从具体同事接受消息，或发送消息到具体同事
 * Created by lizhinian on 2017/11/23.
 */
public class ConcreteMediator implements IMediator{
    private AbstractColleague colleague1;
    private AbstractColleague colleague2;

    public void setColleague1(AbstractColleague colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(AbstractColleague colleague2) {
        this.colleague2 = colleague2;
    }

    /**
     *
     * @param message 要发送的消息
     * @param colleague 要发送消息的同事对象
     */
    @Override
    public void send(String message, AbstractColleague colleague) {
        // 如果是colleague2发的消息，那么就通知到colleague1，后者收到消息
        if(colleague.equals(colleague2)){
            colleague1.notifyTo(message);
        }else{
            colleague2.notifyTo(message);
        }
    }//
}
