package demoPreparedStatement;

/*
* PreparedStatement：执行sql的对象(预编译执行sql）
			1. SQL注入问题：在拼接sql时，有一些sql的特殊关键字参与字符串的拼接。会造成安全性问题
			    1. 输入用户随便，输入密码：a' or 'a' = 'a
				2. sql：select * from user where username = 'fhdsjkf' and password = 'a' or 'a' = 'a'
			2. 解决sql注入问题：使用PreparedStatement对象来解决
			3. 预编译的SQL：参数使用?作为占位符
			     例：select * from user where username = ? and password = ?;
			        给？赋值：
					    方法： setXxx(参数1,参数2)
						    参数1：？的位置编号 从1 开始
                            参数2：？的值

* 注意：后期都会使用PreparedStatement来完成增删改查的所有操作
				1. 可以防止SQL注入
				2. 效率更高
* */

import util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        System.out.println("请输入用户名：");
        String username = new Scanner(System.in).nextLine();
        System.out.println("请输入密码：");
        String password = new Scanner(System.in).nextLine();
        boolean flag = new Demo().login(username,password);
        if(flag){
            System.out.println("登录成功！");
        }else {
            System.out.println("用户名或密码错误！");
        }
    }

    /*
     * 登录方法
     * */
    public boolean login(String username, String password) {
        if(username == null || password == null){
            return false;
        }

        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            //获取连接对象
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "select * from user where username =? and password =?";
            //获取sql执行对象
            pstat = conn.prepareStatement(sql);
            pstat.setString(1,username);
            pstat.setString(2,password);
            //执行查询不需要传参
            rs = pstat.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstat,conn,rs);
        }
        return false;
    }

}
