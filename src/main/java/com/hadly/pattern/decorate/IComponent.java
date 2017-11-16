package com.hadly.pattern.decorate;

/**
 * Created by hadly on 2017/11/1.
 * 等待被装饰的对象的接口定义，可以给这些对象动态的添加职责
 */
public interface IComponent {
    /**
     * 该方法有个show()的方法，等待被装饰
     */
    void show();
}
