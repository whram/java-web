package com.test;

import com.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

/*
* BeanUtils工具类，简化数据封装
		* 用于封装JavaBean的
		1. JavaBean：标准的Java类
			1. 要求：
				1. 类必须被public修饰
				2. 必须提供空参的构造器
				3. 成员变量必须使用private修饰
				4. 提供公共setter和getter方法
			2. 功能：封装数据
		2. 概念：
			成员变量：
			属性：setter和getter方法截取后的产物, 使用BeanUtils方法时操作的是对象方法截取的属性，而非成员变量
				例如：getUsername() --> Username--> username
		3. 方法：
			1. setProperty()
			2. getProperty()
			3. populate(Object obj , Map map):将map集合的键值对信息，封装到对应的JavaBean对象中
* */

import java.lang.reflect.InvocationTargetException;

public class BeanUtilsTest {

    @Test
    public void test() {
        User user = new User();
        try {
            BeanUtils.setProperty(user, "username","Reacher");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(user);
    }

}
