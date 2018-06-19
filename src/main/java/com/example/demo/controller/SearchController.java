package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.services.UserRepository;
import com.example.demo.websecurity.SysRole;
import com.example.demo.websecurity.SysRoleRepository;
import com.example.demo.websecurity.SysUser;
import com.example.demo.websecurity.SysUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.email_verfication;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2018/3/14.
 */
@RestController
public class SearchController {


    @Autowired
    email_verfication email_verfication;


    @Autowired
    UserRepository userRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    SysRoleRepository sysRoleRepository;

    public String random() {
        int a[] = new int[4];
        String string = " ";
        for (int i = 0; i < 4; i++) {
            a[i] = (int) (Math.random() * 10);
            string += a[i] ;
            //  System.out.println(a[i]+" ");
        }
        return string;
    }


    //注册提交的信息
    @RequestMapping("/register")
    public String Signupfunction(@RequestBody User user) throws MessagingException {
        System.out.println("进入注册");
        //  System.out.println("title:"+user.getTitle()+"username: "+user.getUsername()+"password: "+user.getPassword()+"email:"+user.getEmail());

        System.out.println(user.toString());
        //user表中保存用户
        userRepository.save(user);

//保存用户名和角色
        SysRole sysRole = new SysRole();
        SysUser sysUser = new SysUser();
        List<SysRole> sysRoles=new ArrayList<>();


        sysUser.setPassword(user.getPassword());
        sysUser.setUsername(user.getUsername());
        sysRole.setName(user.getTitle());
        sysUser.setRoles(sysRoles);
        sysRoles.add(sysRole);

        sysUserRepository.save(sysUser);
        sysRoleRepository.save(sysRole);

        String string = this.random();


        //设置要发送的随机数
        email_verfication.setString(string);
        email_verfication.setTo(user.getEmail());
        email_verfication.sendidentifycode();
        return string;
    }

    @PostMapping(value = "/rollback")
    public void  delete(){
        sysRoleRepository.deleteSysRoleById();
        sysUserRepository.deleteSysUserById();
        System.out.println("删除");
        String a="删除成功";
        return ;
    }

  /*  @RequestMapping("/login")
    @ResponseBody
    public boolean login(@RequestParam("email") String  email, @RequestParam("password") String password)
    {
        System.out.println(email+password);
        System.out.println(userRepository.findByEmail(email));
        User user=userRepository.findByEmail(email);
        System.out.println(user.getEmail()+user.getPassword());
        if(user!=null&&(user.getPassword().equals(password)))
        {
          return true;
        }
        return  false;
    }*/


}
