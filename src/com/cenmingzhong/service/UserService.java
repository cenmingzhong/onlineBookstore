package com.cenmingzhong.service;

import com.cenmingzhong.prjo.User;

/**
 * @author cenmingzhong
 * @create 2020-09-12-下午 17:29
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);


}
