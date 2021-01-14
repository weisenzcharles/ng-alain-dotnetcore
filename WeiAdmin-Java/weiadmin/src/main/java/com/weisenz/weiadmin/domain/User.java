package com.weisenz.weiadmin.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class User implements Serializable {
    private static final long serialVersionUID = 3597946498546494989L;

    private Integer id;
    private String userName;
    private String realName;
    private String password;
    private String token;
    private String email;
    private Date time;

    private List<String> roles;

    public User() {
        super();
    }

    public User(int id, String userName, String realName) {
        super();
        this.id = id;
        this.userName = userName;
        this.realName = realName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}