package com.cenmingzhong.test;

import com.cenmingzhong.dao.UserDao;
import com.cenmingzhong.dao.impl.UserDaoImpl;
import com.cenmingzhong.prjo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author cenmingzhong
 * @create 2020-09-12-下午 17:15
 */
public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        if (userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","admin")==null){
            System.out.println("用户名或者密码错误，登录失败");
        }else{
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"admin1","123456","cenmingzhong@qq.com")));
    }
}