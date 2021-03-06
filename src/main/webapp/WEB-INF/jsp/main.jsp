<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Shiro Demo</title>
</head>
<style>
    body {
        padding: 0px;
        margin: 0 auto;
    }

    button {
        width: 160px;
        height: 30px;
        background-color: red;
        color: white;
        border: none;
    }

    ul {
        list-style: none;
        margin-left: -40px;
        margin-right: 0px;
    }

    ul li {
        margin: 10px;
        width: 150px;
        height: 25px;
        background-color: black;
        color: white;
        font-size: 15px;
        padding: 5px;
    }

    ul li a {
        text-decoration: none;
        color: white;
    }
</style>
<body>
<center style="margin-top:50px;">
    <h2>shiro demo main page</h2>
    <%--shiro有jsp标签可以获取登录用户信息--%>
    <p>Welcome:<shiro:principal/></p>
    <p>
        The roles you have:
        <c:forEach items="${roles}" var="role">
            ${role}&nbsp;
        </c:forEach>
    </p>
    <br/>
    <ul>
        <li>
            <a href="/personal">personal center</a>
        </li>
        <li>
            <a href="/student">student op</a>
        </li>
        <li>
            <a href="/teacher">teacher op</a>
        </li>
        <li>
            <a href="/administrator">administrator op</a>
        </li>
    </ul>
    <br/>
    <button type="button" onclick="window.location.href='/logout'">退出</button>
</center>
</body>
</html>
