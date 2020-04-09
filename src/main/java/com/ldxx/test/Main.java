package com.ldxx.test;

public class Main {
    public static String static_ma = "当前类静态全局变量";
    public String normal_ma = "当前类全局变量";
    public Main() {
        System.out.println("当前主类构造方法");
    }
    {
        System.out.println("当前类代码块");
    }
    static {
        System.out.println("当前类静态代码块");
    }
    public static void main(String[] args) {
//       Main main = new Main();
       Son father = new Son();
    }
}
class Son extends Father{
    public static String static_sa = "子类静态全局变量";
    public String getStatic_sab = "子类全局变量";
    {
        System.out.println("子类代码块");
    }
    static {
        System.out.println("子类静态代码块");
    }
    public Son() {
        System.out.println("子类构造方法");
    }
    public static void SonStatic() {
        System.out.println("子类静态方法");
    }
}

class Father{
    public static String static_fa = "父类静态全局变量";
    public String fa = "父类全局变量";
    {
        System.out.println("父类代码块");
    }
   static {
        System.out.println("父类静态代码块");
    }
    public Father() {
        System.out.println("父类构造方法");
    }
    public static void FatherStatic() {
        System.out.println("父类静态方法");
    }
}