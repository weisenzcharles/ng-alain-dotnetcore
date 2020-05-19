package com.ngalain.admin.mapper.user;

import com.ngalain.admin.domain.User;
import com.ngalain.admin.domain.UsersLogin;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@MapperScan
@Repository
public interface UserMapper {

    User getUser(long id);

    List<UsersLogin> getUsersLoginList(long userId);



    User findByUsername(String username);
}
