package com.tjj.chapter11;

/**
 * @description: 14.7(22) 使代理可以度量方法次数
 * @author: tangjunjian
 * @create: 2018-07-24 14:25
 **/

import java.lang.reflect.*;
import java.util.HashMap;

class DynamicProxyHandler implements InvocationHandler {
    private HashMap<Method, Integer> count = new HashMap<Method, Integer>();
    private Object proxied;
    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }
    public Object
    invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        if(count.get(method) != null) {
            count.put(method, count.get(method) + 1);
        } else {
            count.put(method, 1);
        }
        System.out.println("**** proxy: " + proxy.getClass() +
                ", method: " + method + ", args: " + args + ", count:" + count.get(method));
        if(args != null) {
            for (Object arg : args) {
                System.out.println("  " + arg);
            }
        }
        return method.invoke(proxied, args);
    }
}

class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.doSomething();
        iface.somethingElse("bonobo");
    }
    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        // Insert a proxy and call again:
        Interface proxy = (Interface)Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{ Interface.class },
                new DynamicProxyHandler(real));
        consumer(proxy);
    }
}
