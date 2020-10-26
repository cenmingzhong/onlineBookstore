package com.cenmingzhong.service.impl;

import com.cenmingzhong.dao.UserDao;
import com.cenmingzhong.dao.impl.UserDaoImpl;
import com.cenmingzhong.prjo.User;
import com.cenmingzhong.service.UserService;

/**
 * @author cenmingzhong
 * @create 2020-09-12-下午 18:54
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());

    }

    @Override
    public boolean existsUsername(String username) {

        if (userDao.queryUserByUsername(username)==null){
            return false;
        }

        return true;
    }
}
