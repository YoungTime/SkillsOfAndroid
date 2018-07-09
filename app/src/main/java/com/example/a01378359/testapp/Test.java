package com.example.a01378359.testapp;

import java.util.ArrayList;

/**
 * Created by 01378359 on 2018/7/5.
 */

public class Test {
    private static ArrayList<Info> infoList = new ArrayList<Info>(); // 一组船
    // 主函数
    public static void main(String[] args) {
        /**
         *  这里应该是数据库查询和添加语句
         */
        // 往数据库加船的信息，不是你干的事，提供了数据库的
        Info info1 = new Info(01,"1号船","由西向东");
        Info info2 = new Info(02,"2号船","由南向北");
        infoList.add(info1);
        infoList.add(info2);

        // 遍历整个船的信息，这里应该是给你们封装好了，你问问同学
        for (Info info :
                infoList) {
            // info 代表每一条船,比如你要船号为 01 的信息
            if (info.id==01){
                // 为方框设置信息
                String name = info.name;
                String line = info.line;
            }

        }
    }

}

// 一个船的信息
class Info{
    int id;// 船号
    String name; // 船名
    String line; // 航线
    public Info(int id,String name,String line){
    }
}
