package com.service.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.domain.PageBean;
import com.domain.User;
import com.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserByid(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] uids) {
        if (uids != null && uids.length>0){
            //遍历数组
            for (String uid : uids) {
                //调用dao的删除方法
                dao.delete(Integer.parseInt(uid));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //设置当前页数不能小于1
        if (currentPage < 1){
            currentPage = 1;
        }

        //创建一个pageBean对象
        PageBean<User> pageBean = new PageBean<>();

        //调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pageBean.setTotalCount(totalCount);

        //计算总页码
        int totalPage = totalCount % rows == 0 ? totalCount/rows : totalCount/rows + 1;
        pageBean.setTotalPage(totalPage);

        //设置当前页数不能大与总页数
        if (currentPage > totalPage){
            currentPage = totalPage;
        }

        //调用dao查询list集合
        int start = currentPage == 0 ? 0 :(currentPage - 1) * rows;
        pageBean.setList(dao.findByPage(start, rows, condition));

        //设置参数
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);

        return pageBean;
    }
}
