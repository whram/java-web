package com.response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 服务器输出字符数据到浏览器
			* 步骤：
				1. 获取字符输出流
				2. 输出数据

			* 注意：
				* 乱码问题：
					1. PrintWriter pw = response.getWriter();获取的流的默认编码是ISO-8859-1
					2. 设置该流的默认编码
					3. 告诉浏览器响应体使用的编码
					//简单的形式，设置编码，是在获取流之前设置
        			response.setContentType("text/html;charset=utf-8");
* */

@WebServlet("/demoWriter")
public class DemoWriter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //简单的形式，设置编码，是在获取流之前设置
        response.setContentType("text/html;charset=utf-8");
        //获取字符输出流
        //PrintWriter pw = response.getWriter();
        //输出数据
        //pw.write("<h1>hello response</h1>");
        //pw.write("hello response");
        //pw.write("你好");

        //获取字节输出流
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write("Hello".getBytes("utf-8"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
