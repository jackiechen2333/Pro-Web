package com.cx.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/2-19:27
 * @Version 2022.2 1.8
 */
@WebServlet(urlPatterns = {"/demo01"},
        initParams = {
        @WebInitParam(name = "hello",value = "world"),
        @WebInitParam(name = "dun",value = "wenyan")
})
public class Demo01Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println(initValue);

        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println(contextConfigLocation);
    }
}
