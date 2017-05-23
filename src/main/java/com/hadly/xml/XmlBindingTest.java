package com.hadly.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by hadly on 2017/5/23.
 */
public class XmlBindingTest {
    public static void main(String[] args) throws JAXBException {
        marshal();
        unmarshal();
    }

    private static void marshal() throws JAXBException {
        Phone phone = new Phone("iphone", 13520000);
        Employee employee = new Employee("zhangsan", "beijing", phone);

        JAXBContext context = JAXBContext.newInstance(Employee.class);    // 获取上下文对象
        Marshaller marshaller = context.createMarshaller(); // 根据上下文获取marshaller对象

        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  // 设置编码字符集
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进

        marshaller.marshal(employee, System.out);   // 打印到控制台

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(employee, baos);
        String xmlObj = new String(baos.toByteArray());         // 生成XML字符串
        System.out.println("string类型的XML:\n" + xmlObj);
    }

    private static void unmarshal() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<employee>\n" +
                "    <address>beijing</address>\n" +
                "    <name>zhangsan</name>\n" +
                "    <phone>\n" +
                "        <brand>iphone</brand>\n" +
                "        <num>13520000</num>\n" +
                "    </phone>\n" +
                "</employee>";
        JAXBContext jc = JAXBContext.newInstance(Employee.class);
        Unmarshaller u = jc.createUnmarshaller();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        Employee obj = (Employee) u.unmarshal(inputStream);
        System.out.println(obj);
    }
}
