package com.hadly.jmx.notification;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * Created by hadly on 2017/6/15.
 */
public class Jack extends NotificationBroadcasterSupport implements JackMBean {

    private int seq = 0;

    public void hi() {
        //创建一个信息包
        Notification notify =
                //通知名称；谁发起的通知；序列号；发起通知时间；发送的消息
                new Notification("jack.hi", this, ++seq, System.currentTimeMillis(), "jack's msg");
        sendNotification(notify);
    }

    @Override
    public String toString() {
        return "Jack{" +
                "seq=" + seq +
                '}';
    }
}
