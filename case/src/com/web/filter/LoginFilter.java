package com.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
* 登录验证过滤器
* */

@WebFilter("/*")
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强制转换
        HttpServletRequest request = (HttpServletRequest)req;
        //获取资源请求的路径
        String uri = request.getRequestURI();
        //判断是否包含登录相关资源路径，还要注意排除css/js/图片/验证码等资源
        if(uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts") || uri.contains("/checkCodeServlet")){
            //包含，用户即使想登录，放行
            chain.doFilter(req, resp);
        } else {
            //不包含，需要验证用户是否登录
            //从session中获取登录信息
            Object user = request.getSession().getAttribute("user");
            if(user != null){
                //已登录，放行
                chain.doFilter(req, resp);
            }else {
                //没有登录，跳转页面
                request.setAttribute("login_msg","您尚未登录，请登录");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

}
