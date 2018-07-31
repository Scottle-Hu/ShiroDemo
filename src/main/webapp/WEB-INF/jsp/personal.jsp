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

</style>
<body>
<center style="margin-top:50px;">
    <h2>shiro demo main page</h2>
    <%--shiro有jsp标签可以获取登录用户信息--%>
    <p>Username:<shiro:principal/></p>
    <p>
        Roles:
        <c:forEach items="${roles}" var="role">
            ${role}&nbsp;
        </c:forEach>
    </p>
    <p>
        Permissions:
        <c:forEach items="${permissions}" var="per">
            ${per}&nbsp;
        </c:forEach>
    </p>
    <br/>
    <button type="button" onclick="window.location.href='/logout'">退出</button>
</center>
</body>
</html>
