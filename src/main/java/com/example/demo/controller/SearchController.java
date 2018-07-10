package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.servletUploadify.resourceListRepository;
import com.example.demo.validation.UserRepository;
import com.example.demo.websecurity.SysRole;
import com.example.demo.websecurity.SysRoleRepository;
import com.example.demo.websecurity.SysUser;
import com.example.demo.websecurity.SysUserRepository;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.validation.email_verfication;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.mail.MessagingException;
import java.io.File;
import java.util.*;

/**
 * Created by hello on 2018/3/14.
 */
@RestController
public class SearchController {

    @Autowired
    applyCourseRepository applyCourseRepository;

    @Autowired
    ClassAndStudentsRepository classAndStudentsRepository;

    @Autowired
    ClassAndQuestionRepository classAndQuestionRepository;

    @Autowired
    saveClassInfo saveClassInfo;

    @Autowired
    email_verfication email_verfication;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageSave messageSave;

    @Autowired
    SysUserRepository sysUserRepository;


    @Autowired
    QuestionOperator questionOperator;

    @Autowired
    SysRoleRepository sysRoleRepository;


    @Autowired
    resourceListRepository resourceListRepository;

    public String random() {
        int a[] = new int[4];
        String string = " ";
        for (int i = 0; i < 4; i++) {
            a[i] = (int) (Math.random() * 10);
            string += a[i];
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
        List<SysRole> sysRoles = new ArrayList<>();
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
    public void delete() {
        sysRoleRepository.deleteSysRoleById();
        sysUserRepository.deleteSysUserById();
        String a = "删除成功";
        return;
    }

    @PostMapping("/saveQuestion")
    public String saveQueston(@RequestParam("own") String own, @RequestParam("question_keyword") String question_keyword, @RequestParam("question") String question
            , @RequestParam("courseId") String courseId) {
        Question question1 = new Question();
        question1.setOwn(own);
        question1.setQuestionkeyword(question_keyword);
        question1.setQuestion(question);
        Question que = questionOperator.save(question1);
        ClassAndQuestion classAndQuestion = new ClassAndQuestion();
        classAndQuestion.setClassId(courseId);
        classAndQuestion.setQuestionId(String.valueOf(que.getQuestionid()));
        classAndQuestionRepository.save(classAndQuestion);
        return String.valueOf(que.getQuestionid());
    }

    @PostMapping("/saveMessage")
    public String saveMessage(@RequestParam(value = "own", required = false) String own, @RequestParam(value = "question_id", required = false) String question_id,
                              @RequestParam(value = "message", required = false) String message, @RequestParam(value = "time", required = false) String time) {
        Message message1 = new Message();
        message1.setOwn(own);
        message1.setQuestionid(Integer.parseInt(question_id));
        message1.setMessage(message);
        message1.setTime(time);
        messageSave.save(message1);
        return "";
    }


    @PostMapping("/allMessage")
    public String findAllMessage(@RequestParam("question_id") String question_id) {
        List<Message> list = messageSave.findByQuestionid(new Integer(question_id));
        JSONArray ja = JSONArray.fromObject(list);
        return ja.toString();
    }

    @PostMapping("/OpenClass")
    public String saveClassInfo(@RequestParam("className") String className, @RequestParam("classId") String classId,
                                @RequestParam("teacherName") String teacherName) {
        classInfo ci = new classInfo();
        ci.setClassName(className);
        ci.setClassId(classId);
        SysUser sysUser = sysUserRepository.findByUsername(teacherName);
        ci.setTeacherId(String.valueOf(sysUser.getId()));
        classInfo classInfo= saveClassInfo.save(ci);
        ClassAndStutents classAndStutents=new ClassAndStutents();
        classAndStutents.setClassId(String.valueOf(classInfo.getId()));
        classAndStutents.setStutentsId(classInfo.getTeacherId());
        classAndStudentsRepository.save(classAndStutents);
        return "ok";
    }

    @PostMapping("/deleteCourse")
    public void deleteCourse(@RequestParam("courseId") String courseId) {
        System.out.println(courseId);
        classInfo info = new classInfo();
        info.setId(Integer.parseInt(courseId));
        saveClassInfo.delete(info);
    }


    @PostMapping("/deleteQuestion")
    public String deleteQuestion(@RequestParam("questionId") String questionId) {
        System.out.print("删除问题");
        System.out.println(questionId);
        classAndQuestionRepository.deleteByQuestionId(questionId);
        return "ok";
    }


    @PostMapping("/deleteResource")
    public String deleteResource(@RequestParam("resourceId") String resourceId, @RequestParam("serverName") String serverName) {
        System.out.println("待删除资源的名称。" + resourceId);
        resourceListRepository.deleteByResourceId(resourceId);
        File file = new File(serverName);
        file.delete();
        return "ok";
    }


    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId") String studentId) {
        classAndStudentsRepository.deleteByStutentsId(studentId);
        return "ok";
    }


    @PostMapping("/allMessageNew")
    public ModelAndView talkManagement(@RequestParam("question_id") String question_id, @RequestParam("courseId") String courseId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/talkNew");
        List<Message> list = messageSave.findByQuestionid(new Integer(question_id));
        JSONArray jsonArray = JSONArray.fromObject(list);
        modelAndView.addObject("jsonArray", jsonArray);
        Question question = new Question();
        Optional<Question> optional = questionOperator.findById(new Integer(question_id));
        question = optional.get();
        modelAndView.addObject("question", question);
        //问题列表
        List<Question> questionsList = new ArrayList<>();
        List<Object[]> questionList = questionOperator.findByClassId(courseId);
        for (Object object : questionList) {
            Object[] objects = (Object[]) object;
            Question question1 = new Question();
            question1.setQuestionid((int) objects[0]);
            question1.setQuestionkeyword((String) objects[1]);
            question1.setOwn((String) objects[2]);
            /*System.out.println(question1.toString());*/
            questionsList.add(question1);
        }
        modelAndView.addObject("questionList", questionsList);
        modelAndView.addObject("courseId", courseId);
        return modelAndView;
    }


    @PostMapping("/addStudents")
    public String addStudents(@RequestParam("Id")String Id,@RequestParam("studentId")String studentId,
                              @RequestParam("courseId")String classId){
        applyCourseRepository.UUpdateStatus(Integer.parseInt(Id));
        System.out.println(classId+ "   asd "+studentId);
        ClassAndStutents classAndStutents=new ClassAndStutents();
        classAndStutents.setClassId(classId);
        classAndStutents.setStutentsId(studentId);
        System.out.println(classAndStutents.toString());
        classAndStudentsRepository.save(classAndStutents);
        return "ok";
    }
    @PostMapping("/denyStudents")
    public String denyStudents(@RequestParam("Id")String Id){
        applyCourseRepository.UUpdateStatus(Integer.parseInt(Id));
        return "ok";
    }

    @PostMapping("/applyForClass")
    public String   applyForClass(@RequestParam("userId")String userId,
                                  @RequestParam("classId")String classId,
                                  @RequestParam("appluInfo")String appluInfo){
        applyCourse applyCourse=new applyCourse();
        applyCourse.setStudentId(userId);
        applyCourse.setCourseId(classId);
        applyCourse.setApplyInfo(appluInfo);
        applyCourse.setStatus(0);
        applyCourseRepository.save(applyCourse);
        return "ok";
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
