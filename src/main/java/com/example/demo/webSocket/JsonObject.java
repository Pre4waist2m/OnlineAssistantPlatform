package com.example.demo.webSocket;


/**
 * Created by hello on 2018/7/8.
 */
public class JsonObject {
    private String courseId;
    private String own;
    private String msg;
    private String time;
    private String question_id;

    public String getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "JsonObject{" +
                "courseId='" + courseId + '\'' +
                ", own='" + own + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                ", question_id='" + question_id + '\'' +
                '}';
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }
}
