package com.cenmingzhong.test;

import java.lang.reflect.Method;

/**
 * @author cenmingzhong
 * @create 2020-09-17-下午 16:44
 */
public class UserServletTest {
    public void login(){
        System.out.println("这是login方法调用了");
    }
    public void regist(){
        System.out.println("这是regist方法调用了");
    }
    public void updataeUser(){
        System.out.println("这是updateUser方法调用了");
    }
    public void updateUserPassword(){
        System.out.println("这是updateUserPassword方法调用了");
    }

    public static void main(String[] args) {
        String action = "login";

        try {
            //获取action业务鉴定字符串，获取相应的业务 方法 和对象
            Method method = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(method);
            //调用目标业务方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
