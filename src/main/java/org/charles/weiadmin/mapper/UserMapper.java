package org.charles.weiadmin.mapper;

import org.charles.weiadmin.domain.User;
import org.charles.weiadmin.domain.UsersLogin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User getUser(long id);

    List<UsersLogin> getUsersLoginList(long userId);

    User findByUsername(String username);
}
