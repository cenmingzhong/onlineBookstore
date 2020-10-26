package com.cenmingzhong.web;

import com.cenmingzhong.prjo.User;
import com.cenmingzhong.service.UserService;
import com.cenmingzhong.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cenmingzhong
 * @create 2020-09-12-下午 20:50
 */
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = userService.login(new User(null, username, password, null));

        if (loginUser==null){

            //把错误信息和回显的表单项信息，保存在request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);


            System.out.println("用户名或密码错误，登录失败");
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
            System.out.println("登录成功");
        }


    }
}
