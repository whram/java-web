package com.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 获取文件的真实(服务器)路径
			1. 方法：String getRealPath(String path)
				 String b = context.getRealPath("/b.txt");//web目录下资源访问
		         System.out.println(b);

		        String c = context.getRealPath("/WEB-INF/c.txt");//WEB-INF目录下的资源访问
		        System.out.println(c);

		        String a = context.getRealPath("/WEB-INF/classes/a.txt");//src目录下的资源访问
		        System.out.println(a);
* */

@WebServlet("/filePath")
public class FilePath extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String realPath = context.getRealPath("/b.txt");//web目录下
        System.out.println(realPath);

        String c = context.getRealPath("/WEB-INF/c.txt");//WEB-INF目录下
        System.out.println(c);

        String a = context.getRealPath("/WEB-INF/classes/a.txt");//src目录下
        System.out.println(a);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
