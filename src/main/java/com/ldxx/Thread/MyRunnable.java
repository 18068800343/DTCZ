package com.ldxx.Thread;

import com.ldxx.test.Main;
import org.apache.poi.ss.formula.functions.T    ;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class MyRunnable<T> implements Runnable{
    private T t;
    public MyRunnable(T t) {
        this.t = t;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Class cls = t.getClass();
        Method method = null;
        try {
        method = cls.getMethod("callable");
        //invoke 方法的第一个参数是被调用的对象,这里是静态方法故为null,第二个参数为给将被调用的方法传入的参数
        Object result = method.invoke(t);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
