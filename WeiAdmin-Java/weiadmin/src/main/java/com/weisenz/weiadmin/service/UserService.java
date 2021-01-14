package com.weisenz.weiadmin.service;

import com.weisenz.weiadmin.domain.*;
import com.weisenz.weiadmin.mapper.user.UserMapper;
import com.weisenz.weiadmin.security.AuthTokenUser;
import com.weisenz.weiadmin.security.JsonWebTokenUtility;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService {

    @Resource()
    private UserMapper userMapper;

    public User getUser(long id) {
        return userMapper.getUser(id);
    }

    public List<UsersLogin> getUsersLoginList(long userId) {
        return userMapper.getUsersLoginList(userId);
    }

    public LoginResult login(String userName, String password) {
        if (!(userName.equals("admin") || userName.equals("user")) && !password.equals("admin")) {
            return LoginResult.builder().msg("Invalid username or password").build();
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
                .msg("ok")
                .user(map)
                .build();
    }
}