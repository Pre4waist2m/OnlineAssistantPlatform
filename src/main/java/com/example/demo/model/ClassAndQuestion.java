package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClassAndQuestion {
    @Id
    @GeneratedValue
    int id;
    String classId;
    String questionId;

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getClassId() {
        return classId;
    }

    public int getId() {
        return id;
    }

    public String getQuestionId() {
        return questionId;
    }
}
