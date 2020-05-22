package com.test;

import com.dao.UserDao;
import com.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void test(){
        User loginUser = new User("Adam","123");
        User user = new UserDao().login(loginUser);
        System.out.println(user);
    }
}
