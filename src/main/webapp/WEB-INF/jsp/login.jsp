<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shiro Demo</title>
</head>
<style>
    body {
        padding: 0px;
        margin: 0 auto;
    }

    .error {
        color: red;
    }

    form input {
        width: 200px;
        height: 30px;
    }
</style>
<body>
<center style="margin-top:50px;">
    <h2>shiro demo login page</h2>
    <p class="error">${errMsg}</p>
    <form action="${pageContext.request.contextPath}/check" method="post">
        <input type="text" name="username" placeholder="username"/>
        <br/><br/>
        <input type="password" name="password" placeholder="password"/>
        <br/><br/>
        <input type="submit" value="login"/>
    </form>
</center>
</body>
</html>
