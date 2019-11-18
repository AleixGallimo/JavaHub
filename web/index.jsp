<%--
  Created by IntelliJ IDEA.
  User: JustinRolo
  Date: 2019/11/11
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to the page</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/indexStyle.css">
</head>
<body>
    <div id="div">
        <div id="logo">
            <img src="img/logo.png" width="570px" height="140px">
        </div>
        <div id="button">
            <!-- Indicates caution should be taken with this action -->
            <div id="text">
                <div id="text_son">
                    <h2>过去属于死神，未来属于你自己。</h2>
                </div>
                <div id="text_son2">
                    <h4>现在就加入JAVA</h4>
                </div>
            </div>
            <div id="b_login">
                <button type="button" class="btn btn-warning" id="button_login" onclick="location.href='login.jsp'">登录</button>
            </div>
            <div id="b_register">
                <button type="button" class="btn btn-warning" id="button_register" onclick="location.href='register.jsp'">注册</button>
            </div>
        </div>
    </div>

</body>
</html>