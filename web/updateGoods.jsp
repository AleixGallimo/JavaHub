<%--
  Created by IntelliJ IDEA.
  User: JustinRolo
  Date: 2019/11/13
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改商品信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/updateStyle.css?v=1.0">
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
    <form action="GoodsServlet?action=updateGoods" method="post" enctype="multipart/form-data" onsubmit="return btn()">
        <div id="formDiv">
            <div id="title">
                <h1>修改商品信息</h1>
            </div>
            <input type="hidden" name="id" value="${id}">
            <div class="form-group">
                <span>商品名称</span>
                <input type="text" class="form-control" placeholder="请输入标题" value="${goods.name}" name="name" maxlength="8">
            </div>
            <div class="form-group">
                <span>价格</span>
                <input type="text" class="form-control" placeholder="请输入价格" value="${goods.price}" name="price" maxlength="7" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();">
            </div>
            <div class="form-group">
                <span>库存</span>
                <input type="text" class="form-control" placeholder="请输入库存" value="${goods.stock}" name="stock" maxlength="7" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();">
            </div>
            <div class="form-group">
                <img src="images/${goods.pic}" width="200px" height="140px">
            </div>
            <input type="hidden" name="picture" value="${goods.pic}">
            <div class="form-group">
                <p style="color: white">请上传封面图片</p>
                <input type="file" name="file">
                <p class="help-block">图片请选择200x140分辨率，效果最佳</p>
            </div>
            <div class="form-group">
                <span>描述</span>
                <textarea class="form-control" rows="3" name="description"  maxlength="56">${goods.description}</textarea>
            </div>
            <br/>
            <div id="button">
                <div id="b_add">
                    <button type="submit" class="btn btn-warning" id="button_add">修改商品</button>
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
