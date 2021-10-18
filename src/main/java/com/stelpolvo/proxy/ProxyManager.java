package com.stelpolvo.proxy;

import com.stelpolvo.factory.BeanFactory;
import com.stelpolvo.service.UserService;

import java.lang.reflect.Proxy;

public class ProxyManager {
    public static UserService getProxy() {
        // 暂时使用手动注入
        UserService userService = (UserService) BeanFactory.getBean("userService");
        return (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(),
                (proxy1, method, args) -> {
                    Object invoke = null;
                    long l = System.currentTimeMillis();
                    for (int i = 1; i < 101; i++) {
                        System.out.print(i);
                        if (i % 10 == 0) System.out.println();
                    }
                    System.out.println();
                    try {
                        invoke = method.invoke(userService, args);
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

