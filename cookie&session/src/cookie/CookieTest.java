package cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
案例：记住上一次访问时间
		1. 需求：
			1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
			2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串

		2. 分析：
			1. 可以采用Cookie来完成
			2. 在服务器中的Servlet判断是否有一个名为lastTime的cookie
				1. 有：不是第一次访问
					1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
					2. 写回Cookie：lastTime=2018年6月10日11:50:01
				2. 没有：是第一次访问
					1. 响应数据：您好，欢迎您首次访问
					2. 写回Cookie：lastTime=2018年6月10日11:50:01
* */

@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应消息体的数据格式
        response.setContentType("text/html;charset=utf-8");

        //获取所有的Cookie
        Cookie[] cookies = request.getCookies();
        //设置flag表示是存在lastTime
        boolean flag = false;
        //遍历cookies
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                //获取cookie的名称
                String name = cookie.getName();
                if ("lastTime".equals(name)) {
                    //有lastTime，不是第一次
                    flag = true;
                    //设置新的时间
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//格式化时间
                    String str_date = sdf.format(new Date());
                    //URL编码
                    str_date = URLEncoder.encode(str_date,"utf-8");
                    cookie.setValue(str_date);
                    //设置cookie存活时间为一个月
                    cookie.setMaxAge(60*60*24*30);
                    //将cookie重新发送回去
                    response.addCookie(cookie);
                    //响应数据，获取数据
                    String value = cookie.getValue();
                    value = URLDecoder.decode(value,"UTF-8");
                    response.getWriter().write("<h1>欢迎回来，您上次访问时间为： " + value + "</h1>");
                }
            }
        }

        if(!flag) {
            //设置新的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//格式化时间
            String str_date = sdf.format(new Date());
            //URL编码
            str_date = URLEncoder.encode(str_date,"utf-8");
            Cookie cookie = new Cookie("lastTime", str_date);
            //设置cookie存活时间为一个月
            cookie.setMaxAge(60*60*24*30);
            //将cookie重新发送回去
            response.addCookie(cookie);
            response.getWriter().write("<h1>您好，欢迎您首次访问</h1>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
