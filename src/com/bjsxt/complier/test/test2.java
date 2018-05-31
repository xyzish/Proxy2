package com.bjsxt.complier.test;

import java.lang.reflect.Method;

public class test2 {

    public static void main(String[] args) {
        Method[] methods = com.bjsxt.proxy.Moveable.class.getMethods();
        for(Method m : methods) {
            System.out.println(m.getName());
        }

    }

}
