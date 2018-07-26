<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Shiro Demo</title>
</head>
<style>
    body {
        padding: 0px;
        margin: 0 auto;
    }
</style>
<body>
<center style="margin-top:50px;">
    <h2>shiro demo main page</h2>
    <%--shiro有jsp标签可以获取登录用户信息--%>
    <p>欢迎登录：<shiro:principal/></p>
    <br/>
    <a href="/logout.do">退出</a>
</center>
</body>
</html>
