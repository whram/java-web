package demoDDLandDML;

/*
* 执行DML语句
* */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoDML {
    public static void main(String[] args) {
        Statement statement = null;
        Connection conn = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.定义sql
            String sql = "create table student (id int, name varchar(20))";
            //获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql://192.168.2.110:3306/db1", "root", "68845292");
            //获取sql执行对象Statement
            statement = conn.createStatement();
            //执行sql
            int count = statement.executeUpdate(sql);
            //处理结果
            System.out.println(count);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //避免空指针异常
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
