package org.charles.weiadmin.service;

import org.charles.weiadmin.domain.LoginResult;
import org.charles.weiadmin.domain.User;
import org.charles.weiadmin.domain.UsersLogin;
import org.charles.weiadmin.mapper.UserMapper;
import org.charles.weiadmin.security.AuthTokenUser;
import org.charles.weiadmin.security.JsonWebTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(long id) {
        return userMapper.getUser(id);
    }

    public List<UsersLogin> getUsersLoginList(long userId) {
        return userMapper.getUsersLoginList(userId);
    }

    public LoginResult login(String userName, String password) {
        if (!(userName.equals("admin") || userName.equals("user")) && !password.equals("admin")) {
            return LoginResult.builder().message("Invalid username or password").build();
        }
        JsonWebTokenUtility jsonWebTokenUtility = new JsonWebTokenUtility();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        List<String> roleNames = new ArrayList<>();
        roleNames.add("admin");
        List<String> privileges = new ArrayList<>();
        privileges.add("admin");
        AuthTokenUser authTokenUser = AuthTokenUser.builder()
            .email(userName + "@test.com")
            .userId(userName)
            .expirationDate(calendar.getTime())
            .roleNames(roleNames)
            .privileges(privileges)
            .build();
        String token = jsonWebTokenUtility.createJsonWebToken(authTokenUser);
//        log.info("token is {{}}", token);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("name", userName);
        map.put("email", userName + "@test.com");
        map.put("id", UUID.randomUUID().toString());
        map.put("time", Calendar.getInstance().getTime().getTime());

        return LoginResult.builder()
            .message("ok")
            .user(map)
            .build();
    }
}
