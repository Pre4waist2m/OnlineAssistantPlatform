package com.example.demo.model;

import com.example.demo.FieldApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by hello on 2018/7/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FieldApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApplyCourseRepositoryTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ApplyCourseRepository applyCourseRepository;

    @Test
    public void findByStatus() throws Exception {
        System.out.println(applyCourseRepository.
                findByStatus(String.valueOf(152)));
        System.out.println();applyCourseRepository.
                findByStatus(String.valueOf(158));
    }

    @Test
    public void UUpdateStatus() throws Exception {
        applyCourseRepository.UUpdateStatus(5192);
    }

}