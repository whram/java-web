package com.web.filter;

/*
* 快速入门：
		1. 步骤：
			1. 定义一个类，实现接口Filter
			2. 复写方法
			3. 配置拦截路径
				1. web.xml
				2. 注解
				* 拦截路径配置：
				    1. 具体资源路径： /index.jsp   只有访问index.jsp资源时，过滤器才会被执行
				    2. 拦截目录： /user/*	访问/user下的所有资源时，过滤器都会被执行
                    3. 后缀名拦截： *.jsp		访问所有后缀名为jsp资源时，过滤器都会被执行
				    4. 拦截所有资源：/*		访问所有资源时，过滤器都会被执行
				拦截方式配置：资源被访问的方式
				* 注解配置：
					* 设置dispatcherTypes属性
						1. REQUEST：默认值。浏览器直接请求资源
						2. FORWARD：转发访问资源 只有转发访问时才会执行过滤器
						3. INCLUDE：包含访问资源
						4. ERROR：错误跳转资源
						5. ASYNC：异步访问资源
				* web.xml配置
					* 设置<dispatcher></dispatcher>标签即可
* */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class Demo02 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter02...");

        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
