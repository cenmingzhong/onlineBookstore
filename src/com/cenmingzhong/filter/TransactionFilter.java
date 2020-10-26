package com.cenmingzhong.filter;

import com.cenmingzhong.utils.JdbcUtils;
import jdk.nashorn.internal.scripts.JD;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author cenmingzhong
 * @create 2020-09-30-上午 11:27
 */
public class TransactionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();//提交事务

        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛出去给Tomcat管理展示友好的错误页面
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
