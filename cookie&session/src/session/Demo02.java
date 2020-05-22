package session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
* Session：主菜
	1. 概念：服务器端会话技术，只能在一次会话的多次请求间共享数据，将数据保存在服务器端的对象中。HttpSession
	2. 快速入门：
		1. 获取HttpSession对象：
			HttpSession session = request.getSession();
		2. 使用HttpSession对象：
			Object getAttribute(String name)
			void setAttribute(String name, Object value)
			void removeAttribute(String name)

	3. 原理
		* Session的实现是依赖于Cookie的。
		    获取session时会在服务器中创建一个session对象，并将对象id发送给浏览器
		    Cookie将session的id传回服务器，在服务器中通过id找到session
* */

@WebServlet("/demoSession02")
public class Demo02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //使用session共享数据
        //获取session
        HttpSession session = request.getSession();
        //获取数据
        Object msg = session.getAttribute("msg");
        System.out.println(msg);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
