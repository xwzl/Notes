package com.java.mvc.servlet;

import com.java.mvc.filter.HelloWorldFilter;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

//@Configuration
public class CustomServletContextInitializer implements ServletContextInitializer {

    private final static String URL = "/hello1";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("创建 helloWorldServlet...");

        ServletRegistration.Dynamic servlet = servletContext.addServlet(
                Servlet.class.getSimpleName(),
                Servlet.class);

        servlet.addMapping(URL);

        System.out.println("创建 HelloWorldFilter...");

        FilterRegistration.Dynamic filter = servletContext.addFilter(
                HelloWorldFilter.class.getSimpleName(), HelloWorldFilter.class);

        EnumSet<DispatcherType> dispatcherType = EnumSet.allOf(DispatcherType.class);
        dispatcherType.add(DispatcherType.REQUEST);
        dispatcherType.add(DispatcherType.FORWARD);

        filter.addMappingForUrlPatterns(dispatcherType, true, URL);
    }
}

class Servlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("123");
        resp.getWriter().flush();
        resp.getWriter().close();
        System.out.println("哈哈");
    }
}