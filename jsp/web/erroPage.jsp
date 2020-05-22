<%--
  Created by IntelliJ IDEA.
  User: 79366
  Date: 2020/4/15
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>服务器正忙...</h1>

    <%
        String message = exception.getMessage();
        out.println(message);
    %>
</body>
</html>
