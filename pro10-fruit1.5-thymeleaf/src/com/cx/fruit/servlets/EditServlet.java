package com.cx.fruit.servlets;

import com.cx.fruit.dao.FruitDAO;
import com.cx.fruit.dao.impl.FruitDAOImpl;
import com.cx.fruit.myspringmvc.ViewBaseServlet;
import com.cx.fruit.pojo.Fruit;
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
 * @Data 2023/2/26-18:38
 * @Version 2022.2 1.8
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fid = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fid)){
            int f = Integer.parseInt(fid);
            Fruit fruit = fruitDAO.getFruitByFid(f);
            req.setAttribute("fruit",fruit);
            super.processTemplate("edit",req,resp);
        }
    }
}
