package com.service;

import com.domain.PageBean;
import com.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    public User login(User user);

    //保存对象
    void addUser(User user);

    void deleteUser(String id);

    /*
    * 根据id查询user
    * */
    User findUserByid(String id);

    void updateUser(User user);

    void delSelectedUser(String[] uids);

    /*
    * 分页条件查询
    * */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
