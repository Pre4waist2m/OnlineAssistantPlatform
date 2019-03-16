package com.example.demo.model;

import com.example.demo.FieldApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by hello on 2018/7/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FieldApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageSaveTest {
    @Autowired MessageSave messageSave;
    @Test
    public void findByQuestionid() throws Exception {
        System.out.println(messageSave.findByQuestionid(5199));
    }

}