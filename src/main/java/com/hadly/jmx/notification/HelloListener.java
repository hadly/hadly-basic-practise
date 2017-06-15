package com.hadly.jmx.notification;

import com.hadly.jmx.Hello;

import javax.management.Notification;
import javax.management.NotificationListener;

/**
 * Created by hadly on 2017/6/15.
 */
public class HelloListener implements NotificationListener{

    public void handleNotification(Notification notification, Object handback) {
        if(handback instanceof Hello)
        {
            Hello hello = (Hello)handback;
            hello.printHello(notification.toString());
        }
    }
}
