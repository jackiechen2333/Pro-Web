package com.cx.fruit.servlets;

import com.cx.fruit.dao.FruitDAO;
import com.cx.fruit.dao.impl.FruitDAOImpl;
import com.cx.fruit.myspringmvc.ViewBaseServlet;
import com.cx.fruit.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/26-22:24
 * @Version 2022.2 1.8
 */
@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruitByFid(fid);
            resp.sendRedirect("index");
        }

    }
}
