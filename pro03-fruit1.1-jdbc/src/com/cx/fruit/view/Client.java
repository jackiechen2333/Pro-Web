package com.cx.fruit.view;

import com.cx.fruit.comtroller.Menu;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-20:24
 * @Version 2022.2 1.8
 */
public class Client {
    public static void main(String[] args) {
        Menu menu = new Menu();

        int select = menu.showMainMenu();
        boolean flag = true;
        while (flag){
            switch (select){
                case 1:
                    //显示库存列表
                    menu.showFruitList();
                    break;
                case 2:
                    menu.addFruit();
                    break;
                case 3:
                    menu.showFruitInfo();
                    break;
                case 4:
                    menu.delFruit();
                    break;
                case 5:
                    flag=menu.exit();
                    break;
                default:
                    System.out.println("你不按套路出牌！");
                    break;
            }
        }
        System.out.println("谢谢使用！再见！");
    }
}
