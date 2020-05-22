package DemoResultSet;

/*
* 练习：
				* 定义一个方法，查询emp表的数据将其封装为对象，然后装载集合，返回。
					1. 定义Emp类
					2. 定义方法 public List<Emp> findAll(){}
					3. 实现方法 select * from emp;
* */

import util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemoSample {

    public static void main(String[] args) {
        //调用非静态方法需要创建对象
        List<Emp> list = new DemoSample().findAll2();
        System.out.println(list);
    }
    /*
    * 查询所有emp对象
    * */
    public List<Emp> findAll() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Emp> list = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://192.168.2.110:3306/db1", "root", "68845292");
            //定义sql
            String sql = "select * from emp";
            //获取执行对象
            statement = conn.createStatement();
            //执行sql
            resultSet = statement.executeQuery(sql);
            //遍历结果集
            Emp emp = null;
            list = new ArrayList<>();
            while (resultSet.next()){

                //创建对象
                emp= new Emp();
                emp.setId(resultSet.getInt("id"));
                emp.seteName(resultSet.getString("ename"));
                emp.setJob_id(resultSet.getInt("job_id"));
                emp.setMgr(resultSet.getInt("mgr"));
                emp.setJoinDate(resultSet.getDate("joindate"));
                emp.setSalary(resultSet.getDouble("salary"));
                emp.setBounds(resultSet.getDouble("bonus"));
                emp.setDept_id(resultSet.getInt("dept_id"));

                list.add(emp);
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
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public List<Emp> findAll2() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Emp> list = null;
        try {
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "select * from emp";
            //获取执行对象
            statement = conn.createStatement();
            //执行sql
            resultSet = statement.executeQuery(sql);
            //遍历结果集
            Emp emp = null;
            list = new ArrayList<>();
            while (resultSet.next()){

                //创建对象
                emp= new Emp();
                emp.setId(resultSet.getInt("id"));
                emp.seteName(resultSet.getString("ename"));
                emp.setJob_id(resultSet.getInt("job_id"));
                emp.setMgr(resultSet.getInt("mgr"));
                emp.setJoinDate(resultSet.getDate("joindate"));
                emp.setSalary(resultSet.getDouble("salary"));
                emp.setBounds(resultSet.getDouble("bonus"));
                emp.setDept_id(resultSet.getInt("dept_id"));

                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(statement,conn,resultSet);
        }
        return list;
    }
}
