package com.example.myjsoup;

import jdk.nashorn.internal.ir.Block;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2024/01/05/下午 02:35:03
 * @Description:
 */
public class MyThread implements Runnable{
    static Integer piao = 0;

    static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                if (method()) break;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
    private  boolean method(){
        if(piao == 100){
            return true;
        }else {
            piao++;
            System.out.println(Thread.currentThread().getName()+"在卖第"+piao+"票");
        }
        return false;
    }
}
