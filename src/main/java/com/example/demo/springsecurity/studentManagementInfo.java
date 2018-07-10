package com.example.demo.springsecurity;

/**
 * Created by hello on 2018/7/7.
 */
public class studentManagementInfo {
    String id;
    String username;

    @Override
    public String toString() {
        return "studentManagementInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
