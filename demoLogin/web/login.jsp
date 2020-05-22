<%--
  Created by IntelliJ IDEA.
  User: 79366
  Date: 2020/4/13
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>

    <script>
        window.onload = function () {
            document.getElementById("img").onclick = function () {
                this.src="/demoLogin/checkCodeServlet?time="+new Date().getTime()
            }

        }
    </script>

    <style>
        div{
            color: red;
        }

    </style>

</head>
<body>
    <form action = "/demoLogin/loginServlet" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td><input type="text" name="checkCode"></td>
            </tr>
            <tr>
                <td colspan="2"><img id="img" src="/demoLogin/checkCodeServlet"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>

    <div><%=request.getAttribute("checkCode_error") == null ? "" : request.getAttribute("checkCode_error")%></div>
    <div><%=request.getAttribute("user_error") == null ? "" : request.getAttribute("user_error")%></div>

</body>
</html>
