package com.cx.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/23-16:20
 * @Version 2022.2 1.8
 */
@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("uname","lili");
        req.getRequestDispatcher("demo02").forward(req,resp);
//        resp.sendRedirect("demo02");
    }
}
