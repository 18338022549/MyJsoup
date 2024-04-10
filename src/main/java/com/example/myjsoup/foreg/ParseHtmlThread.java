package com.example.myjsoup.foreg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2023/11/16/下午 03:57:45
 * @Description:
 */
public class ParseHtmlThread extends Thread {
    private Storage storage;

    public ParseHtmlThread(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while(true){
            File file = storage.pop();
            if(file==null){
                break;
            }
            try {
                Document doc = Jsoup.parse(file, "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
