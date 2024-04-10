package com.example.myjsoup.foreg;

import com.example.myjsoup.until.BookDAO;
import com.example.myjsoup.until.Books;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2023/11/16/下午 03:45:34
 * @Description:
 */
public class ParseBook {
    public static void main(String[] args) throws IOException {
        // 准备一个集合，用来存储解析的数据对象
        ArrayList<Books> arraybk = new ArrayList<Books>();
        int x = 0;
        for (int i = 1 ; i<=26 ; i++) {
            String fileName = "E:\\Fang\\Book"+i+".html";
            // 有了文件名，可以创建一个文件对象
            File file = new File(fileName);
            // 通过Jsoup解析这个文件
            Document doc = Jsoup.parse(file, "utf-8");



            // 通过class获取所有的职位DIV
            Elements bookElements = doc.getElementsByClass("infor");
            // 由于Elements是继承自ArrayList的，所以我们可以按照ArrayList的方式遍历这个对象
            for(int b = 0; b < bookElements.size(); b ++) {
                String bookname = null;
                String bookauthor = null;
                String bookdate = null;
                String bookpress = null;
                String bookprice = null;
                String bookinfo = null ;
                try {
                    //每一个b对象就代表页面的一个图书信息的标签
                    Element element = bookElements.get(b);

                        //通过class获取储存图书信息的对象，并取出其中的文本信息
                        bookname = element.getElementsByClass("name").first().text();
                        //取出作者信息
                        bookauthor = element.getElementsByClass("author").first().text();
                        //出版时间
                        bookdate = element.getElementsByClass("pulishTiem").first().wholeText();
                        //取出图书出版社
                        bookpress = element.getElementsByClass("publisher").first().text();
                        //取出图书价格
                        bookprice = element.getElementsByClass("sellPrice").first().text();
                        //取出图书简介信息
                        bookinfo = element.getElementsByClass("recoLagu").first().text();
                        //输出到控制台
                        System.out.println("---" + (++x) + "---");
                        System.out.println("图书名：" + bookname);
                        System.out.println("图书作者：" + bookauthor);
                        System.out.println("图书出版时间：" + bookdate);
                        System.out.println("图书出版社：" + bookpress);
                        System.out.println("图书价格：" + bookprice);
                        System.out.println("图书简介信息：" + bookinfo);

                }catch (Exception e){
                    e.printStackTrace();
                }


                //创建一个Books对象
                Books books = new Books();
                // 将上面的所有数据都设置到books对象中

                books.setBookname(bookname);

                books.setBookauthor(bookauthor);

                books.setBookdate(bookdate);
                books.setBookpress(bookpress);
                books.setBookprice(bookprice);
                books.setBookinfo(bookinfo);
                // 每次解析一个books出来就将这个对象加入arraybk集合
                arraybk.add(books);
            }
            System.out.println(fileName);
        }
        System.out.println(arraybk);
        // 在解析完成之后，写一段程序将解析的数据添加到数据库
        int count = 0;
        // 准备BookDAO对象
        BookDAO dao = new BookDAO();
        for (Books b : arraybk){
            // 准备此时此刻的系统时间
            Date date = new Date();
            String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            // 设置创建时间
            b.setCreateTime(dateStr);
            // 设置最后修改时间
            b.setModifyTime(dateStr);
            // 设置默认的status
            b.setStatus(1);
            // 调用DAO的save方法保存数据
            int result = dao.save(b);
            count += result;
        }

        System.out.println("总共保存了"+count+"职位信息到数据库");
    }

}
