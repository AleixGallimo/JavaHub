package com.cs.controller;

import com.cs.dao.impl.IUserDaoImpl;
import com.cs.entity.User;
import com.cs.util.ArrayUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
    IUserDaoImpl userDao = new IUserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "register":
                register(request,response);
                break;
            case "login":
                login(request,response);
                break;
            case "logout":
                logout(request,response);
                break;
            default:
                break;
        }
    }

    //登出
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getServletContext().removeAttribute("uId");
        Cookie cookie = new Cookie("username", URLEncoder.encode(" ", "utf-8"));
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect("login.jsp");
    }

    //登录校验
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Integer id = userDao.checkUser(new User(username, password));
        if (id > 0){
            String remember = request.getParameter("remember");
            if (remember != null){
                Cookie cookie = new Cookie("username", URLEncoder.encode(username, "utf-8"));
                cookie.setMaxAge(60*15);
                response.addCookie(cookie);
            }
            request.getServletContext().setAttribute("uId",id);
            request.getSession().setAttribute("username", username);
            request.getRequestDispatcher("GoodsServlet?action=showGoods").forward(request,response);

//          request.getServletContext().setAttribute("uid",id);
//          response.sendRedirect("GoodsServlet?action=showGoods");
        } else {
            request.setAttribute("msg","登陆失败，请检查用户名或密码是否有误");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        //校验用户名是否存在
        Integer check = userDao.checkUsername(username);
        if (check != 0){
            request.setAttribute("msg","用户名已存在，请检查后输入或登录");
            request.getRequestDispatcher("register.jsp").forward(request,response);
            return;
        }

        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String[] hobbiesArray = request.getParameterValues("hobbies");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String[] addressArray = request.getParameterValues("address");

        //处理hobbies和address数组
        String hobbies = ArrayUtil.toStringByArray(hobbiesArray);
        String address = ArrayUtil.toStringByArray(addressArray);

        //执行dao层进行注册
        User user = new User(username,password,sex,hobbies,phone,email,address);
        Integer count = userDao.addUser(user);

        if (count > 0){
            request.setAttribute("msg","注册成功！请登录");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else{
            request.setAttribute("msg","注册失败");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }
    }
}
