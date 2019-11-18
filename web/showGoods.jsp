<%@ page import="com.cs.entity.Goods" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: JustinRolo
  Date: 2019/11/12
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<% Object uId = request.getServletContext().getAttribute("uId");%>
<head>
    <title>商品展示</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/goodsStyle.css">
    <script type="text/javascript">
        onload = function () {
            var select = document.getElementsByClassName("select");
            var all = document.getElementById("button_all");
            var pic = 1==<%=uId%>?true:false
            if(pic){
                document.getElementById("pic").href = "images/bbg.jpg";
            }else{
                document.getElementById("pic").href = "#";
            }
            /*var thisPage = document.getElementsByName("thisPage");
            var on = ;
            var page = thisPage.values - 1;
            if (on == page){
                thisPage.innerText = "当前";
            }*/

            for (var i in select){
                select[i].onclick = function () {
                    var isAll = true;
                    for (var i = 0;i < select.length;i++){
                        if (!select[i].checked){
                            isAll = false;
                        }
                    }
                    if (isAll){
                        all.innerText = "反选";
                    } else {
                        all.innerText = "全选";
                    }
                }
            }
        }



        var flag = false;
        function selectAll() {
            var select = document.getElementsByClassName("select");
            var all = document.getElementById("button_all");
            if (all.innerText == "全选"){
                flag = false;
            }
            if (flag){
                for (var i in select){
                    select[i].checked = false;
                }
                flag = false;
                all.innerText = "全选";
                all.style.background = "#131313";
                all.style.color = "#dd8624";
            } else {
                for (var i in select){
                    select[i].checked = true;
                }
                flag = true;
                all.innerText = "反选"
                all.style.background = "#dd8624";
                all.style.color = "#131313";
            }

        }
        function delAll() {
            var select = document.getElementsByClassName("select");
            var id = "";
            for (var i = 0;i < select.length;i++){
                if (select[i].checked){
                    id += select[i].value + ",";
                }
            }
            if (id == ""){
                return;
            }
            location.href = "GoodsServlet?action=deleteAll&id="+id;
        }


        function byRange(obj) {
            debugger
            var sp = document.getElementById("s_p");
            if (obj.value == "byRange"){
                sp.innerHTML = " <input type='text' class='tp' name='priceA'>-<input type='text' class='tp' name='priceB'>"
            } else {
                sp.innerHTML = "";
            }
        }
        function checkNull() {
            var tp = document.getElementsByClassName("tp");
            if (tp[0].value == "" || tp[1].value == ""){
                tp[0].style.border = "red 1px solid";
                tp[1].style.border = "red 1px solid";
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div id="head">
        <div id="logout">
            <a href="UserServlet?action=logout">注销</a>
        </div>
        <div id="id">
            enjoy your own life ！
        </div>
        <div class="clear"></div>
    </div>
    <hr width="100%" style="margin: 0;border-top:1px solid #292929;">
    <div id="font">
        <div id="logoTitle">
            <img src="img/title_logo.png" onclick="select(this)" style="margin-right: 10px"><img src="img/title.png" height="80%">
        </div>
    </div>
<%--    <hr width="100%" style="margin: 0;border-top:1px solid #292929;">--%>
    <div id="screen">
        <div id="s_s">
            <form action="GoodsServlet?action=sort&id=1" method="post" onsubmit="return checkNull()">
                <div id="s2">
                    <select class="form-control input-sm" id="sort" style="width: 120px;color: #ee9634;background-color: black;border-color: #ee9634" name="selectSort" onchange="byRange(this)">
                        <option value="default">默认</option>
                        <option value="asc">价格从低到高</option>
                        <option value="desc">价格从高到低</option>
                        <option value="byName">按标题排序</option>
                        <option value="byRange" >价格范围排序</option>
                    </select>
                    <div id="s_p">

                    </div>
                </div>

                <div id="s3">
                    <button type="submit" class="btn btn-warning" id="button_enter">确认</button>
                </div>
            </form>
        </div>

        <div id="s_o">
            <button type="submit" class="btn btn-warning" id="button_add" onclick="location.href='addGoods.jsp'">添加</button>
            <button type="submit" class="btn btn-warning" id="button_all" onclick="selectAll()" >全选</button>
            <button type="submit" class="btn btn-warning" id="button_del" onclick="delAll()">删除选中</button>
        </div>

        <div class="search">
            <form action="GoodsServlet?action=searchGoods" method="post">
                <input type="text" placeholder="搜索从这里开始.." name="search" id=""/>
                <button type="submit"><i>搜索</i></button>
            </form>
        </div>

        <div class="clear"></div>
    </div>
    
<%--    <hr width="100%" style="margin: 0;border-top:1px solid #292929;">--%>
    
    <%--<div style="width: 1250px;margin: 0 auto 0 auto;border-color: rgba(0,0,0,0.2)" >
        <img src="img/animal001.png">
    </div>--%>
    
    <div id="b_list">
        <c:forEach items="${list}" var="goods">
            <div class="list">
                <input type="hidden" name="id" value="${goods.id}">
                <div class="l_img"><img src="images/${goods.pic}" width="200px" height="140px" ></div>
                <div class="l_price"><div class="p_p">${goods.price}元</div><div class="p_s">库存:${goods.stock}</div></div>
                <div class="l_title"><p>${goods.name}</p></div>
                <div class="l_description">
                    <p>${goods.description}</p>
                </div>
                <div class="l_operation">
                    <input type="checkbox" value="${goods.id}" class="select">
                    <a href="GoodsServlet?action=updateShowGoods&id=${goods.id}">编辑</a><a href="GoodsServlet?action=delete&id=${goods.id}">删除</a>
                </div>
            </div>
        </c:forEach>
        <div class="clear"></div>
        <%--<div id="foodDiv">
            <div id="lastFood">
                <%
                    Integer start = (Integer)request.getAttribute("start");
                    if (start != 0){
                %>
                <a href="GoodsServlet?action=showGoods&start=0">[首  页]</a>
                <a href="GoodsServlet?action=showGoods&start=${pre}">[上一页]</a>
                <%
                    }
                %>
                <%
                    Integer j = (Integer)request.getAttribute("page");
                    for (int i = 0;i < j;i++){
                %>
                <input type="hidden" name="thisPage" value="<%=i+1%>">
                <a href="GoodsServlet?action=showGoods&start=<%=i%>"><%=i+1%></a>
                <%
                    }
                %>

                <%
                    Integer last = (Integer)request.getAttribute("last");
                    if (start != last){
                %>
                <a href="GoodsServlet?action=showGoods&start=${next}">[下一页]</a>
                <a href="GoodsServlet?action=showGoods&start=${last}">[末 页]</a>
                <%
                    }
                %>
            </div>
        </div>--%>
    </div>
<div id="food" style="border-color: white;height: 100px;width: 100%;text-align: center;padding-top: 40px">
    <a href="images/bbg.jpg" id="pic">localhost:8080//Week01Project/oa</a>
</div>
</body>
</html>
