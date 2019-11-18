<%--
  Created by IntelliJ IDEA.
  User: JustinRolo
  Date: 2019/11/11
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/registerStyle.css?v=1.0">
    <script type="text/javascript" src="js/registerVerify.js"></script>
    <script>
        onload = function () {
            var str = "${msg}";
            if (str != ""){
                alert(str);
            }
        }
    </script>
</head>
<body>

    <div id="form">
        <div id="formDiv">
            <form action="UserServlet?action=register" method="post" onsubmit="return verify()">
                <div id="title">
                    <h1>用户注册</h1>
                </div>
                <div class="form-group">
                    <span>账号以字母开头由4-16位字母和数字组成</span>
                    <input type="text" class="form-control" placeholder="Username" name="username" maxlength="16" id="username">
                </div>
                <div class="form-group">
                    <span>密码由4-16位字母和数字组成</span>
                    <input type="password" class="form-control" placeholder="Password" name="password" maxlength="16" id="password">
                </div>
                <div class="form-group">
                    <span>请再次确认您的密码</span>
                    <input type="password" class="form-control" placeholder="Password" name="rePassword" maxlength="16" id="rePassword">
                </div>

                <div class="form-group">
                    <span>兴趣爱好必填一项</span><br/>
                    <input type="text" class="form-control" placeholder="Hobby" name="hobbies" style="width: 110px;float: left;margin-right: 5px;" maxlength="6" id="e1">
                    <input type="text" class="form-control" placeholder="Hobby" name="hobbies" style="width: 110px;float: left;margin-right: 5px;" maxlength="6" id="e2">
                    <input type="text" class="form-control" placeholder="Hobby" name="hobbies" style="width: 110px;float: left;margin-right: 5px;" maxlength="6" id="e3">
                </div>
                <br/>
                <br/>
                <div class="form-group">
                    <span>请输入您的电子邮箱</span>
                    <input type="text" class="form-control" placeholder="E-mail" name="email" id="email">
                </div>
                <div class="form-group">
                    <span>请输入您的电话号码</span>
                    <input type="text" class="form-control" placeholder="Phone Number " name="phone" id="phone">
                </div>

                <div class="form-group">
                    <span>请选择性别</span><br/>
                    <select class="form-control" name="sex" style="width: 150px">
                        <option value="man">男</option>
                        <option value="woman">女</option>
                    </select>
                </div>

                <div class="form-group" style="margin-bottom: 30px;" >
                    <span>请选择地址</span><br/>
                    <select class="form-control" name="address" style="width: 110px;float: left;padding: 2px;" id="d1">
                        <option value="default">请选择国家</option>
                        <option value="ch">中国</option>
                        <option value="us">美国</option>
                    </select>
                    <select class="form-control" name="address" style="width: 110px;float: left;padding: 2px;margin: 0 5px 0 5px;" id="d2">
                        <option value="default">请选择省份</option>
                        <option value="gd">广东</option>
                    </select>
                    <select class="form-control" name="address" style="width: 110px;float: left;padding: 2px;" id="d3">
                        <option value="default">请选择城市</option>
                        <option value="gz">广州</option>
                    </select>
                </div>
                <br/>
                <div id="button" style="margin-top: 10px">
                    <div id="b_login">
                        <button type="submit" class="btn btn-warning" id="button_login">注册</button>
                    </div>
                    <div id="b_register">
                        <button type="button" class="btn btn-warning" id="button_register" onclick="location.href='login.jsp'">返回登录</button>
                    </div>
                    <div class="clean"></div>
                </div>
            </form>
        </div>
        <div class="clean"></div>
    </div>
</body>
</html>
