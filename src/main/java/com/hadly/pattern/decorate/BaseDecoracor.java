package com.hadly.pattern.decorate;

/**
 * Created by hadly on 2017/11/1.
 */
public class BaseDecoracor implements IComponent {
    private IComponent component;

    public void decorate(IComponent component) {
        this.component = component;
    }

    @Override
    public void show() {
        if (null != component) {
            component.show();
        }
    }
}
