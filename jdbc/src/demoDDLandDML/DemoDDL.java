package demoDDLandDML;

/*
* 1. account表 添加一条记录
* 2. account表 修改记录
* 3. account表 删除一条记录
* */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoDDL {

    public static void main(String[] args) {
        Statement statement = null;
        Connection conn = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.定义sql
            String sql = "insert account values(null, 'Reacher', 2000)";
            //获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql://192.168.2.110:3306/db1", "root", "68845292");
            //获取sql执行对象Statement
            statement = conn.createStatement();
            //执行sql
            int count = statement.executeUpdate(sql);
            //处理结果
            System.out.println(count);
            //判断结果
            if (count > 0) {
                System.out.println("添加成功！");
            }else {
                System.out.println("添加失败！");
            }
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
