package com.example.demo.model;

import com.example.demo.FieldApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.config.QuerydslWebConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

/**
 * Created by hello on 2018/7/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FieldApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassAndQuestionRepositoryTest {

    @Autowired ClassAndQuestionRepository classAndQuestionRepository;
    @Test
    public void deleteByQuestionId() throws Exception {
   //     classAndQuestionRepository.deleteByQuestionId(String.valueOf(5195));
    }


}