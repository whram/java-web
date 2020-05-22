package com.dao.impl;

import com.dao.UserDao;
import com.domain.User;
import com.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库...
        //1.定义sql
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public void add(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public void delete(int id) {
        String sql = "delete from user where id = ?";
        template.update(sql,id);
    }

    @Override
    public User findById(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void update(User user) {
        String sql = "update user set name=?, gender=?, age=?, address=?, qq=?, email=? where id=?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //定义模板初始化sql
        String sql = "select count(*) from user where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> keySet = condition.keySet();
        //定义一个参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            if(!"name".equals(key) && !"address".equals(key) && !"email".equals(key)){
                continue;
            }
            //获取Value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value != null || !"".equals(value)) {
                //value有值
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");//条件值的数组
            }
        }

        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> keySet = condition.keySet();
        //定义一个参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            if(!"name".equals(key) && !"address".equals(key) && !"email".equals(key)){
                continue;
            }
            //获取Value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value != null || !"".equals(value)) {
                //value有值
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");//条件值的数组
            }
        }

        //添加分页查询条件
        sb.append(" limit ?, ?");
        params.add(start);
        params.add(rows);

        return template.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }
}
