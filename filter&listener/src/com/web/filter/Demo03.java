package com.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class Demo03 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //对request对象请求消息增强
        System.out.println("filterDemo03执行");
        //放行
        chain.doFilter(req, resp);
        //对responds对象
        System.out.println("filterDemo3放行");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
