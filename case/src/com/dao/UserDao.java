package com.dao;

import com.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public interface UserDao {

    public User findUserByUsernameAndPassword(String username, String password);


    public List<User> findAll();

    void add(User user);

    void delete(int id);

    User findById(int parseInt);

    void update(User user);

    int findTotalCount(Map<String, String[]> condition);

    /*
    * 分页查询没页的记录
    * */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
