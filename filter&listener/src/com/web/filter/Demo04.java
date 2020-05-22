package com.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class Demo04 implements Filter {
    /*
    * 在服务器关闭后，Filter对象被销毁，如果服务器正常关闭会执行，只执行一次，用于释放资源
    * */
    public void destroy() {
        System.out.println("destroy...");
    }

    /*
    * 每一次请求被拦截的时候会执行
    * */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("doFilter...");
        chain.doFilter(req, resp);
    }

    /*
    * 在服务器启动后会创建Filter对象，然后调用init方法，只执行一次，用于加载资源
    * */
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init...");
    }

}
