<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: JustinRolo
  Date: 2019/11/11
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/loginStyle.css">
    <script type="text/javascript">
        var str = "${msg}";
        if (str != ""){
            alert(str);
        }
        function verify() {
            var username = document.getElementById("username");
            var password = document.getElementById("password");
            var span = document.getElementsByTagName("span");
            if (username.value == ""){
                span[0].style.color = "red";
                return false;
            } else if (password.value == ""){
                span[1].style.color = "red";
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    response.sendRedirect("GoodsServlet?action=showGoods");
                    return;
                }
            }
        }
%>
<div id="form">
    <div id="formDiv">
        <form action="UserServlet?action=login" method="post" onsubmit="return verify()">
            <div id="title">
                <h1>登录系统</h1>
            </div>
            <div class="form-group">
                <span>请输入您的账号</span>
                <input type="text" class="form-control" placeholder="Username" name="username" id="username" maxlength="16">
            </div>
            <div class="form-group">
                <span>请输入您的密码</span>
                <input type="password" class="form-control" placeholder="Password" name="password" id="password" maxlength="16">
            </div>
            <div class="checkbox" style="margin-bottom: 20px;">
                <label>
                    <input type="checkbox" name="remember"> 记住密码，下次直接登录
                </label>
            </div>
            <div id="button">
                <div id="b_login">
                    <button type="submit" class="btn btn-warning" id="button_login">登录</button>
                </div>
                <div id="b_register">
                    <button type="button" class="btn btn-warning" id="button_register" onclick="location.href='register.jsp'">注册</button>
                </div>
                <div class="clean" ></div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
