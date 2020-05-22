package DemoResultSet;

/*
* ResultSet：结果集对象,封装查询结果 游标开始时在第一行数据的上一行，因此需要先使用next
*       * boolean next(): 游标向下移动一行，判断当前行是否是最后一行末尾(是否有数据)，如果是，则返回false，如果不是则返回true
		* getXxx(参数):获取数据
				* Xxx：代表数据类型   如： int getInt() ,	String getString()
				* 参数：
					1. int：代表列的编号,从1开始   如： getString(1)
					2. String：代表列名称。 如： getDouble("balance")
* 注意：
				* 使用步骤：
					1. 游标向下移动一行
					2. 判断是否有数据
					3. 获取数据

				   //循环判断游标是否是最后一行末尾。
		            while (resultSet.next()){
                            //获取数据
                            int id = resultSet.getInt(1);
                            String name = resultSet.getString("NAME");
                            double balance = resultSet.getDouble(3);
                            System.out.println(id + " " + name + " " + balance);
                    }

* */

import java.sql.*;

public class Demo01 {

    public static void main(String[] args) {
        Statement statement = null;
        Connection conn = null;
        ResultSet resultSet = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.定义sql
            String sql = "select * from account";
            //获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql://192.168.2.110:3306/db1", "root", "68845292");
            //获取sql执行对象Statement
            statement = conn.createStatement();
            //执行sql
            resultSet = statement.executeQuery(sql);
            //处理结果
            //循环判断游标是否是最后一行末尾。
            while (resultSet.next()){
                //获取数据
                int id = resultSet.getInt(1);
                String name = resultSet.getString("NAME");
                double balance = resultSet.getDouble(3);
                System.out.println(id + " " + name + " " + balance);
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

    }

}
