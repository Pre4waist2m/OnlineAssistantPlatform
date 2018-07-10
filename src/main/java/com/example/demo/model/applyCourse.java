package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by hello on 2018/7/8.
 */
@Entity
public class applyCourse {
    @Id
    @GeneratedValue
    int id;
    String studentId;
    String applyInfo;
    String courseId;
    @Column(name="status")
    int status;

    @Override
    public String toString() {
        return "applyCourse{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", applyInfo='" + applyInfo + '\'' +
                ", courseId='" + courseId + '\'' +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
