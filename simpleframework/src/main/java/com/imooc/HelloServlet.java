package com.imooc;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 泛型、反射机制 
 *
 *
 * */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet{
    @Override
    protected  void  doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name="我的简易框架";
        req.setAttribute("name",name);
        req.getRequestDispatcher("/WEB-INF/jsp.hello.jsp").forward(req,resp);
         
    }
}
