
/*
*  练习：
		* 需求：
			1. 通过键盘录入用户名和密码
			2. 判断用户是否登录成功
				* select * from user where username = "" and password = "";
				* 如果这个sql有查询结果，则成功，反之，则失败
				*
* 存在SQL注入问题
* 解决sql注入问题：使用PreparedStatement对象来解决
* */

import util.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DemoLogin {

    public static void main(String[] args) {
        System.out.println("请输入用户名：");
        String username = new Scanner(System.in).nextLine();
        System.out.println("请输入密码：");
        String password = new Scanner(System.in).nextLine();
        boolean flag = new DemoLogin().login(username,password);
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
        Statement state = null;
        ResultSet rs = null;
        try {
            //获取连接对象
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = String.format("select * from user where username ='%s' and password ='%s'", username, password);
            //获取sql执行对象
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(state,conn,rs);
        }
        return false;
    }
}
