package com.java.mvc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SpringBoot 兼容Servlet 3.0    Servlet 注解版本
 * @author xuweizhi
 * @date 2019/03/30 23:47
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("123");
        resp.getWriter().flush();
        resp.getWriter().close();
        System.out.println("哈哈");
    }

}
