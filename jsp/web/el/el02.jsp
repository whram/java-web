<%@ page import="com.domian.User" %>
<%@ page import="java.util.*" %>
<%--
  使用：
		1. 运算：
			* 运算符：
				1. 算数运算符： + - * /(div) %(mod)
				2. 比较运算符： > < >= <= == !=
				3. 逻辑运算符： &&(and) ||(or) !(not)
				4. 空运算符： empty
					* 功能：用于判断字符串、集合、数组对象是否为null或者长度是否为0
					* ${empty list}:判断字符串、集合、数组对象是否为null或者长度为0
					* ${not empty str}:表示判断字符串、集合、数组对象是否不为null 并且 长度>0
		2. 获取值
			1. el表达式只能从域对象中获取值
			2. 语法：
				1. ${域名称.键名}：从指定域中获取指定键的值
					* 域名称：
						1. pageScope		--> pageContext
						2. requestScope 	--> request
						3. sessionScope 	--> session
						4. applicationScope --> application（ServletContext）
					* 举例：在request域中存储了name=张三
					* 获取：${requestScope.name}
				2. ${键名}：表示依次从最小的域中查找是否有该键对应的值，直到找到为止。
				    pageScope < requestScope < sessionScope < applicationScope
				3. 获取对象、List集合、Map集合的值
					1. 对象：${域名称.键名.属性名}
						* 本质上会去调用对象的getter方法
					2. List集合：${域名称.键名[索引]}
					3. Map集合：
						* ${域名称.键名.key名称}
						* ${域名称.键名["key名称"]}
		3. 隐式对象：
			* el表达式中有11个隐式对象
			* pageContext：
				* 获取jsp其他八个内置对象
					* ${pageContext.request.contextPath}：动态获取虚拟目录
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>算数运算</h3>
    ${3 + 4}<br>

<%
    request.setAttribute("name","Adam");
    session.setAttribute("age",21);
    session.setAttribute("name","Reacher");

    User user = new User("Tom",20,new Date());
    request.setAttribute("u",user);

    List list = new ArrayList();
    list.add("aaa");
    list.add("bbb");
    list.add(user);
    request.setAttribute("l",list);

    Map map = new HashMap();
    map.put("sname","张三");
    map.put("gender","男");
    map.put("user",user);
    request.setAttribute("m",map);
%>

<h4>获取值</h4>
${requestScope.name}<br>
${sessionScope.age}<br>
${name}<br>

${requestScope.u}<br>

<h4>通过对象的属性来获取</h4>
${u.name}<br>
${u.birStr}<br>

<h4>获取list值</h4>
${l}<br>
${l[0]}<br>
${l[2].name}<br>

<h4>获取map值</h4>
${m.gender}<br>
${m["sname"]}<br>
${m.user.birStr}<br>

<h4>empty运算符</h4>
<%
    String str = "aaaa";
    request.setAttribute("str",str);
%>
${not empty str}

<h4>el隐式对象</h4>
${pageContext.request}<br>
${pageContext.request.contextPath}<br>

</body>
</html>
