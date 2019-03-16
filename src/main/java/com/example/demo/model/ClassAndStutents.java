package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClassAndStutents {
    @Id
    @GeneratedValue
    int id;
    String classId;
    String stutentsId;

    @Override
    public String toString() {
        return "ClassAndStutents{" +
                "id=" + id +
                ", classId='" + classId + '\'' +
                ", stutentsId='" + stutentsId + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setStutentsId(String stutentsId) {
        this.stutentsId = stutentsId;
    }

    public int getId() {
        return id;
    }

    public String getClassId() {
        return classId;
    }

    public String getStutentsId() {
        return stutentsId;
    }
}
