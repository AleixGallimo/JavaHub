package com.cs.controller;

import com.cs.dao.impl.IGoodsDaoImpl;
import com.cs.entity.Goods;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "GoodsServlet", urlPatterns = "/GoodsServlet")
public class GoodsServlet extends HttpServlet {
    IGoodsDaoImpl goodsDao = new IGoodsDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "addGoods":
                try {
                    addGoods(request,response);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case "showGoods":
                showGoods(request,response);
                break;
            case "delete":
                delete(request,response);
                break;
            case "deleteAll":
                deleteAll(request,response);
                break;
            case "sort":
                sort(request,response);
                break;
            case "updateShowGoods":
                updateShowGoods(request,response);
                break;
            case "updateGoods":
                try {
                    updateGoods(request,response);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case "searchGoods":
                searchGoods(request,response);
                break;
            default:
                break;
        }
    }


    //修改商品
    private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException, InvocationTargetException, IllegalAccessException, ServletException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> list = upload.parseRequest(request);
        HashMap<String, String> map = new HashMap<>();
        Goods goods = new Goods();
        String picture = null;
        String fileName;
        String value = null;
        for (FileItem fileItem : list){
            if(fileItem.isFormField()){
                if ("picture".equals(fileItem.getFieldName())){
                    value = fileItem.getString("utf-8");
                } else {
                    String name = fileItem.getFieldName();
                    value = fileItem.getString("utf-8");
                    map.put(name, value);
                }
            }else {
                InputStream is = fileItem.getInputStream();
                ServletContext servletContext = getServletContext();
                String path = servletContext.getRealPath("images");
                File file = new File(path);
                if(!file.exists()){
                    file.mkdir();
                }
                UUID uuid = UUID.randomUUID();
                if ("".equals(fileItem.getName())){
                    fileName = value;
                } else {
                    fileName = uuid.toString()+"_FileName="+fileItem.getName();
                    FileOutputStream fos = new FileOutputStream(path + "\\" + fileName);
                    IOUtils.copy(is,fos);
                    fos.close();
                    is.close();
                }
                goods.setPic(fileName);
            }
        }
        BeanUtils.populate(goods,map);
        Integer count = goodsDao.updateGoods(goods);
        if (count > 0){
            response.sendRedirect("GoodsServlet?action=showGoods");
        }else{
            request.setAttribute("msg","添加失败");
            request.getRequestDispatcher("addGoods.jsp").forward(request,response);
        }
    }

    //修改显示数据
    private void updateShowGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Goods goods = goodsDao.updateShowGoods(Integer.parseInt(id));
        request.setAttribute("id",id);
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("updateGoods.jsp").forward(request,response);
    }

    //删除所有选中项目
    private void deleteAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] ids = request.getParameterValues("id");
        String id = ids[0];
        String[] split = id.split(",");
        int count = 0;
        for (int i = 0;i < split.length;i++){
            count += goodsDao.delGoods(Integer.parseInt(split[i]));
        }
        if (count > 0){
            response.sendRedirect("GoodsServlet?action=showGoods");
        }
    }

    //排序
    private void sort(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String value = request.getParameter("selectSort");
        if ("default".equals(value)){
            response.sendRedirect("GoodsServlet?action=showGoods");
        } else if ("byName".equals(value)){
            List<Goods> list = goodsDao.sortByName();
            request.setAttribute("list",list);
            request.getRequestDispatcher("showGoods.jsp").forward(request,response);
        }else if ("byRange".equals(value)){
            Integer priceA = Integer.parseInt(request.getParameter("priceA"));
            Integer priceB = Integer.parseInt(request.getParameter("priceB"));
            int c;
            Boolean flag = false;
            if (priceA > priceB){
                c = priceA;
                priceA = priceB;
                priceB = c;
                flag = true;
            }
            List<Goods> list = goodsDao.priceAtoB(priceA, priceB, flag);
            request.setAttribute("list", list);
            request.getRequestDispatcher("showGoods.jsp").forward(request,response);
        } else {
            List<Goods> list = goodsDao.sort(value);
            request.setAttribute("list",list);
            request.getRequestDispatcher("showGoods.jsp").forward(request,response);
        }
    }

    //单个删除事件
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String start1 = request.getParameter("start");
//        Integer start = Integer.parseInt(request.getParameter("start"));
        String id = request.getParameter("id");
        Integer count = goodsDao.delGoods(Integer.parseInt(id));
        if (count > 0){
            response.sendRedirect("GoodsServlet?action=showGoods&start="+start1);
        } else {
            request.setAttribute("msg","删除失败");
        }

    }

    //显示列表（首页）
    private void showGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*int start = 0;
        int count = 2;

        try {
            start = Integer.parseInt(request.getParameter("start"));
        }catch ( NumberFormatException e ){

        }
        //上一页
        int pre = start - count;
        pre = pre < 0 ? 0 : pre;

        //下一页
        int next = start + count;
        int total = goodsDao.countGoods();

        //数字分页
        int page;
        if (total % count == 0){
            page = total/count;
        } else {
            page = (total/count) + 1;
        }

        //最后一页
        int last;
        if (total % count == 0){
            last = total - count;
        }else{
            last = total - total % count;
        }
        next = next > last ? last : next;

        List<Goods> list = goodsDao.pageGoods(start, count);
        request.setAttribute("total", total);
        request.setAttribute("start", start);
        request.setAttribute("page", page);
        request.setAttribute("next", next);
        request.setAttribute("pre", pre);
        request.setAttribute("last", last);
        request.setAttribute("list", list);
        request.getRequestDispatcher("showGoods.jsp").forward(request,response);*/
        //我的代码
        List<Goods> list = goodsDao.showGoods();
        request.setAttribute("list",list);
        request.getRequestDispatcher("showGoods.jsp").forward(request,response);
    }

    //搜索商品
    private void searchGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String search = request.getParameter("search");
        int start = 0;
        int count = 2;

        try {
            start = Integer.parseInt(request.getParameter("start"));
        }catch ( NumberFormatException e ){

        }
        //上一页
        int pre = start - count;
        pre = pre < 0 ? 0 : pre;

        //下一页
        int next = start + count;
        int total = goodsDao.searchGoodsCount(search);

        //数字分页
        int page;
        if (total % count == 0){
            page = total/count;
        } else {
            page = (total/count) + 1;
        }

        //最后一页
        int last;
        if (total % count == 0){
            last = total - count;
        }else{
            last = total - total % count;
        }
        next = next > last ? last : next;
        List<Goods> list = goodsDao.searchGoods(search, start, count);
        request.setAttribute("total", total);
        request.setAttribute("start", start);
        request.setAttribute("page", page);
        request.setAttribute("next", next);
        request.setAttribute("pre", pre);
        request.setAttribute("last", last);
        request.setAttribute("list", list);
        request.getRequestDispatcher("showGoods.jsp").forward(request,response);*/

        String search = request.getParameter("search");
        List<Goods> list = goodsDao.searchGoods(search);
        request.setAttribute("list",list);
        request.getRequestDispatcher("showGoods.jsp").forward(request,response);

    }


    //添加商品信息
    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException, InvocationTargetException, IllegalAccessException, ServletException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> list = upload.parseRequest(request);
        HashMap<String, String> map = new HashMap<>();
        Goods goods = new Goods();
        for (FileItem fileItem : list){
            if(fileItem.isFormField()){
                String name = fileItem.getFieldName();
                map.put(name,fileItem.getString("utf-8"));
            }else{
                InputStream is = fileItem.getInputStream();
                ServletContext servletContext = getServletContext();
                String path = servletContext.getRealPath("images");
                File file = new File(path);
                if(!file.exists()){
                    file.mkdir();
                }
                UUID uuid = UUID.randomUUID();
                String fileName = uuid.toString()+"_FileName="+fileItem.getName();
                FileOutputStream fos = new FileOutputStream(path + "\\" + fileName);
                IOUtils.copy(is,fos);
                fos.close();
                is.close();

                goods.setPic(fileName);
            }
        }
        BeanUtils.populate(goods,map);
        Integer count = goodsDao.addGoods(goods);
        if (count > 0){
            response.sendRedirect("GoodsServlet?action=showGoods");
        }else{
            request.setAttribute("msg","添加失败");
            request.getRequestDispatcher("addGoods.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
