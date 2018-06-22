package com.example.a01378359.testapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 01378359 on 2018/6/22.
 */

public class Main {
    public static void main(String[] args) {
        final List<String>list = new ArrayList<String>();

        list.add("hhgggh");
        list.remove(0);

        list.add(0,"hhahfdaf");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}
