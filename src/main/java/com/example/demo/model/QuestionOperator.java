package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by hello on 2018/6/21.
 */
public interface QuestionOperator extends JpaRepository<Question, Integer> {
    @Query(value = "SELECT question.questionid,question.questionkeyword,question.own from question " +
            "INNER JOIN class_and_question on question.questionid=class_and_question.question_id " +
            "where class_and_question.class_id=?1",nativeQuery = true)
    List<Object[]> findByClassId(String classid);
}
