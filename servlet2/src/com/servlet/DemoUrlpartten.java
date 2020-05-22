package com.servlet;

/*
* urlpartten:Servlet访问路径
			1. 一个Servlet可以定义多个访问路径 ： @WebServlet({"/02","/user/*","*.do"})
			2. 路径定义规则：
				1. /xxx：路径匹配
				2. /xxx/xxx:多层路径，目录结构
				3. *.do：扩展名匹配
* */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/02","/user/*","*.do"})
public class DemoUrlpartten extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet...");
        System.out.println(req);
    }
}
