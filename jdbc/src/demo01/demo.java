package demo01;

/*
* JDBC快速入门
* 步骤：
			1. 导入驱动jar包 mysql-connector-java-5.1.37-bin.jar
				1.复制mysql-connector-java-5.1.37-bin.jar到项目的libs目录下
				2.右键-->Add As Library
			2. 注册驱动
			3. 获取数据库连接对象 Connection
			4. 定义sql
			5. 获取执行sql语句的对象 Statement
			6. 执行sql，接受返回结果
			7. 处理结果
			8. 释放资源
* */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class demo {

    public static void main(String[] args) throws Exception {
        //注册驱动:告诉程序该使用哪一个数据库驱动jar  jdbc5之后可以省略
        //Class.forName("com.mysql.jdbc.Driver");
        //获取数据库连接对象 Connection
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.2.110:3306/db1", "root", "68845292");
        //定义sql
        String sql = "update account set balance = 500 where id = 1";
        //获取执行sql语句的对象 Statement
        Statement statement = conn.createStatement();
        /*执行sql，接受返回结果
        *       1. boolean execute(String sql) ：可以执行任意的sql 了解
				2. int executeUpdate(String sql) ：执行DML（insert、update、delete）语句、DDL(create，alter、drop)语句
					* 返回值：影响的行数，可以通过这个影响的行数判断DML语句是否执行成功 返回值>0的则执行成功，反之，则失败。
				3. ResultSet executeQuery(String sql)  ：执行DQL（select)语句
		*/
        int count = statement.executeUpdate(sql);
        //处理结果
        System.out.println(count);
        //释放资源
        statement.close();
        conn.close();
    }

}
