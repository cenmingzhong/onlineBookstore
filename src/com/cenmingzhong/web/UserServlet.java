package com.cenmingzhong.web;

import com.cenmingzhong.prjo.User;
import com.cenmingzhong.service.UserService;
import com.cenmingzhong.service.impl.UserServiceImpl;
import com.cenmingzhong.test.UserServletTest;
import com.cenmingzhong.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AuthProvider;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author cenmingzhong
 * @create 2020-09-17-下午 16:12
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.销毁session中用户登陆的信息（或者销毁session)
        req.getSession().invalidate();
        //2.重定向到首页或者登陆页面
        resp.sendRedirect(req.getContextPath());

    }

        /**
         * 处理登陆的功能
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


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
                //登陆成功
                //保存用户登陆的信息到session域中
                req.getSession().setAttribute("user",loginUser);
                //请求转发到登陆成功页面
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
                System.out.println("登录成功");
            }


        }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数username
        String username = req.getParameter("username");
        //调用userService.existsUsername()
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装成为Map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);


    }

        /**
         * 处理注册的功能
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取Session中的验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String code = req.getParameter("code");


        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());


        if (token!=null&&token.equalsIgnoreCase(code)) {
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
