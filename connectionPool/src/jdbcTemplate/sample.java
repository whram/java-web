package jdbcTemplate;

/*
* 练习：
			* 需求：
				1. 修改1号数据的 salary 为 10000
				2. 添加一条记录
				3. 删除刚才添加的记录
				4. 查询id为1的记录，将其封装为Map集合
				5. 查询所有记录，将其封装为List
				6. 查询所有记录，将其封装为Emp对象的List集合
				7. 查询总记录数
* */

import jdbcTemplate.domain.Emp;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class sample {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    //Junit单元测试，可以让方法单独执行

    //1. 修改1号数据的 salary 为 10000
    @Test
    public void test01() {
        String sql = "update emp set salary = 10000 where id = ?;";
        int count = template.update(sql, 1001);
        System.out.println(count);
    }

    //2. 添加一条记录
    @Test
    public void test02() {
        String sql = "INSERT INTO emp(id, ename, dept_id) VALUES(?,?,?)";
        int count = template.update(sql, 1015, "一二三", 10);
        System.out.println(count);
    }

    //3. 删除刚才添加的记录
    @Test
    public void test03() {
        String sql = "DELETE FROM emp WHERE id = ?";
        int count = template.update(sql, 1015);
        System.out.println(count);
    }

    //4. 查询id为1001的记录，将其封装为Map集合
    @Test
    public void test04() {
        String sql = "SELECT * from emp where id =?";
        Map<String, Object> map = template.queryForMap(sql, 1001);
        System.out.println(map);
    }

    //5. 查询所有记录，将其封装为List
    @Test
    public void test05() {
        String sql = "select * from emp";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }
    
	//6. 查询所有记录，将其封装为Emp对象的List集合
    @Test
    public void test06() {
        String sql = "select * from emp";
        /*List<Emp> list = template.query(sql, new RowMapper<Emp>() {
            @Override
            public Emp mapRow(ResultSet resultSet, int i) throws SQLException {
                Emp emp = new Emp();
                emp.setId(resultSet.getInt("id"));
                emp.seteName(resultSet.getString("ename"));
                emp.setJob_id(resultSet.getInt("job_id"));
                emp.setMgr(resultSet.getInt("mgr"));
                emp.setJoinDate(resultSet.getDate("joindate"));
                emp.setSalary(resultSet.getDouble("salary"));
                emp.setBonus(resultSet.getDouble("bonus"));
                emp.setDept_id(resultSet.getInt("dept_id"));
                return emp;
            }
        });*/

        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));

        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

	//7. 查询总记录数
    @Test
    public void test07() {
        String sql = "select count(id) from emp";
        Long total = template.queryForObject(sql, Long.class);
        System.out.println(total);
    }
}
