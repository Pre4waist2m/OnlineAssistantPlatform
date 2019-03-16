package com.example.demo.model;

import com.example.demo.FieldApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.junit.Assert.*;

/**
 * Created by hello on 2018/7/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FieldApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionOperatorTest {
    @Autowired
    QuestionOperator questionOperator;
    @Test
    public void findByClassId() throws Exception {
        Assert.notNull(questionOperator.findByClassId("123"));
    }

}