package com.example.myjsoup;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2024/01/05/下午 02:37:01
 * @Description:
 */
/***
 * 继承Thread与实现Runnable区别
 * 前者直接可以创建对象并调用Thread里面的start方法，后者创建自身对象后还需要创建Thread对象
 * 前者获取线程名字直接可以getName，后者必须通过Thread.currentThread调用getName
 */

import java.util.Random;

/**
 * 给定若干人数，开始抢占任务，抢到任务后随机休息5秒内结束，
 */
public class MyThreadTest {
    static int num;
    public static void main(String[] args){

        MyThread mt = new MyThread();


        Thread t1 = new Thread(mt,"线程1");
        Thread t2 = new Thread(mt,"线程2");
        Thread t3 = new Thread(mt,"线程3");
        t1.start();
        t2.start();
        t3.start();
//        threadSizePage(3,t1);
    }

    /*//给定人数，开始抢占任务
    private static void threadSizePage(Integer iter, MyThread mt, int random) {

        long begin = System.currentTimeMillis();

        try {
            int random = new Random().nextInt(5);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/



}
