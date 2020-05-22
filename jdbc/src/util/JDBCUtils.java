package util;

/*
* JDBC工具类
* 简化书写
* */

import javax.xml.xpath.XPath;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    /*
    * 配置文件的读取，只需要读取一次，因此使用静态代码块
    * */
    static {
        //读取资源文件，获取值
        try {
            Properties pro = new Properties();

            //获取jdbc.properties的绝对路径
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            String path = classLoader.getResource("jdbc.properties").getPath();

            pro.load(new FileReader(path));
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 获得数据库连接对象
    * */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    /*
    * 释放资源
    * */
    public static void close(Statement state, Connection conn, ResultSet rs){
        if(state != null) {
            try {
                state.close();
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
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
