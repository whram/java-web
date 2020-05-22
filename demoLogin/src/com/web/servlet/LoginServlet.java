package com.web.servlet;

import com.dao.UserDao;
import com.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        //获取请求参数
        /*String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new UserDao().login(new User(username, password));*/

        //使用BeanUtils工具类，简化数据封装
        //获取所有请求参数
        Map<String, String[]> map = request.getParameterMap();
        //创建User对象
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        String checkCode = map.get("checkCode")[0];

        //通过session获取生成的验证码
        HttpSession session = request.getSession();
        String checkCode_session = (String)session.getAttribute("checkCode_session");

        //删除session中验证码
        session.removeAttribute("checkCode_session");
        //判断验证码是否正确,忽略大小写
        if (checkCode_session!= null && checkCode_session.equalsIgnoreCase(checkCode)) {
            //验证码一致，判断用户名密码是否正确

            User user = new UserDao().login(loginUser);

            if (user != null) {
                //登录成功

                /*
                //存储数据
                request.setAttribute("user",user);
                //转发到successServlet
                request.getRequestDispatcher("/successServlet").forward(request,response);
                */

                //存储数据
                session.setAttribute("username",user.getUsername());
                //重定向到success.jsp,绝对路径
                response.sendRedirect(request.getContextPath()+"/success.jsp");

            }else {
                //登录失败
                /*
                request.getRequestDispatcher("/failServlet").forward(request,response);
                * */

                //存储信息提示到request
                request.setAttribute("user_error","用户名或者密码错误！");
                //重定向到登录界面
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }else {
            //登录失败
            request.setAttribute("checkCode_error","验证码错误！");
            //重定向到登录界面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
