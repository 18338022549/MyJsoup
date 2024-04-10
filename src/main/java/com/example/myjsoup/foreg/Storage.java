package com.example.myjsoup.foreg;

import java.io.File;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2023/11/16/下午 03:37:13
 * @Description:
 */
public class Storage {
    // 准备一个集合用来放下载好的文件对象
    private LinkedList<File> files = new LinkedList<>();
    // 给集合中放文件
    public synchronized void push(File file){
        files.push(file);
        // 每次放一个文件进来之后，就立刻唤醒解析线程开始解析
        this.notifyAll();
    }

    // 从集合中获取文件
    public synchronized File pop(){
        if(files.size()==0){
            // 集合中没有文件，就开始等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 开始消费文件。
        File file = files.pop();
        return file;
    }
}
