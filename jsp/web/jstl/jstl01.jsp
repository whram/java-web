<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  JSTL
	1. 概念：JavaServer Pages Tag Library  JSP标准标签库
		* 是由Apache组织提供的开源的免费的jsp标签		<标签>

	2. 作用：用于简化和替换jsp页面上的java代码

	3. 使用步骤：
		1. 导入jstl相关jar包
		2. 引入标签库：taglib指令：  <%@ taglib %>
		3. 使用标签

	4. 常用的JSTL标签
		1. if
		2. choose:相当于java代码的switch语句
		3. foreach:相当于java代码的for语句
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>if标签</h3>
<%--
if:相当于java代码的if语句
			1. 属性：
	            * test 必须属性，接受boolean表达式
	                * 如果表达式为true，则显示if标签体内容，如果为false，则不显示标签体内容
	                * 一般情况下，test属性值会结合el表达式一起使用
       		 2. 注意：
	       		 * c:if标签没有else情况，想要else情况，则可以在定义一个c:if标签
--%>
    <c:if test="true">
        is true...<br>
    </c:if>

<%
    List list = new ArrayList();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    request.setAttribute("l",list);
%>

<c:if test="${not empty l}">
    集合不为空
</c:if>

<h3>choose标签</h3>
<%--
choose:相当于java代码的switch语句
			1. 使用choose标签声明         			相当于switch声明
            2. 使用when标签做判断         			相当于case
            3. 使用otherwise标签做其他情况的声明    	相当于default
--%>

<%
request.setAttribute("number",0);
%>

<c:choose>
    <c:when test="${number == 1}">星期一</c:when>
    <c:when test="${number == 2}">星期二</c:when>
    <c:when test="${number == 3}">星期三</c:when>
    <c:when test="${number == 4}">星期四</c:when>
    <c:when test="${number == 5}">星期五</c:when>
    <c:when test="${number == 6}">星期六</c:when>
    <c:when test="${number == 7}">星期日</c:when>
    <c:otherwise>数据输入有误</c:otherwise>
</c:choose><br>

<h3>foreach标签</h3>

<%--
foreach:相当于java代码的for语句
    重复操作属性：
        begin： 开始值
        end：结束值
        var：临时变量
        step：步长
        varStatus：循环状态对象
            index：容器中元素的索引，从0开始
            count：循环次数，从1开始

    遍历容器属性：
        items：容器对象
        var：容器中元素的临时变量
--%>

<c:forEach begin="1" end="10" var="i" step="2" varStatus="s">
    ${i} ${s.index} ${s.count}<br>
</c:forEach>

<hr>

<c:forEach items="${l}" var="str" varStatus="s">
    ${s.index} ${s.count} ${str}<br>
</c:forEach>

</body>
</html>
