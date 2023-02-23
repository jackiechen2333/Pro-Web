package com.cx.servlets;

import com.cx.fruit.dao.FruitDAO;
import com.cx.fruit.dao.impl.FruitDAOImpl;
import com.cx.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    /**
     * @Description:相应post请求
     * @author:cx
     * @data:2023/2/22-22:02
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fCountStr = req.getParameter("fcount");
        Integer fcount = Integer.parseInt(fCountStr);
        String remark = req.getParameter("remark");

        FruitDAO fruitDAOImpl = new FruitDAOImpl();
        boolean flag = fruitDAOImpl.addFruit(new Fruit(0, fname, price, fcount, remark));
        System.out.println(flag ? "添加成功" : "添加失败");
    }
}
