package com.cenmingzhong.web;

import com.cenmingzhong.prjo.User;
import com.cenmingzhong.service.UserService;
import com.cenmingzhong.service.impl.UserServiceImpl;
import sun.misc.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cenmingzhong
 * @create 2020-09-12-下午 19:30
 */
public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        if ("abcde".equalsIgnoreCase(code)) {
            if (userService.existsUsername(username)) {

                //把回显信息保存在request域中
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);



                System.out.println("用户名[" + username + "]已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

            } else {
                userService.registerUser(new User(null,username,password,email));

                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        } else {

            //把回显信息保存在request域中
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);


            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }
}
