package datasource.c3p0;

/*
* C3P0演示
* */

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo02 {

    public static void main(String[] args) throws SQLException {
        //获取DataSource 不传参使用默认配置，参数为配置的名称
        DataSource ds = new ComboPooledDataSource("otherc3p0");
        //获取连接 配置文件中配置最大连接数为10个
        for (int i = 0; i < 10; i++) {
            Connection conn = ds.getConnection();
            System.out.println(i+":"+conn);
            if(i == 5){
                //归还连接到连接池中
                conn.close();
            }
        }
    }

}
