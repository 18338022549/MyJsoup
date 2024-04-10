package com.example.myjsoup.until;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2023/11/16/下午 03:56:18
 * @Description:
 */
public class JDBCHelper {
    // 链接数据库的用户名和密码
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    // 驱动类名，不同的数据库有不同的驱动，不同的驱动，有不同的驱动类名 (MySQL8.0有些区别)
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/books";
    // 执行所有的增删改操作
    public int executeUpdate(String sql,Object ... params){
        int result = 0;
        if(sql == null || "".equals(sql)){
            return result;
        }
        Connection con = null;
        PreparedStatement pst = null;
        try{
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            pst = con.prepareStatement(sql);
            // 设置参数
            if(params!=null && params.length > 0 ) {
                for (int i = 1; i <= params.length; i++) {
                    Object obj = params[i - 1];
                    if (obj != null) {
                        pst.setString(i, obj.toString());
                    } else {
                        pst.setString(i, "");
                    }
                }
            }
            result = pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(pst!=null)
                    pst.close();
                if(con!=null)
                    con.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    // 查询总条数
    public int queryTotal(String sql ,Object ... params){
        int total = 0;
        if(sql == null || "".equals(sql)){
            return total;
        }
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con  = getCon();// 获取链接
            pst =con.prepareStatement(sql);
            // 设置参数
            if(params!=null && params.length > 0 ){
                for(int i = 1;i <= params.length; i++){
                    Object obj = params[i-1];
                    if(obj!=null){
                        pst.setString(i,obj.toString());
                    }else{
                        pst.setString(i,"");
                    }
                }
            }
            // 执行sql语句
            rs = pst.executeQuery();
            // 由于是查询总条数，所以只有一条
            if(rs.next()){
                total = rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        return total;
    };

    // 关闭所有
    public void closeAll(ResultSet rs, Statement st, Connection con ){
        try{
            if(rs!=null)
                rs.close();
            if(st!=null)
                st.close();
            if(con!=null)
                con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 获取一个可用的链接
    public Connection getCon(){
        Connection con = null;
        try {
            Class.forName(DRIVER_CLASS);
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  con;
    }

}
