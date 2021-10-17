package com.stelpolvo.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * 使用反射生产对象
 */
public class BeanFactory {
    private static HashMap<String, Object> map = new HashMap<>();

    static {
        InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document read = saxReader.read(inputStream);
            Element rootElement = read.getRootElement();
            List<Element> beanList = rootElement.selectNodes("//bean");
            for (int i = 0; i < beanList.size(); i++) {
                Element element = beanList.get(i);
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                Object o = Class.forName(clazz).getDeclaredConstructor().newInstance();
                map.put(id, o);
            }
            List<Element> propertyList = rootElement.selectNodes("//property");
            for (int i = 0; i < propertyList.size(); i++) {
                Element element = propertyList.get(i);
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");
                String id = element.getParent().attributeValue("id");
                Object parentObject = map.get(id);
                Method[] methods = parentObject.getClass().getMethods();
                for (int i1 = 0; i1 < methods.length; i1++) {
                    Method method = methods[i1];
                    if (("set" + name).equalsIgnoreCase(method.getName())) {
                        Object propertyObject = map.get(ref);
                        method.invoke(parentObject, propertyObject);
                    }
                }
                map.put(id, parentObject);
            }
        } catch (DocumentException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String id) {
        return map.get(id);
    }
}
