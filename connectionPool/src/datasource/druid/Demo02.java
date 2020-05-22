package datasource.druid;

/*
* 使用Druid连接池的工具类
* */

import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo02 {
    public static void main(String[] args) {
        //给account表添加一条记录
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            //获取连接
            conn = JDBCUtils.getConnection();
            //定义sql语句
            String sql = "INSERT INTO account VALUES(null, ?, ?);";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1,"Jack");
            pstat.setDouble(2,3000);
            int count = pstat.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstat, conn);
        }
    }
}
