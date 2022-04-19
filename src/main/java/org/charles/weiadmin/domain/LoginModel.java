package org.charles.weiadmin.domain;

import java.io.Serializable;

public class LoginModel implements Serializable {
    private static final long serialVersionUID = 7967042759955215985L;

    public String userName;
    public String password;
    public Integer type;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
