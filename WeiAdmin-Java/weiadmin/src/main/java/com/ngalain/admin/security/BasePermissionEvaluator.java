package com.ngalain.admin.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class BasePermissionEvaluator implements PermissionEvaluator {

    private JsonWebTokenUtility tokenService = new JsonWebTokenUtility();

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        JsonWebTokenAuthentication jsonWebTokenAuthentication = (JsonWebTokenAuthentication)authentication;
        System.out.println("*****************hasPermission");
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
