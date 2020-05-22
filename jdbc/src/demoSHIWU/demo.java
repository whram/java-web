package demoSHIWU;

/*
* 事务操作
* 使用Connection对象来管理事务
 * 开启事务：setAutoCommit(boolean autoCommit) ：调用该方法设置参数为false，即开启事务
        在执行sql之前开启事务
 * 提交事务：commit()
        当所有sql都执行完提交事务
 * 回滚事务：rollback()
        在catch中回滚事务
* */

import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class demo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstat1 = null;
        PreparedStatement pstat2 = null;
        try {
            conn = JDBCUtils.getConnection();

            //开启事务
            conn.setAutoCommit(false);

            //定义sql
            //1.Adam -100
            String sql1 = "update account set balance = balance - ? where id = ?";
            //2.Reacher +100
            String sql2 = "update account set balance = balance + ? where id = ?";

            pstat1 = conn.prepareStatement(sql1);
            pstat2 = conn.prepareStatement(sql2);

            pstat1.setDouble(1, 100);
            pstat1.setInt(2, 1);

            pstat2.setDouble(1, 100);
            pstat2.setInt(2, 2);

            pstat1.executeUpdate();
            //手动制造异常
            int n = 1/0;
            pstat2.executeUpdate();

            //提交事务
            conn.commit();
        } catch (Exception e) {
            //回滚事务
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstat1,conn,null);
            JDBCUtils.close(pstat2,null,null);
        }
    }

}
