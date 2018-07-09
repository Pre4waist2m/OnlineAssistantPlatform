package com.example.demo.webSocket;

import com.example.demo.model.ClassAndStudentsRepository;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Created by hello on 2018/4/12.
 */
@Component
@ServerEndpoint("/talk/{param}")
public class talkSocket {
    /* private static List<Session> sessions = new ArrayList<Session>();
     private static List<String> usernames = new ArrayList<String>();
 */
    private static Map<String, Session> nameAndSession = new HashMap<String, Session>();
    private String username = "";
    private static Map<String, List<String>> courseInfo = new HashMap<>();
    ;

    @PostConstruct
    public void init() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://127.0.0.1:3306/field?useSSL=true&serverTimezone=UTC&characterEncoding=utf8&useUnicode=true";

        Connection connection = DriverManager.getConnection(url, "root", "15130140362");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT class_and_stutents.class_id,sys_user.username from sys_user " +
                    " INNER JOIN class_and_stutents on class_and_stutents.stutents_id=sys_user.id ");
            while (resultSet.next()) {
                String key = resultSet.getString("class_id");
                if (courseInfo.containsKey(key)) {
                    List<String> list = courseInfo.get(key);
                    list.add(resultSet.getString("username"));
                } else {
                    List<String> list1 = new LinkedList<>();
                    list1.add(resultSet.getString("username"));
                    courseInfo.put(key, list1);
                }
            }
            System.out.println(courseInfo.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @OnOpen
    public void open(Session session, @PathParam(value = "param") String param) throws IOException, SQLException {
        courseInfo=new HashMap<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://127.0.0.1:3306/field?useSSL=true&serverTimezone=UTC&characterEncoding=utf8&useUnicode=true";

        Connection connection = DriverManager.getConnection(url, "root", "15130140362");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT class_and_stutents.class_id,sys_user.username from sys_user " +
                    " INNER JOIN class_and_stutents on class_and_stutents.stutents_id=sys_user.id ");
            while (resultSet.next()) {
                String key = resultSet.getString("class_id");
                if (courseInfo.containsKey(key)) {
                    List<String> list = courseInfo.get(key);
                    list.add(resultSet.getString("username"));
                } else {
                    List<String> list1 = new LinkedList<>();
                    list1.add(resultSet.getString("username"));
                    courseInfo.put(key, list1);
                }
            }
            System.out.println(courseInfo.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nameAndSession.put(param, session);
        username=param;
        //  String msg = "欢迎" + username + "登陆";
        //  Message message = new Message();
        //  message.setWelcome(msg);
        // message.setUsernames(usernames);
        //  broadcast(sessions, message.toJson());
    }

    public void broadcast(Session session, List<String> usernames, String msg) throws IOException {
        for (String name : usernames) {
            Session session1 = nameAndSession.get(name);
            if(session1==null)continue;
            if (!session1.getId().equals(session.getId()))
                session1.getBasicRemote().sendText(msg);
        }
    }

    Gson gson = new Gson();

    @OnMessage
    public void message(Session session, String json) throws IOException {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        System.out.println("jsonObject:"+jsonObject.toString());
        String courseId = jsonObject.getCourseId();
        List<String> list = courseInfo.get(courseId);
        System.out.println(Arrays.toString(list.toArray()));
        broadcast(session, list, json);
        // broadcast(sessions,json);
      /*  if(vo.getType())*/
      /*  Message message = new Message();
        String to = vo.getTo();
        Session session_to = map.get(to);
        message.setContent(this.username, "<font color='red' >私聊：" + vo.getMsg() + "</font>");
        session_to.getBasicRemote().sendText(message.toJson());
        session.getBasicRemote().sendText(message.toJson());*/
    }

    @OnClose
    public void close(Session session) throws IOException {
        nameAndSession.remove(username);
        //    sessions.remove(session);
        /* sessions.remove(session);
        usernames.remove(this.username);
        map.remove(this.username);
        Message message = new Message();
        String msg = this.username + "退出聊天室";
        message.setWelcome(msg);
        message.setUsernames(usernames);
        broadcast(this.sessions, message.toJson());*/
        System.out.println(username+"退出");
    }
}
