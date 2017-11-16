package com.hadly.pattern.decorate;

/**
 * 装饰抽象类，继承了Component（因为要装饰这个类，所以继承该接口）
 * Created by hadly on 2017/11/1.
 */
public class Decorator implements IComponent {
    private IComponent component;

    /**
     * 该方法用于设置被装饰对象
     * @param component
     */
    public void decorate(IComponent component) {
        this.component = component;
    }

    /**
     * 装饰了这个方法，默认情况就是执行Icomponent的show()方法，即，原方法（被装饰方法）
     */
    @Override
    public void show() {
        if (null != component) {
            component.show();
        }
    }//
}
