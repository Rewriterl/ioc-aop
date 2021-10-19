package com.stelpolvo.factory.proxy;

import com.stelpolvo.factory.BeanFactory;
import com.stelpolvo.service.UserService;

import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static Object getProxy(Object obj) {
        // 暂时使用手动注入
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
                (proxy1, method, args) -> {
                    Object invoke = null;
                    long l = System.currentTimeMillis();
                    try {
                        invoke = method.invoke(obj, args);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    long l1 = System.currentTimeMillis();
                    double l2 = (double) (l1 - l) / 1000L;
                    System.out.println("程序运行时间:" + l2 + "s");
                    return invoke;
                });
    }
}

