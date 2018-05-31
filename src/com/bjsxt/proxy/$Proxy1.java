package com.bjsxt.proxy;
import java.lang.reflect.Method;
public class $Proxy1 implements com.bjsxt.proxy.Moveable {
     public $Proxy1(InvocationHandler h) {
         super();
         this.h = h;
     }
     com.bjsxt.proxy.InvocationHandler h;
@Override
public void move() {
    try {
    Method md = com.bjsxt.proxy.Moveable.class.getMethod("move");
    h.invoke(this,  md );
    }catch(Exception e) {e.printStackTrace();}
}
}