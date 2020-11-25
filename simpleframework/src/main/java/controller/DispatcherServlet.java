package controller;
/**
 * 派发类，
 * 拦截所有请求
 * 解析相关的请求
 * 派发给对应的Controller里面的方法进行处理
 * */


import controller.frontend.MainPageController;
import controller.superadmin.HeadLineOperationController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//反斜杠表示拦截所有的servlet,web请求
@Slf4j
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(){

    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("request path is : " + req.getServletPath());
        System.out.println("request method is : " + req.getMethod());
        if (req.getServletPath() == "/frontend/getmainpageinfo" && req.getMethod() == "GET"){
             new MainPageController().getMainPageInfo(req, resp);
        } else if(req.getServletPath() == "/superadmin/addheadline" && req.getMethod() == "POST"){
            new HeadLineOperationController().addHeadLine(req, resp);
        }
    }
}
