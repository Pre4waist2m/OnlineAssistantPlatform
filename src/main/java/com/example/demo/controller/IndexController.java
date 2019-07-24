package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.servletUploadify.ResourceList;
import com.example.demo.servletUploadify.resourceListRepository;
import com.example.demo.springsecurity.SysUser;
import com.example.demo.springsecurity.SysUserRepository;
import com.example.demo.springsecurity.studentManagementInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.*;


/**
 * Created by hello on 2018/3/14.
 */
@Controller
public class IndexController {

    @Autowired
    ApplyCourseRepository applyCourseRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    resourceListRepository resourceListRepository;


    @Autowired
    QuestionOperator questionOperator;

    @Autowired
    saveClassInfo saveClassInfo;


    @Autowired
    ClassAndStudentsRepository classAndStudentsSave;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        System.out.println("11");
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + request.getServerPort() + path + "/";
        Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("sysUser信息:" + sysUser.toString());
        redirectAttributes.addAttribute("userId", sysUser.getId());

        if (roles.contains("ROLE_teacher")) {
            /*model.addAttribute("userId",sysUser.getId());*/
            return "redirect:/setclass";
        } else {
            return "redirect:/MyCenter";
        }
    }


    @RequestMapping("/setclass")
    public String setClass(Model model, @RequestParam("userId") String userId) {
        System.out.println("用户的id:" + userId);
        List<classInfo> lists = saveClassInfo.findByTeacherId(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("classList", lists);
        List<applyCourse> applyCourseslist= applyCourseRepository.findByStatus(userId);
        model.addAttribute("applyInfo",applyCourseslist);
        return "/SetClass";
    }

    @RequestMapping("/setclass/courseManagement")
    public ModelAndView courseManagement(
            @RequestParam("courseId") String courseId,
            @RequestParam("userId") String userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/CourseManagement");
        modelAndView.addObject("userId", userId);
        Optional<classInfo> info = saveClassInfo.findById(Integer.parseInt(courseId));
        classInfo classInfo = info.get();
        modelAndView.addObject("courseName", classInfo.getClassName());
        List<Object[]> lists = sysUserRepository.findbyClassId(courseId);
        List<studentManagementInfo> infoList = new ArrayList<>();
        for (Object object : lists) {
            Object[] objects = (Object[]) object;
            String string1 = (String) objects[0];
            BigInteger bigInteger = new BigInteger(String.valueOf(objects[1]));
            studentManagementInfo student = new studentManagementInfo();
            student.setId(String.valueOf(bigInteger));
            student.setUsername(string1);
            infoList.add(student);
        }
        modelAndView.addObject("studentsInfo", infoList);
        //问题列表
        List<Question> questionsList = new ArrayList<>();
        List<Object[]> questionList = questionOperator.findByClassId(courseId);
        for (Object object : questionList) {
            Object[] objects = (Object[]) object;
            Question question = new Question();
            question.setQuestionid((int) objects[0]);
            question.setQuestionkeyword((String) objects[1]);
            question.setOwn((String) objects[2]);
            System.out.println(question.toString());
            questionsList.add(question);
        }
        modelAndView.addObject("courseId", courseId);
        modelAndView.addObject("questionList", questionsList);
        List<ResourceList> resourceLists = resourceListRepository.findByClassId(courseId);
        modelAndView.addObject("resourceList", resourceLists);
        return modelAndView;
    }

    @GetMapping("/MyCenter")
    public String getMyCenter(Model model, @RequestParam("userId") String userId) {
        List<Object[]> list = classAndStudentsSave.findByStutentsId(userId);
        List<classInfo> classAndStutents = new ArrayList<>();
        for (Object[] objects : list) {
            classInfo classInfo = new classInfo();
            Integer integer = Integer.parseInt(String.valueOf(objects[0]));
            String string = String.valueOf(objects[1]);
            classInfo.setId(integer);
            classInfo.setClassName(string);
            classAndStutents.add(classInfo);
        }
        model.addAttribute("myclass", classAndStutents);
        model.addAttribute("userId",userId);
        return "/MyCenter";
    }


    @GetMapping("/signup")
    public String signup() {
        return "/signup";
    }


    @GetMapping("/login")
    public String index() {
        return "login";
    }

    @RequestMapping(value = {"/talk"}, method = RequestMethod.GET)
    public String get(Model model) {
        List<Question> list = questionOperator.findAll();
        model.addAttribute("questionList", list);
        return "/talk";
    }

    @GetMapping("/ask")
    public String askGet(Model model) {
        List<Question> list = questionOperator.findAll();
        model.addAttribute("questionList", list);
        return "/ask_question";
    }


  /*  @GetMapping("/resource")
    public String getResource(Model model) {
        List<ResourceList> lists = resourceListRepository.findAll();
        model.addAttribute("resourceList", lists);
        return "/resource";
    }*/

  /*  @GetMapping("/uploadify")
    public void uploadify(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, FileUploadException {
        Map map = new HashMap();
        request.setCharacterEncoding("utf-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        String path = request.getRealPath("/file");
        factory.setRepository(new File(path));
        factory.setSizeThreshold(1024 * 1024*1024);
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
        for (FileItem item : list) {
            String name = item.getName() ;
            System.out.println("获得的名字："+name);
            String fileSuffix  = name.substring(name.lastIndexOf(".")+1,name.length());
            //file的后缀
            String oldName = name.replaceAll("." + fileSuffix,"");
            String fileName = String.valueOf(new Date().getTime());//生成唯一的文件标识
            String newName = fileName + "." + fileSuffix;
            OutputStream out = new FileOutputStream(new File(path,newName));
            InputStream in = item.getInputStream() ;
            in.close();
            out.close();
        }
    }*/
}
