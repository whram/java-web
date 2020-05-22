package com.servlet;

/*
* servlet的方法
* */

import javax.servlet.*;
import java.io.IOException;

public class Demo02 implements Servlet {

    /*
    * 初始化方法
    *   在servlet被创建的时候执行且执行一次
    * Servlet的init方法，只执行一次，说明一个Servlet在内存中只存在一个对象，Servlet是单例的
     * 多个用户同时访问时，可能存在线程安全问题。
     * 解决：尽量不要在Servlet中定义成员变量，要使用局部变量。即使定义了成员变量，也不要对修改值
    * */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init....");
    }

    //获取ServletConfig对象  servlet配置对象
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /*
    * 提供服务的方法
    *   每次servlet被访问，执行。执行多次
    * */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service....");
    }

    //获取Servlet的一些信息：版本、作者
    @Override
    public String getServletInfo() {
        return null;
    }

    /*
    * 被销毁：执行destroy方法，只执行一次
     * Servlet被销毁时执行。服务器关闭时，Servlet被销毁
     * 只有服务器正常关闭时，才会执行destroy方法。
     * destroy方法在Servlet被销毁之前执行，一般用于释放资源
    * */
    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}
