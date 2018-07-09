package com.example.a01378359.testapp.eventbus;

/**
 * Created by 01378359 on 2018/7/9.
 */

public class Event {
    private String data;

    public Event(String data){
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
