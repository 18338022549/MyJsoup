package com.example.myjsoup.until;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2023/11/16/下午 03:52:43
 * @Description:
 */
public class BookDAO {
    //不确定条件的查询
    public ArrayList<Books> queryByTerm(Books books){
        ArrayList<Books> bk = new ArrayList<>();
        String sql = "select * from tbooks where 1 = 1 ";
        // 判断是否要添加条件
        if(books!=null){
            if(books.getBookname()!=null && !"".equals(books.getBookname())){
                sql += " and bookname like '%"+books.getBookname()+"%' ";
            }
            if(books.getBookauthor()!=null && !"".equals(books.getBookauthor())){
                sql += " and bookauthor like '%"+books.getBookauthor()+"%' ";
            }
            if(books.getBookdate()!=null && !"".equals(books.getBookdate())){
                sql += " and bookdate like '%"+books.getBookdate()+"%' ";
            }
            if(books.getBookpress()!=null && !"".equals(books.getBookpress()) && !"全部".equals(books.getBookpress())){
                sql += " and bookpress = '"+books.getBookpress()+"' ";
            }
            if(books.getBookprice()!=null && !"".equals(books.getBookprice()) && !"全部".equals(books.getBookprice())){
                sql += " and bookprice = '"+books.getBookprice()+"' ";
            }
        }
        // 准备JDBC工具类
        JDBCHelper helper = new JDBCHelper();
        // 准备JDBC需要的接口对象
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con =  helper.getCon();
            pst =  con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                // 每循环一次就是一条记录，我们将一条记录的数据封装在一个Books对象中
                Books B = new Books();
                B.setId(rs.getLong("id"));
                B.setBookname(rs.getString("bookname"));
                B.setBookauthor(rs.getString("bookauthor"));
                B.setBookdate(rs.getString("bookdate"));
                B.setBookpress(rs.getString("bookpress"));
                B.setBookprice(rs.getString("bookprice"));
                B.setBookinfo(rs.getString("bookinfo"));
                B.setStatus(rs.getInt("status"));
                B.setModifyTime(rs.getString("modifyTime"));
                B.setCreateTime(rs.getString("createTime"));
                // 将封装好的Books对象放入bk集合
                bk.add(B);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            helper.closeAll(rs,pst,con);
        }
        return bk;
    }



    // 根据年份查询出版日期分组后的数据
    public static ArrayList<String> queryComBoxByDate(String pulishTiem){
        ArrayList<String> result = new ArrayList<>();
        // 准备JDBC工具类
        JDBCHelper helper = new JDBCHelper();
        // 准备SQL
        String sql = "select " + pulishTiem + " from tbooks group by " + pulishTiem;
        // 准备JDBC需要的接口对象
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            con =  helper.getCon();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                result.add(rs.getString(pulishTiem));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            helper.closeAll(rs,pst,con);
        }
        return result;
    }

    public int save(Books book){
        // 准备 sql语句
        String sql = "insert into tbooks(bookname,bookauthor,bookdate,bookpress,bookprice,bookinfo,createTime,modifyTime,status) " +
                " values(?,?,?,?,?,?,?,?,?)";
        // 创建工具类对象
        JDBCHelper helper = new JDBCHelper();
        // 调用helper的方法执行sql 并且传入9个参数
        int result = helper.executeUpdate(sql,book.getBookname(),book.getBookauthor(),book.getBookdate(),book.getBookpress(),book.getBookprice(),book.getBookinfo(),book.getCreateTime(),book.getModifyTime(),book.getStatus());
        // 返回执行结果   返回添加之后数据库受影响的行数
        return result;
    }
}
