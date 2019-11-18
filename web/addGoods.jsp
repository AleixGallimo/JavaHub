<%--
  Created by IntelliJ IDEA.
  User: JustinRolo
  Date: 2019/11/12
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/addStyle.css?v=1.0">
    <script type="text/javascript">
        function btn(){
            var text = document.getElementsByClassName("form-control");
            var span = document.getElementsByTagName("span");
            for (var i = 0;i < text.length;i++){
                if (text[i].value == ""){
                    span[i].style.color = "red";
                    return false;
                }
            }
            return true;
        }
    </script>
</head>
<body>
    <div id="form">
        <form action="GoodsServlet?action=addGoods" method="post" enctype="multipart/form-data" onsubmit="return btn()">
            <div id="formDiv">
                <div id="title">
                    <h1>添加商品</h1>
                </div>
                <div class="form-group">
                    <span>商品名称</span>
                    <input type="text" class="form-control" placeholder="name" name="name" maxlength="8">
                </div>
                <div class="form-group">
                    <span>价格</span>
                    <input type="text" class="form-control" placeholder="price" name="price" maxlength="7" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();">
                </div>
                <div class="form-group">
                    <span>库存</span>
                    <input type="text" class="form-control" placeholder="stock" name="stock" maxlength="7" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();">
                </div>
                <div class="form-group">
                    <p style="color:white;">请上传封面图片</p>
                    <input type="file" name="file">
                    <p class="help-block">图片请选择200x140分辨率，效果最佳</p>
                </div>
                <div class="form-group">
                    <span>描述</span>
                    <textarea class="form-control" rows="3" name="description"  maxlength="56"></textarea>
                </div>
                <br/>
                <div id="button">
                    <div id="b_add">
                        <button type="submit" class="btn btn-warning" id="button_add">添加商品</button>
                    </div>
                    <div id="b_back">
                        <button type="button" class="btn btn-warning" id="button_back" onclick="location.href='GoodsServlet?action=showGoods'">返回</button>
                    </div>
                    <div class="clean" ></div>
                </div>
            </div>
        </form>
    </div>
</body>
</html>

