package com.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class Demo06 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("demo06...request");
        chain.doFilter(req, resp);
        System.out.println("demo06...response");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
