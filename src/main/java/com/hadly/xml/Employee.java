package com.hadly.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hadly on 2017/5/23.
 */
@XmlRootElement
public class Employee {
    /**
     * 1.name用于指明生成的xml元素别名
     * 2.如果添加了XmlElement，不能再有getter方法，否则会报如下错误
     * <p>
     * Exception in thread "main" com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationsException: 1 counts of IllegalAnnotationExceptions
     * 类的两个属性具有相同名称 "name"
     * this problem is related to the following location:
     * at public java.lang.String com.hadly.xml.Employee.getName()
     * at com.hadly.xml.Employee
     */
    @XmlElement(name = "Name")
    private String name;
    private String address;
    private Phone phone;

    /**
     * NOTE：必须要有无参构造函数
     */
    public Employee() {
    }

    public Employee(String name, String address, Phone phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

//    public String getName() {
//        return name;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }
}
