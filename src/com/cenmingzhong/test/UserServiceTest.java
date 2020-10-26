package com.cenmingzhong.test;

import com.cenmingzhong.prjo.User;
import com.cenmingzhong.service.UserService;
import com.cenmingzhong.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author cenmingzhong
 * @create 2020-09-12-下午 19:01
 */
public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"admin2","123456","cenmingzhong@qq.com"));
    }

    @Test
    public void login() {
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("admin")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}