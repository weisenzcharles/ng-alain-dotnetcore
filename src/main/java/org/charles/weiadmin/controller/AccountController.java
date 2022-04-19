package org.charles.weiadmin.controller;


import org.charles.weiadmin.common.ResponseResult;
import org.charles.weiadmin.domain.LoginModel;
import org.charles.weiadmin.domain.User;
import org.charles.weiadmin.security.AuthTokenUser;
import org.charles.weiadmin.security.JsonWebTokenUtility;
import org.charles.weiadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RequestMapping("api/account")
@RestController
public class AccountController {


    @Autowired
    private UserService userService;

    @GetMapping("/api/user")
    public String user(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "user/user";
    }

    @PostMapping("/login")
//    public ResponseResult login(@RequestBody Map<String, String> map) {
    public ResponseResult login(@RequestBody LoginModel loginModel) {
        ResponseResult responseResult = new ResponseResult();
        if (!(loginModel.userName.equals("admin") || loginModel.userName.equals("user")) || !loginModel.password.equals("admin")) {
            responseResult.setCode(401);
            responseResult.setMsg("Invalid username or password（admin/admin）");
            return responseResult;
        }
        JsonWebTokenUtility jsonWebTokenUtility = new JsonWebTokenUtility();
        User user = new User();
        user.setId(10000);
        user.setUserName(loginModel.userName);
        user.setEmail("master@weilog.net");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        user.setTime(calendar.getTime());
        List<String> roleNames = new ArrayList<>();
        roleNames.add("admin");
        List<String> privileges = new ArrayList<>();
        privileges.add("admin");
//        log.info("login request map is {}", map);
        AuthTokenUser authTokenUser = AuthTokenUser.builder()
            .email(loginModel.userName + "@test.com")
            .userId(loginModel.userName)
            .expirationDate(calendar.getTime())
            .roleNames(roleNames)
            .privileges(privileges)
            .build();
        String token = jsonWebTokenUtility.createJsonWebToken(authTokenUser);
        user.setToken(token);
        responseResult.setData(user);
        return responseResult;
//
//        LoginResult result = userService.login(map.getOrDefault("userName", "")
//                , map.getOrDefault("password", ""));
//        responseResult.setData(result);
//        return responseResult;
    }
}
