package com.example.demo.webSocket;

/**
 * Created by hello on 2018/4/11.
 */

public class ContentVo {
    String to;
    String msg;
    int type;

    public void setTo(String to) {
        this.to = to;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTo() {
        return to;
    }

    public String getMsg() {
        return msg;
    }

    public int getType() {
        return type;
    }
}