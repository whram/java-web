package com.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 获取请求头数据
 * 方法：
 * (*)String getHeader(String name):通过请求头的名称获取请求头的值
 * Enumeration<String> getHeaderNames():获取所有的请求头名称
* */

@WebServlet("/reqDemo02")
public class DemoHeader extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求头数据
        /*Enumeration<String> headerNames = request.getHeaderNames();
        //遍历
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            //根据名称获取请求头的值
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }*/

        //获取usr-agent
        /*String agent = request.getHeader("user-agent");
        if(agent.contains("Chrome")){
            System.out.println("Chrome!");
        }*/

        //获取referer
        String referer = request.getHeader("referer");
        System.out.println(referer);
        //防盗链操作
        if(referer != null){
            if (referer.contains("/servlet2")){
                //System.out.println("正常访问！");
                response.setContentType("text/html;charset = utf-8");
                response.getWriter().write("正常访问！");
            }else{
                //System.out.println("盗取链接！");
                response.setContentType("text/html;charset = utf-8");
                response.getWriter().write("正常访问！");
            }
        }
    }
}
