package com.cx.fruit.controllers;

import com.cx.fruit.dao.FruitDAO;
import com.cx.fruit.dao.impl.FruitDAOImpl;
import com.cx.fruit.myspringmvc.ViewBaseServlet;
import com.cx.fruit.pojo.Fruit;
import com.cx.fruit.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/27-10:57
 * @Version 2022.2 1.8
 */
public class FruitController extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();

    private String update(Integer fid,String fname,Integer price,Integer fcount,String remark){
        fruitDAO.updateFruit(new Fruit(fid,fname, price ,fcount ,remark ));
        //4.资源跳转
        //super.processTemplate("index",request,response);
        //request.getRequestDispatcher("index.html").forward(request,response);
        //此处需要重定向，目的是重新给IndexServlet发请求，重新获取furitList，然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的
//        response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }


    private String edit(Integer fid ,HttpServletRequest request){
//        String fid = req.getParameter("fid");
        if(fid != null ){
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
//            super.processTemplate("edit",req,resp);
            return "edit";
        }
        return "error";
    }

    private String del(Integer fid){
//        req.setCharacterEncoding("utf-8");
        if(fid != null){
            fruitDAO.delFruitByFid(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname,Integer price,Integer pageNo,Integer fcount,String remark){
        //1.设置编码
//        request.setCharacterEncoding("utf-8");
        fruitDAO.addFruit(new Fruit(0,fname,price,fcount,remark));
        return "redirect:fruit.do";
    }

    private String index(String oper,String keyword,Integer pageNo,HttpServletRequest request){
//        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession() ;

        if(pageNo == null){
            pageNo = 1;
        }

        //如果oper!=null 说明 通过表单的查询按钮点击过来的
        //如果oper是空的，说明 不是通过表单的查询按钮点击过来的

        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            pageNo = 1 ;
            if(StringUtil.isEmpty(keyword)){
                keyword = "" ;
            }
            session.setAttribute("keyword",keyword);
        }else{
            //说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
            //此时keyword应该从session作用域获取
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj ;
            }else{
                keyword = "" ;
            }
        }

        session.setAttribute("pageNo",pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword , pageNo);

        session.setAttribute("fruitList",fruitList);

        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount+5-1)/5 ;
        /*
        总记录条数       总页数
        1               1
        5               1
        6               2
        10              2
        11              3
        fruitCount      (fruitCount+5-1)/5
         */
        session.setAttribute("pageCount",pageCount);

        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
//        super.processTemplate("index",request,response);
        return "index";
    }

}
