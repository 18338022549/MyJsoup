package com.example.myjsoup.foreg;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2023/11/16/下午 03:26:05
 * @Description:
 */

/**
 * 
 */
public class DownloadBook {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 26; i++) {
            String href = "http://www.bookschina.com/book_find2/default.aspx?stp=java&scate=0&f=1&sort=0&asc=0&sh=0&so=1&p=" + i + "&pb=1";
            //      创建字符输出流
            BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\Fang\\Book" + i + ".html"));
            //        Java 提供了一个专门用来访问资源的一个类
            URL url = new URL(href);
            //        通过这个URL打开一个链接， 这个 openconnection 方法本身返回的就是HttpURLConnection对象
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //        设置请求消息
            conn.addRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36");
            conn.addRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            conn.addRequestProperty("cookie", "indexCache=yes; ASP.NET_SessionId=q50njskli4rjdepubllewg5b; Hm_lvt_6993f0ad5f90f4e1a0e6b2d471ca113a=1652964005; BookUser=1%7c5b9705c4-1688-472b-b83b-d33d7114fc23%7c1%7c0%7c637911816134514701%7c20180722%7cec130b2b6afcf113; Hm_lpvt_6993f0ad5f90f4e1a0e6b2d471ca113a=1652964295");
            //        发送请求
            conn.connect();//通过客户端的输出流，向服务器发送请求
            // 从响应中取出数据 从Conn中取出输入流 字节流
            InputStream in = conn.getInputStream();
            //        从输入流中读取服务器响应的数据
            //        为了方便操作，我们将字节流转换为字符流
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "GB2312"));
            //按行读取
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            bw.close();
            in.close();

            //等待1-2秒去下载
            try {
                //休眠时间为1秒＋随机数0-1秒   共1~2秒
                Thread.sleep(1000 + (new Random().nextInt(900) + 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
