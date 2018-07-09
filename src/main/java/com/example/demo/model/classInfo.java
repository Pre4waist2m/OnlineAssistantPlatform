package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by hello on 2018/7/4.
 */
@Entity
public class classInfo {
    @Id
    @GeneratedValue
    Integer id;
    String className;
    String classId;
    String teacherId;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "classInfo{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", classId='" + classId + '\'' +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}
