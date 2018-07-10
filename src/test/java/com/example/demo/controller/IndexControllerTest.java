package com.example.demo.controller;

import com.example.demo.FieldApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by hello on 2018/7/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FieldApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IndexControllerTest {
    @Autowired
    private MockMvc mockMvc;

   /* @Autowired
    private WebApplicationContext webApplicationContext;
*/
    /*@Before
    public void before() {
        //获取mockmvc对象实例
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }*/

    @Test
    public void loginTest() throws Exception {
       /* MvcResult mvcResult=mockMvc.
                perform(MockMvcRequestBuilders.get("/login"))
                .andReturn();
        int status=mvcResult.getResponse().getStatus();
        System.out.println("状态"+status);
        String responseString=mvcResult.getResponse().getContentAsString();
        System.out.println(responseString);
*/
        mockMvc.perform(MockMvcRequestBuilders.get("/login").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void signupTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/signup").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void welcomeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_HTML)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
