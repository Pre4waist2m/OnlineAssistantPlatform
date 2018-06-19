package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Created by hello on 2018/3/14.
 */
@Controller
public class IndexController {

    @GetMapping("/signup")
    public String signup() {
        return "/signup";
    }

    @GetMapping("/login")
    public String index() {
        return "login";
    }

    @GetMapping("/")
    public String get() {
        return "/talk";
    }

    @GetMapping("/talk")
    public String get2() {
        return "ask_question";
    }

}
