<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showTime</title>
</head>
<body>

<%
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
%>
                    <h1>欢迎回来,您上次访问时间为<%=value%></h1>
<%
                    break;
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
%>
            <h1>您好,欢迎您首次访问</h1>
<%
        }

%>

</body>
</html>
