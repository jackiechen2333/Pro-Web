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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/23-12:19
 * @Version 2022.2 1.8
 */
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNo = 1;
        String pageNoStr = req.getParameter("pageNo");
        if(StringUtil.isNotEmpty(pageNoStr)){
            pageNo = Integer.parseInt(pageNoStr);
        }
        HttpSession session = req.getSession();
        session.setAttribute("pageNo",pageNo);
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(pageNo);
        session.setAttribute("fruitList",fruitList);

        int fruitCount = fruitDAO.getFruitCount();
        int pageCount = (fruitCount + 5 -1)/5;
        session.setAttribute("pageCount",pageCount);

        super.processTemplate("index",req,resp);
    }
}
