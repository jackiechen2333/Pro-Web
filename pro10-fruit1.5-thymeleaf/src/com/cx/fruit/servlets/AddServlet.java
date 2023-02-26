package com.cx.fruit.servlets;

import com.cx.fruit.dao.FruitDAO;
import com.cx.fruit.dao.impl.FruitDAOImpl;
import com.cx.fruit.myspringmvc.ViewBaseServlet;
import com.cx.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/26-22:40
 * @Version 2022.2 1.8
 */
@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取参数
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        fruitDAO.addFruit(new Fruit(0,fname,price,fcount,remark));

        response.sendRedirect("index");
    }
}
