package com.cenmingzhong.dao.impl;

import com.cenmingzhong.dao.UserDao;
import com.cenmingzhong.prjo.User;
import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @author cenmingzhong
 * @create 2020-09-12-下午 17:03
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`, `username`, `password`, `email` from t_user where username = ?";

        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`, `username`, `password`, `email` from t_user " +
                "where username = ? and password = ?";

        return queryForOne(User.class,sql,username,password);
    }


    @Override
    public int saveUser(User user) {
    String sql = "insert into t_user(`username`, `password`, `email`) " +
            "VALUES(?,?,?)";
        return updata(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
