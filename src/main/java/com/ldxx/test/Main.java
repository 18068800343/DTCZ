package com.ldxx.test;

import com.ldxx.Thread.MyRunnable;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        System.out.println("**************主线程执行开始***************");

        Main main = new Main();
        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.execute(new MyRunnable(main));
        System.out.println("*************主线程执行完毕****************");
    }

    public  void callable(){
        System.out.println("异步线程执行完成了");
    }
}