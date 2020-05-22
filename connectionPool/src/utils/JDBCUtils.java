package utils;

/*
* 定义工具类
			1. 定义一个类 JDBCUtils
			2. 提供静态代码块加载配置文件，初始化连接池对象
			3. 提供方法
				1. 获取连接方法：通过数据库连接池获取连接
				2. 释放资源
				3. 获取连接池的方法
* */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    //定义一个成员变量
    private static DataSource ds;

    static {
        //加载配置文件
        Properties pro = new Properties();
        try {
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //获取datasource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 获取连接
    * */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /*
    * 获取连接池的方法
    * */
    public static DataSource getDataSource() {
        return ds;
    }

    /*
    * 释放资源
    * */
    public static void close(Statement stat, Connection conn){
        close(stat,conn,null);
    }

    public static void close(Statement stat, Connection conn, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
